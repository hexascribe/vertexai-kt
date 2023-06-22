package com.hexascribe.vertexai.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class InstanceDto(
    @SerialName("prompt")
    val prompt: String
)
