package com.hexascribe.vertexai.data.repository

import com.hexascribe.vertexai.data.api.VertexApi
import com.hexascribe.vertexai.data.dto.request.DataDto

internal class VertexRepository(private val vertexApi: VertexApi) {

     suspend fun text(param: DataDto): Result<List<String>> {
          return try {
               val result = vertexApi.text(param)
                    .getBodyOrThrow()
                    .predictions
                    .map { it.content }
               Result.success(result)
          } catch (throwable: Throwable) {
               Result.failure(throwable)
          }
     }
}
