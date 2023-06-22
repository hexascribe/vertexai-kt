package com.hexascribe.vertexai.main.viewmodel

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.hexascribe.vertexai.BuildConfig
import com.hexascribe.vertexai.VertexAI
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class MainViewModel @Inject constructor() : ViewModel() {

    private val vertexAI by lazy {
        VertexAI.Builder()
            .setAccessToken(BuildConfig.ACCESS_TOKEN)
            .setProjectId(BuildConfig.PROJECT_ID)
            .build()
    }

    val message = mutableStateOf("")

    private val textRequest by lazy {
        vertexAI.textRequest()
            .setMaxTokens(256)
            .setTemperature(0.8)
    }

    fun setMessage(message: String) {
        this.message.value = message
    }

    fun request() = viewModelScope.launch {
        val result = textRequest.execute(message.value)
        handleTextResult(result)
    }

    private fun handleTextResult(result: Result<List<String>>) {
        result
            .onSuccess {
                println("Text Result Success: " + it.first())
            }
            .onFailure {
                println("Text Result Failure: " + it.message)
            }
    }
}
