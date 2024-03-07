package com.example.composeandflow.model.api

import com.example.composeandflow.model.ApiItems
import retrofit2.http.GET

interface ApiService {
    @GET("hiring.json")
    suspend fun getUsers(): List<ApiItems>

}