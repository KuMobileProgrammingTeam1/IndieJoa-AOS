package com.example.myapp.stage

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.myapp.Retrofit.RetrofitFactory.addressService
import com.example.myapp.Retrofit.StageData
import kotlinx.coroutines.launch
import retrofit2.HttpException

class AddressViewModel : ViewModel() {
    private val _responseData = MutableLiveData<StageData?>()
    val responseData: LiveData<StageData?> get() = _responseData

    private val _errorMessage = MutableLiveData<String?>()
    val errorMessage: LiveData<String?> get() = _errorMessage

    val isStageDataLoaded = mutableStateOf(false)

    fun getAddresses(indieStreetId: Int) {
        isStageDataLoaded.value = false
        viewModelScope.launch {
            try {
                Log.d("AddressViewModel", "id: $indieStreetId")
                val responseData = addressService.getAddressById(indieStreetId)
                _responseData.value = responseData
                Log.d("AddressViewModel", "Response data: $responseData")
                isStageDataLoaded.value = true
            } catch (e: HttpException) {
                _errorMessage.value = "공연장 정보가 없습니다."
                Log.e("AddressViewModel", "Failed to get addresses", e)

            } catch (e: Exception) {
                _errorMessage.value = "Error: ${e.message}"
                Log.e("AddressViewModel", "Failed to get addresses", e)
            }
        }
    }

}