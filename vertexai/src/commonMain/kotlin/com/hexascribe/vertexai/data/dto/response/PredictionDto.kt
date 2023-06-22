package com.hexascribe.vertexai.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PredictionDto(
    @SerialName("safetyAttributes")
    val safetyAttributes: SafetyAttributesDto,
    @SerialName("content")
    val content: String
)
