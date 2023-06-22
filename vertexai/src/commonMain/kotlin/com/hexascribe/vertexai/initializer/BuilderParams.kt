package com.hexascribe.vertexai.initializer

internal data class BuilderParams(
    var projectId: String = "",
    var accessToken: String = "",
    var region: String = "us-central1",
)
