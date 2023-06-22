package com.hexascribe.vertexai

import com.hexascribe.vertexai.features.TextRequest
import com.hexascribe.vertexai.initializer.BuilderParams
import com.hexascribe.vertexai.initializer.VertexImpl
import kotlin.jvm.Volatile
import kotlin.native.concurrent.ThreadLocal

/**
 * The [VertexAI] library aims to abstract all API call logic from VertexAI APIs.
 */
public abstract class VertexAI {

    /**
     * Fine-tuned to follow natural language instructions and is suitable for a variety of language tasks.
     * This request is ideal for tasks that can be completed with one API response, without the need
     * for continuous conversation.
     */
    public abstract fun textRequest(): TextRequest

    /**
     * A builder class for configuring and constructing an instance of [VertexAI].
     */
    public class Builder {

        private var params = BuilderParams()

        /**
         * Sets the Google Cloud Project ID.
         * @return The [Builder] instance with the updated [projectId].
         */
        public fun setProjectId(projectId: String): Builder {
            this.params.projectId = projectId
            return this
        }

        /**
         * Sets the Access Token for authentication.
         * Read more about [Google Cloud Platform Authentication](https://cloud.google.com/docs/authentication)
         * @return The [Builder] instance with the updated [accessToken].
         */
        public fun setAccessToken(accessToken: String): Builder {
            this.params.accessToken = accessToken
            return this
        }

        /**
         * Sets the Vertex AI region to be used.
         * If this method is not set, the default value will be us-central1.
         * @return The [Builder] instance with the updated [region].
         */
        public fun setRegion(region: String): Builder {
            this.params.region = region
            return this
        }

        /**
         * Builds and returns an instance of [VertexAI].
         * @throws IllegalStateException if the required parameters are not set.
         * @return An instance of [VertexAI].
         */
        public fun build(): VertexAI {
            if (this.params.projectId.isEmpty()) {
                throw IllegalStateException("Project ID is required. Please call the setProjectId method in the builder.")
            }
            if (this.params.accessToken.isEmpty()) {
                throw IllegalStateException("Access Token is required. Please call the setAccessToken method in the builder.")
            }
            return instance ?: VertexImpl(params).also { instance = it }
        }

        @ThreadLocal
        internal companion object {
            @Volatile
            private var instance: VertexAI? = null
        }
    }
}
