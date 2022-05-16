package com.oscar.test.ui.login

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oscar.test.network.Resource
import com.oscar.test.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun login(user: String, password: String) = liveData {
        emit(Resource.loading(null))
        try {
            val token = mainRepository.login(user, password)
            emit(Resource.success(token))
        } catch (exception: Exception) {
            Log.e("Mode", exception.localizedMessage)
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }

}