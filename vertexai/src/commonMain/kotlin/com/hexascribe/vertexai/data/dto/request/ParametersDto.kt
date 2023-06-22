package com.hexascribe.vertexai.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class ParametersDto(
    @SerialName("temperature")
    val temperature: Double,
    @SerialName("maxOutputTokens")
    val maxOutputTokens: Int,
    @SerialName("topK")
    val topK: Int,
    @SerialName("topP")
    val topP: Double
)
