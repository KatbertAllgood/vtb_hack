package com.example.vtb_hackathon

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vtb_hackathon.data.mapper.toData
import com.example.vtb_hackathon.data.utils.Constants
import com.example.vtb_hackathon.domain.usecase.database.InsertOfficeDBUseCase
import com.example.vtb_hackathon.domain.usecase.network.GetAllOfficesUseCase
import com.example.vtb_hackathon.domain.usecase.sp.GetStringPrefsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainVM @Inject constructor(
    private val getStringPrefsUseCase: GetStringPrefsUseCase,
    getAllOfficesUseCase: GetAllOfficesUseCase,
    insertOfficeDBUseCase: InsertOfficeDBUseCase
) : ViewModel() {

    private val TAG = MainVM::class.simpleName

    private val navigationLiveData = MutableLiveData<Boolean>()

    init {
        viewModelScope.launch(Dispatchers.IO) {
            getAllOfficesUseCase().catch {
                Log.e("NETWORK_ERROR", it.message.toString())
            }.collect { bankOfficeDomains ->
                Log.d("", bankOfficeDomains.toString())
                insertOfficeDBUseCase(bankOfficeDomains.map {
                    it.toData()
                })
            }
        }
    }

    fun getNavLiveData(): LiveData<Boolean> = navigationLiveData

    fun checkPhoneNumber() {

        viewModelScope.launch(Dispatchers.IO) {

            Log.d(TAG, getStringPrefsUseCase(Constants.PHONE_NUMBER))

            if (getStringPrefsUseCase(Constants.PHONE_NUMBER) == "") {

                navigationLiveData.postValue(false)
            } else {

                navigationLiveData.postValue(true)
            }
        }
    }
}