package com.example.composeandflow

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ViewModelFactory(private val apiHelper: ApiHelper,
                       private val dispatcherProvider: DispatcherProvider
) : ViewModelProvider.NewInstanceFactory() {
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(SingleNetworkCallViewModel::class.java)) {
            return SingleNetworkCallViewModel(apiHelper, dispatcherProvider) as T
        }
        throw IllegalArgumentException("Unknown class name")
}
}