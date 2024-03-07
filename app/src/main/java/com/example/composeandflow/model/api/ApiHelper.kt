package com.example.composeandflow.model.api

import com.example.composeandflow.model.ApiItems
import kotlinx.coroutines.flow.Flow
interface ApiHelper {
    fun getUsers(): Flow<List<ApiItems>>


}