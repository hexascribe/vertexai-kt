package com.hexascribe.vertexai.di.provider

import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.engine.okhttp.OkHttp

internal actual object HttpEngineProvider {
    actual fun getEngine(): HttpClientEngine {
        return OkHttp.create()
    }
}
