package com.example.vtb_hackathon.ui.screen.map

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vtb_hackathon.domain.usecase.database.GetAllOfficesDBUseCase
import com.example.vtb_hackathon.domain.usecase.database.GetOfficeByCordsDBUseCase
import com.example.vtb_hackathon.domain.usecase.database.GetOfficeByIdDbUseCase
import com.example.vtb_hackathon.domain.usecase.location.GetLocationUseCase
import com.example.vtb_hackathon.entity.BankOfficePresentation
import com.example.vtb_hackathon.entity.CurrentLocationPresentation
import com.example.vtb_hackathon.entity.DepartmentPresentation
import com.example.vtb_hackathon.entity.FilterList
import com.example.vtb_hackathon.mappers.toPresentation
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MapVM @Inject constructor(
    getLocationUseCase: GetLocationUseCase,
    getAllOfficesUseCase: GetAllOfficesDBUseCase,
    private val getOfficeByIdDbUseCase: GetOfficeByIdDbUseCase,
    private val getOfficeByCordsDBUseCase: GetOfficeByCordsDBUseCase
) : ViewModel() {
    private var index:Int = 0
    private val TAG = MapVM::class.simpleName

    private var allOfficesList: List<BankOfficePresentation>? = null

    init {
        viewModelScope.launch(Dispatchers.IO) {
            launch {
                getLocationUseCase.invoke().catch {
                    Log.e("LocationError", it.message.toString())
                }.collect {
                    userLocation.postValue(it.toPresentation())
                }
            }
            getAllOfficesUseCase().catch {
                Log.e("DatabaseError", it.message.toString())
            }.collect { bankOfficeDomains ->
                Log.d("OFFICES", bankOfficeDomains.toString())
                allOfficesList = bankOfficeDomains.map {
                    it.toPresentation()
                }
                updateCurrentOfficesList(allOfficesList!!)
                getServicesByFilter(0)
            }
        }
    }

    fun selectOfficeByCords(latitude: Double, longitude: Double) {

        Log.d("DATABSEERROR", latitude.toString())
        viewModelScope.launch(Dispatchers.IO) {
            getOfficeByCordsDBUseCase.invoke(longitude, latitude).catch {
                Log.e("DATABSEERROR", it.message.toString())
            }.collect {
                Log.d("DATABSEERROR", it.toString())

                _chosenOffice.postValue(it.toPresentation())
            }
        }
    }

    private var userLocation = MutableLiveData<CurrentLocationPresentation>()

    private var _currentOfficesList = MutableLiveData<List<BankOfficePresentation>>()

    private var _chosenOffice = MutableLiveData<BankOfficePresentation>()

    private val _servicesLiveData = MutableLiveData<List<String>>()

    private var _selectedService: String by mutableStateOf("Все")

    private var _selectedFilterIndex: Int by mutableStateOf(0)

    private var _navigationDrivingTime: String by mutableStateOf("")

    private var _navigationDrivingDistance: String by mutableStateOf("")

    private var _navigationWalkingTime: String by mutableStateOf("")

    private var _navigationWalkingDistance: String by mutableStateOf("")

    private var _navigationType: Int by mutableStateOf(1)

    val getUserLocation: LiveData<CurrentLocationPresentation>
        get() = userLocation

    val getCurrentOfficesList: LiveData<List<BankOfficePresentation>>
        get() = _currentOfficesList

    val getChosenOffice: LiveData<BankOfficePresentation>
        get() = _chosenOffice

    val getServicesLiveData: LiveData<List<String>>
        get() = _servicesLiveData

    val selectedService: String
        get() = _selectedService

    val selectedFilterIndex: Int
        get() = _selectedFilterIndex

    val navigationDrivingTime: String
        get() = _navigationDrivingTime

    val navigationDrivingDistance: String
        get() = _navigationDrivingDistance

    val navigationWalkingTime: String
        get() = _navigationWalkingTime

    val navigationWalkingDistance: String
        get() = _navigationWalkingDistance

    val navigationType: Int
        get() = _navigationType

    private fun updateCurrentOfficesList(list: List<BankOfficePresentation>) {
        _currentOfficesList.postValue(list)
    }

    fun updateChosenOffice(value: BankOfficePresentation) {
        viewModelScope.launch(Dispatchers.IO) {
            getOfficeByIdDbUseCase(value.id).catch {
                Log.e("DatabaseError", it.message.toString())
            }.collect {
                _chosenOffice.postValue(it.toPresentation())
            }
        }
    }

    private fun getServicesByFilter(index: Int) {
        _servicesLiveData.postValue(FilterList.filterList[index].serviceList)
        getAllOffices(index)
    }

    private fun getAllOffices(index: Int) {
        _currentOfficesList.postValue(allOfficesList?.filter {
            it.departments[0].type == index
        })
    }

    private fun getOfficesByService(serviceName: String) {
        if (serviceName == "Все") {
            getAllOffices(index)
        } else {
            val sortedByServiceList: MutableList<BankOfficePresentation> = mutableListOf()
            allOfficesList?.forEach {
                var sortedDepartments: MutableList<DepartmentPresentation> = mutableListOf()
                it.departments.forEach {
                    sortedDepartments.add(
                        DepartmentPresentation(
                            it.type,
                            it.services.filter {
                                it.name == serviceName
                            }
                        )
                    )
                }
                sortedDepartments = sortedDepartments.filter {
                    it.services.isNotEmpty()
                }.toMutableList()
                sortedByServiceList.add(
                    BankOfficePresentation(
                        id = it.id,
                        name = it.name,
                        address = it.address,
                        departments = sortedDepartments,
                        longitude = it.longitude,
                        latitude = it.latitude,
                        isForLowMobility = it.isForLowMobility,
                        occupancy = it.occupancy
                    )
                )
            }
            Log.d("SORTED LIST", sortedByServiceList.toString())
            _currentOfficesList.postValue(sortedByServiceList.filter {
                it.departments.isNotEmpty()
            })
        }
    }

    fun updateSelectedService(value: String) {
        _selectedService = value
        getOfficesByService(value)
    }

    fun updateSelectedFilterIndex(value: Int) {
        _selectedFilterIndex = value
        getServicesByFilter(value)
        index = value
    }

    fun updateNavigationDrivingTime(value: String) {
        _navigationDrivingTime = value
    }

    fun updateNavigationDrivingDistance(value: String) {
        _navigationDrivingDistance = value
    }

    fun updateNavigationWalkingTime(value: String) {
        _navigationWalkingTime = value
    }

    fun updateNavigationWalkingDistance(value: String) {
        _navigationWalkingDistance = value
    }

    fun updateNavigationType(value: Int) {
        _navigationType = value
    }
}