package com.example.composeandflow.view

import android.os.Bundle
import android.view.View
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable

class CustomUserProfile: ComponentActivity() {
    override fun onCreate(savedInstanceState : Bundle?){
        super.onCreate(savedInstanceState)
        setContent {
            profilePage()
        }

    }
}

@Composable
fun profilePage(){

}