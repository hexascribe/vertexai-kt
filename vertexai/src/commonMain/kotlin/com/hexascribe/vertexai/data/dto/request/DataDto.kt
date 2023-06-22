package com.hexascribe.vertexai.data.dto.request

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
internal data class DataDto(
    @SerialName("instances")
    val instances: List<InstanceDto>,
    @SerialName("parameters")
    val parameters: ParametersDto
)
