package com.example.composeandflow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.composeandflow.model.api.ApiHelper
import com.example.composeandflow.utils.DispatcherProvider

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