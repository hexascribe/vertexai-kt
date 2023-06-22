package com.hexascribe.vertexai.domain

import com.hexascribe.vertexai.network.exception.VertexException

/**
 * Represents the result of a vertex operation, which can contain a value of type T or an exception.
 * @param T the type of the data contained in the result.
 * @property data The data contained in the result. It can be null.
 * @property exception The exception associated with the result. It can be null.
 * @property isSuccessful Indicates whether the operation was successful or not.
 */
public data class VertexResult<T : Any>(
    private val data: T? = null,
    private val exception: VertexException? = null,
) {

    /**
     * Indicates whether the operation was successful or not.
     */
    val isSuccessful: Boolean = exception == null

    /**
     * Retrieves the data from the result or throws an exception if the data is null.
     * @return The data from the result.
     * @throws VertexException if the data is null.
     */
    public fun getOrThrow(): T {
        val exception = exception
            ?: VertexException("")
        return data ?: throw exception
    }

    /**
     * Retrieves the data from the result or returns null if the data is null.
     * @return The data from the result, or null if the data is null.
     */
    public fun getOrNull(): T? {
        return data
    }

    /**
     * Retrieves the data from the result or returns a default value if the data is null.
     * @param default The default value to return if the data is null.
     * @return The data from the result, or the default value if the data is null.
     */
    public fun getOrDefault(default: T): T {
        return data ?: default
    }

    /**
     * Retrieves the exception from the result or returns null if there is no exception.
     * @return The exception from the result, or null if there is no exception.
     */
    public fun exceptionOrNull(): VertexException? {
        return exception
    }

    /**
     * Executes the specified action if the operation was successful.
     * @param action The action to execute.
     * @return The current VertexResult instance.
     */
    public fun onSuccess(action: (value: T) -> Unit): VertexResult<T> {
        if (isSuccessful) action(getOrThrow())
        return this
    }

    /**
     * Executes the specified action if the operation failed.
     * @param action The action to execute.
     * @return The current VertexResult instance.
     */
    public fun onFailure(action: (value: VertexException) -> Unit): VertexResult<T> {
        exceptionOrNull()?.let { action(it) }
        return this
    }

    /**
     * Ensures that the operation was successful, otherwise throws an exception.
     * @throws VertexException if the operation was not successful and no exception is available.
     */
    public fun ensureSuccess() {
        if (!isSuccessful)
            throw exception ?: VertexException("")
    }

    internal companion object {
        fun <T : Any> success(value: T) = VertexResult(value)
        fun <T : Any> failure(exception: VertexException) = VertexResult<T>(exception = exception)
    }
}
