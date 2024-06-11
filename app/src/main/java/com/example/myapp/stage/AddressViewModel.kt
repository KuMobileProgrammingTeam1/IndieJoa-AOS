package com.example.myapp.stage

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Retrofit.RetrofitFactory
import com.example.myapp.Retrofit.RetrofitFactory.addressService
import com.example.myapp.Retrofit.StageResponse
import kotlinx.coroutines.launch

class AddressViewModel : ViewModel() {
    private val _responseData = MutableLiveData<StageResponse?>()
    val responseData: LiveData<StageResponse?> get() = _responseData

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    fun fetchAddresses(page: Int = 0, size: Int = 100) {
        viewModelScope.launch {
            try {
                val responseData = addressService.getAddressById(page, size)
                _responseData.value = responseData
                Log.d("AddressViewModel", "Fetched response data: $responseData")
            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
                Log.e("AddressViewModel", "Failed to fetch addresses", e)
            }
        }
    }
}