package com.hexascribe.vertexai.features.impl

import com.hexascribe.vertexai.data.repository.VertexRepository
import com.hexascribe.vertexai.domain.TextParameters
import com.hexascribe.vertexai.domain.toDataDto
import com.hexascribe.vertexai.features.TextRequest
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.SupervisorJob

internal class TextRequestImpl(
    private val dispatcher: CoroutineDispatcher,
    private val vertexRepository: VertexRepository,
): TextRequest {

    private val scope by lazy { CoroutineScope(SupervisorJob() + dispatcher) }

    private var params: TextParameters = TextParameters()

    override fun setTemperature(temperature: Double): TextRequest {
        this.params.temperature = temperature
        return this
    }

    override fun setMaxTokens(maxTokens: Int): TextRequest {
        this.params.maxTokens = maxTokens
        return this
    }

    override fun setTopK(topK: Int): TextRequest {
        this.params.topK = topK
        return this
    }

    override fun setTopP(topP: Double): TextRequest {
        this.params.topP = topP
        return this
    }

    override suspend fun execute(prompt: String): Result<List<String>> {
        this.params.prompt = prompt
        val requestDto = params.toDataDto()
        return vertexRepository.text(requestDto)
    }
}
