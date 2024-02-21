package com.example.composeandflow

import kotlinx.coroutines.flow.Flow

interface ApiHelper {
    fun getUsers(): Flow<List<ApiUser>>

    fun getMoreUsers(): Flow<List<ApiUser>>

    fun getUsersWithError(): Flow<List<ApiUser>>
}