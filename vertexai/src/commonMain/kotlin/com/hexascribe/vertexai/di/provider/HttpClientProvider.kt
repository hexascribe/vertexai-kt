package com.hexascribe.vertexai.di.provider

import com.hexascribe.vertexai.initializer.BuilderParams
import io.ktor.client.HttpClient
import io.ktor.client.engine.HttpClientEngine
import io.ktor.client.plugins.HttpTimeout
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.ContentType
import io.ktor.http.URLProtocol
import io.ktor.http.contentType
import io.ktor.serialization.kotlinx.json.json
import kotlinx.serialization.json.Json

internal class HttpClientProvider(
    private val builderParams: BuilderParams,
    private val httpClientEngine: HttpClientEngine = HttpEngineProvider.getEngine(),
) {

    fun provide(): HttpClient {
        val baseUrl = "${builderParams.region}-aiplatform.googleapis.com"
        return HttpClient(httpClientEngine) {
            defaultRequest {
                url {
                    host = baseUrl
                    protocol = URLProtocol.HTTPS
                    contentType(ContentType.Application.Json)
                }
                header("Authorization", "Bearer ${builderParams.accessToken}")
            }
            install(HttpTimeout) {
                requestTimeoutMillis = TIMEOUT_MILLIS
                connectTimeoutMillis = TIMEOUT_MILLIS
                socketTimeoutMillis = TIMEOUT_MILLIS
            }
            install(ContentNegotiation) {
                json(
                    Json {
                        prettyPrint = true
                        isLenient = true
                        ignoreUnknownKeys = true
                    }
                )
            }
        }
    }

    companion object {
        private const val TIMEOUT_MILLIS = 60000L
    }
}
