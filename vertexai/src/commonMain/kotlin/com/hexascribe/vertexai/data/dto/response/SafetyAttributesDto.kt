package com.hexascribe.vertexai.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class SafetyAttributesDto(
    @SerialName("categories")
    val categories: List<String>,
    @SerialName("scores")
    val scores: List<Double>,
    @SerialName("blocked")
    val blocked: Boolean
)
