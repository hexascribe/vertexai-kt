package com.hexascribe.vertexai.network.infrastructure

import com.hexascribe.vertexai.network.exception.VertexException

/** Encapsulates an outcome from source api. */
internal data class ApiResult<T>(
    val body: T? = null,
    val headers: Map<String, List<String>> = mapOf(),
    val statusCode: Int? = null,
    val exception: VertexException? = null,
) {

    /** Return true if the api outcome was successful. */
    val isSuccessful: Boolean = exception == null

    /** Try to get [body], if it is null an [VertexException] will be thrown. */
    fun getBodyOrThrow(): T {
        val exception = exception
            ?: VertexException("Could not retrieve body from ApiResult.")
        return body ?: throw exception
    }

    /** Throw an [exception] when [isSuccessful] is false. */
    fun ensureSuccess() {
        if (!isSuccessful)
            throw exception ?: VertexException("Api request was not successful.")
    }
}
