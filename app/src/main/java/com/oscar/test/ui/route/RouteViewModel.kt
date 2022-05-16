package com.oscar.test.ui.route

import androidx.lifecycle.ViewModel
import androidx.lifecycle.liveData
import com.oscar.test.network.Resource
import com.oscar.test.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class RouteViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    fun getRoute(plate: String) = liveData {
        emit(Resource.loading(null))
        try {
            val route = mainRepository.getRoute(plate)
            emit(Resource.success(route))
        } catch (exception: Exception) {
            emit(Resource.error(data = null, msg = exception.message ?: "Error Occurred!"))
        }
    }

}