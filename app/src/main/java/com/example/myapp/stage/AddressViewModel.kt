package com.example.myapp.stage

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Retrofit.RetrofitFactory
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {
    private val _address = MutableLiveData<StageAddress?>()
    val address: LiveData<StageAddress?> get() = _address

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun fetchAddressById(id: Long) {
        viewModelScope.launch {
            try {
                val address = RetrofitFactory.addressService.getAddressById(id)
                _address.value = address
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
            }
        }
    }
}