package com.example.composeandflow.model.api

import com.example.composeandflow.model.ApiUser
import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>
}