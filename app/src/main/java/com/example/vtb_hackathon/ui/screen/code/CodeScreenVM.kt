package com.example.vtb_hackathon.ui.screen.code

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vtb_hackathon.data.utils.Constants
import com.example.vtb_hackathon.domain.usecase.sp.GetStringPrefsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class CodeScreenVM @Inject constructor(
    private val getStringPrefsUseCase: GetStringPrefsUseCase
) : ViewModel() {

    init {

    }

    private var _firstDigit: String by mutableStateOf("")
    private var _secondDigit: String by mutableStateOf("")
    private var _thirdDigit: String by mutableStateOf("")
    private var _fourthDigit: String by mutableStateOf("")
    private var _fifthDigit: String by mutableStateOf("")
    private var _sixthDigit: String by mutableStateOf("")

    val firstDigit: String
        get() = _firstDigit

    val secondDigit: String
        get() = _secondDigit

    val thirdDigit: String
        get() = _thirdDigit

    val fourthDigit: String
        get() = _fourthDigit

    val fifthDigit: String
        get() = _fifthDigit

    val sixthDigit: String
        get() = _sixthDigit

    fun updateDigit(
        num: Int,
        value: String
    ) = when (num) {
        1 -> _firstDigit = value
        2 -> _secondDigit = value
        3 -> _thirdDigit = value
        4 -> _fourthDigit = value
        5 -> _fifthDigit = value
        6 -> _sixthDigit = value
        else -> { }
    }

    private var _phoneNumberLiveData = MutableLiveData<String>()
    val phoneNumberLiveData : LiveData<String>
        get() = _phoneNumberLiveData

    fun getPhoneNumberPrefs() {

        viewModelScope.launch(Dispatchers.IO) {

            _phoneNumberLiveData.postValue(getStringPrefsUseCase.invoke(Constants.PHONE_NUMBER))
        }
    }
}