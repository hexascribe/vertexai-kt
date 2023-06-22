package com.hexascribe.vertexai.di.provider

import io.ktor.client.engine.HttpClientEngine

internal expect object HttpEngineProvider {
    actual fun getEngine(): HttpClientEngine
}
