package com.example.composeandflow.viewModel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.composeandflow.model.api.UiState
import com.example.composeandflow.model.ApiItems
import com.example.composeandflow.model.api.ApiHelper
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.flowOn
import kotlinx.coroutines.flow.map
import kotlinx.coroutines.launch

class MainViewModel(private val apiHelper: ApiHelper, )
    : ViewModel() {

    private val _uiState = MutableStateFlow<UiState<List<ApiItems>>>(UiState.Loading)
    val uiState: StateFlow<UiState<List<ApiItems>>> = _uiState
fun fetchUsers() {
    viewModelScope.launch(Dispatchers.Main) {
        _uiState.value = UiState.Loading
        apiHelper.getUsers()
            .flowOn(Dispatchers.IO)
            .catch { e ->
                _uiState.value = UiState.Error(e.toString())
            }
            .map { userList ->
                userList.groupBy { it.listId }.flatMap { it.value }
                    .sortedBy { it.listId }
                    .sortedBy { it.name }
                    .filter { it.name != null && it.name!!.isNotBlank() }
            }
            .collect { groupedUsers ->
                _uiState.value = UiState.Success(groupedUsers)
            }
        }
    }
}
