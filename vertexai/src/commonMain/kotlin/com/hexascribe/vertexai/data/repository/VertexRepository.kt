package com.hexascribe.vertexai.data.repository

import com.hexascribe.vertexai.data.api.VertexApi
import com.hexascribe.vertexai.data.dto.request.DataDto
import com.hexascribe.vertexai.domain.VertexResult
import com.hexascribe.vertexai.network.exception.VertexException

internal class VertexRepository(private val vertexApi: VertexApi) {

     suspend fun text(param: DataDto): VertexResult<String> {
          return try {
               val result = vertexApi.text(param)
                    .getBodyOrThrow()
                    .predictions
                    .first().content
               VertexResult.success(result)
          } catch (vertexException: VertexException) {
               VertexResult.failure(vertexException)
          }
     }
}
