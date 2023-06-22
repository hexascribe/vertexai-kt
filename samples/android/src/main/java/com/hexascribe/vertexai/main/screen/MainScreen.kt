package com.hexascribe.vertexai.main.screen

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.hexascribe.vertexai.main.viewmodel.MainViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainScreen(mainViewModel: MainViewModel = viewModel()) {
    Column(
        horizontalAlignment = Alignment.CenterHorizontally,
        modifier = Modifier
            .fillMaxSize()
            .padding(24.dp),
    ) {
        Text(text = "VertexAI Sample", style = MaterialTheme.typography.titleLarge)
        Spacer(modifier = Modifier.height(24.dp))
        OutlinedTextField(
            value = mainViewModel.message.value,
            onValueChange = { mainViewModel.setMessage(it) }
        )
        Spacer(modifier = Modifier.height(24.dp))
        Button(
            onClick = { mainViewModel.request() },
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(text = "Request")
        }
    }
}

@Composable
@Preview
private fun MainScreenPreview() {
    MainScreen()
}
