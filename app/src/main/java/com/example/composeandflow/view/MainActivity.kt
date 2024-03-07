package com.example.composeandflow.view

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.MaterialTheme.typography
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.ViewModelProvider
import com.example.composeandflow.model.ApiItems
import com.example.composeandflow.model.api.ApiHelperImpl
import com.example.composeandflow.model.api.RetrofitBuilder
import com.example.composeandflow.model.api.UiState
import com.example.composeandflow.ui.theme.ComposeAndFlowTheme
import com.example.composeandflow.viewModel.SingleNetworkCallViewModel
import com.example.composeandflow.viewModel.ViewModelFactory

class MainActivity : AppCompatActivity() {

    private lateinit var viewModel: SingleNetworkCallViewModel

    @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
    @OptIn(ExperimentalMaterial3Api::class)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setupViewModel()
        viewModel.fetchUsers()

        setContent {
            val uiState by viewModel.uiState.collectAsState()
            ComposeAndFlowTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    Scaffold(
                        content = {
                            ItemList(uiState)
                        }
                    )
                }
            }
        }
    }
    private fun setupViewModel() {
        viewModel = ViewModelProvider(
            this,
            ViewModelFactory(
                ApiHelperImpl(RetrofitBuilder.apiService))
        )[SingleNetworkCallViewModel::class.java]
    }
}
@Composable
fun ItemList(uiState: UiState<List<ApiItems>>) {
    when (uiState) {
        is UiState.Loading -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(20.dp),
                contentAlignment = Alignment.Center
            ) {
                CircularProgressIndicator()
            }
        }
        is UiState.Success -> {
            LazyColumn {
                items(uiState.data) { user ->
                    ItemCard(user)
                }
            }
        }
        is UiState.Error -> {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(16.dp),
                contentAlignment = Alignment.Center
            ) {
                Text("Error: ${uiState.message}", style = typography.bodyLarge)
            }
        }
    }
}
@Composable
fun ItemCard(items: ApiItems) {

    Row(modifier = Modifier
        .padding(all = 5.dp)
    ) {

        Column(Modifier.width(1000.dp)) {
            Text(
                text = "Name: ${items.name}", style = typography.titleLarge,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "ID: ${items.id}", style = typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
            Text(
                text = "LIST-ID: ${items.listId}", style = typography.titleMedium,
                color = MaterialTheme.colorScheme.onBackground
            )
        }
    }
}

