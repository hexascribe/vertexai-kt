package com.hexascribe.vertexai.network.infrastructure

import com.hexascribe.vertexai.network.extensions.toApiResult
import io.ktor.client.HttpClient
import io.ktor.client.plugins.ResponseException
import io.ktor.client.request.request
import io.ktor.client.request.setBody
import io.ktor.client.statement.HttpResponse
import io.ktor.client.utils.EmptyContent
import io.ktor.http.HttpMethod
import io.ktor.utils.io.errors.IOException

internal class ApiExecutor(private val httpClient: HttpClient) {

    private lateinit var endpoint: String

    private lateinit var httpMethod: HttpMethod

    private var body: Any = EmptyContent

    fun setEndpoint(endpoint: String): ApiExecutor {
        this.endpoint = endpoint
        return this
    }

    fun setHttpMethod(httpMethod: HttpMethod): ApiExecutor {
        this.httpMethod = httpMethod
        return this
    }

    fun setBody(body: Any): ApiExecutor {
        this.body = body
        return this
    }

    suspend inline fun <reified T> execute(): ApiResult<T> {
        return try {
            val response = executeRequest()
            return response.toApiResult()
        } catch (responseException: ResponseException) {
            responseException.toApiResult()
        } catch (iOException: IOException) {
            iOException.toApiResult()
        }
    }

    suspend fun executeRequest(): HttpResponse {
        return httpClient.request(endpoint) {
            method = httpMethod
            this.setBody(this@ApiExecutor.body)
        }
    }
}
