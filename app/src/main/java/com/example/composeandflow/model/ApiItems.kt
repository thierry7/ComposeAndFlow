package com.example.composeandflow.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable
data class ApiItems(
     var id     : Int?,
     var listId : Int?,
     var name   : String?
): Serializable
