package com.hexascribe.vertexai.domain

import com.hexascribe.vertexai.data.dto.request.DataDto
import com.hexascribe.vertexai.data.dto.request.InstanceDto
import com.hexascribe.vertexai.data.dto.request.ParametersDto

internal fun TextParameters.toDataDto(): DataDto {
    return DataDto(
        instances = listOf(InstanceDto(prompt = this.prompt)),
        parameters = ParametersDto(
            temperature = this.temperature,
            maxOutputTokens = this.maxTokens,
            topK = this.topK,
            topP = this.topP,
        )
    )
}
