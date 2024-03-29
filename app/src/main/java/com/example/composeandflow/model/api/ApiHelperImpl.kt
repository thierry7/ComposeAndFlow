package com.example.composeandflow.model.api

import kotlinx.coroutines.flow.flow

class ApiHelperImpl(private val apiService: ApiService): ApiHelper {
    override fun getUsers() = flow {
        emit(apiService.getUsers())
    }
}