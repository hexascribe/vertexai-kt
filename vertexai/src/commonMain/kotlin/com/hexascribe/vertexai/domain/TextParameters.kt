package com.hexascribe.vertexai.domain

internal data class TextParameters(
    var model: String = "text-bison",
    var prompt: String = "",
    var temperature: Double = 0.2,
    var maxTokens: Int = 256,
    var topK: Int = 40,
    var topP: Double = 0.8,
)
