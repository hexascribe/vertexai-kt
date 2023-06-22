package com.hexascribe.vertexai.data.api

import com.hexascribe.vertexai.data.dto.request.DataDto
import com.hexascribe.vertexai.data.dto.response.PredictionsDto
import com.hexascribe.vertexai.network.infrastructure.ApiExecutor
import com.hexascribe.vertexai.network.infrastructure.ApiResult
import io.ktor.http.HttpMethod

internal class VertexApi(
    private val projectId: String,
    private val region: String,
    private val apiExecutor: ApiExecutor
) {

    suspend fun text(param: DataDto): ApiResult<PredictionsDto> {
        return apiExecutor
            .setEndpoint("v1/projects/$projectId/locations/$region/publishers/google/models/text-bison:predict")
            .setHttpMethod(HttpMethod.Post)
            .setBody(param)
            .execute()
    }
}
