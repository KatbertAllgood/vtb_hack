package com.example.vtb_hackathon.ui.screen.login

import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.vtb_hackathon.data.utils.Constants
import com.example.vtb_hackathon.domain.usecase.network.GetAllOfficesUseCase
import com.example.vtb_hackathon.domain.usecase.sp.UpdatePrefsUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginVM @Inject constructor(
    private val updatePrefsUseCase: UpdatePrefsUseCase,
    private val getAllOfficesUseCase: GetAllOfficesUseCase
) : ViewModel() {

    private val TAG = LoginVM::class.simpleName

    private var _phoneNumber: String by mutableStateOf("")
    val phoneNumber: String
        get() = _phoneNumber

    fun updatePhoneNumber(phoneNumber: String) {
        _phoneNumber = phoneNumber
    }

    fun updatePhoneNumberPrefs(){

        val phoneToListChar = _phoneNumber.toList()

        val formedPhoneNumber = "+7 (${phoneToListChar[0]}${phoneToListChar[1]}${phoneToListChar[2]}) ${phoneToListChar[3]}${phoneToListChar[4]}${phoneToListChar[5]}-${phoneToListChar[6]}${phoneToListChar[7]}-${phoneToListChar[8]}${phoneToListChar[9]}"

        viewModelScope.launch(Dispatchers.IO) {

            Log.d(TAG, "phoneNum: $formedPhoneNumber")

            updatePrefsUseCase.invoke(Constants.PHONE_NUMBER, formedPhoneNumber)
        }
    }
}