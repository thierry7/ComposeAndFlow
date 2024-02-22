package com.example.composeandflow.model

import java.io.Serializable



data class ApiUser(
    val id: Int = 0,
    val name: String = "",
    val email: String = "",
    val avatar: String = ""
): Serializable
