package com.hexascribe.vertexai.data.dto.response

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class PredictionsDto(
    @SerialName("predictions")
    val predictions: List<PredictionDto>
)
