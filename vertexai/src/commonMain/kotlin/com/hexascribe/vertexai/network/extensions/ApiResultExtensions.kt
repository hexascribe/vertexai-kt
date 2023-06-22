package com.hexascribe.vertexai.network.extensions

import com.hexascribe.vertexai.network.infrastructure.ApiResult
import com.hexascribe.vertexai.network.exception.VertexException
import io.ktor.client.call.body
import io.ktor.client.plugins.ResponseException
import io.ktor.client.statement.HttpResponse
import io.ktor.client.statement.bodyAsText
import io.ktor.http.isSuccess
import io.ktor.util.toMap

internal suspend inline fun <reified T> HttpResponse.toApiResult(): ApiResult<T> {
    val headers = this.headers.toMap()
    val statusCode = this.status.value
    return if (!this.status.isSuccess()) {
        val errorMessage = this.bodyAsText()
        val exception = VertexException(errorMessage, statusCode = statusCode)
        ApiResult(null, headers, statusCode, exception)
    } else {
        ApiResult(this.body<T>(), headers, statusCode, null)
    }
}

internal fun <T> ResponseException.toApiResult(): ApiResult<T> {
    return ApiResult(
        statusCode = this.response.status.value,
        exception = VertexException(this.cause, this.response.status.value)
    )
}

internal fun <T> Throwable.toApiResult(): ApiResult<T> {
    return ApiResult(
        statusCode = null,
        exception = VertexException(this.cause)
    )
}
