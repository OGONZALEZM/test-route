package com.oscar.test.repository

import com.oscar.test.network.APIService
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val service: APIService
) {

    suspend fun login(user: String, password: String) = service.login(user, password)

    suspend fun getRoute(plate: String) = service.getRoute(plate)

}