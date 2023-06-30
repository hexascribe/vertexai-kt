package com.hexascribe.vertexai.features

import com.hexascribe.vertexai.VertexAI
import com.hexascribe.vertexai.domain.VertexResult

public interface TextRequest {

    /**
     * Sets the ID of the [model] to use. The default value is "text-bison".

     * @return The updated [TextRequest] object with the new [model] ID.
     */
    public fun setModel(model: String): TextRequest

    /**
     * The [temperature] is used for sampling during response generation, which occurs when topP
     * and topK are applied. Temperature controls the degree of randomness in token selection.
     * Lower temperatures are good for prompts that require a more deterministic and less open-ended
     * or creative response, while higher temperatures can lead to more diverse or creative results.
     * A temperature of 0 is deterministic, meaning that the highest probability response
     * is always selected.
     *
     * If this method is not set, the default value will be 0.2.
     *
     * @return The instance of the [TextRequest] with the updated [temperature].
     */
    public fun setTemperature(temperature: Double): TextRequest

    /**
     * Maximum number of tokens that can be generated in the response. A token is approximately four
     * characters. 100 tokens correspond to roughly 60-80 words. Specify a lower value for shorter
     * responses and a higher value for longer responses.
     *
     * If this method is not set, the default value will be 256.
     *
     * @return The instance of the [TextRequest] with the updated [maxTokens].
     */
    public fun setMaxTokens(maxTokens: Int): TextRequest

    /**
     * Top-K changes how the model selects tokens for output. A top-K of 1 means the next selected
     * token is the most probable among all tokens in the model's vocabulary (also called greedy decoding),
     * while a top-K of 3 means that the next token is selected from among the three most probable
     * tokens by using temperature.
     *
     * For each token selection step, the top-K tokens with the highest probabilities are sampled.
     * Then tokens are further filtered based on top-P with the final token selected using
     * temperature sampling.
     *
     * Specify a lower value for less random responses and a higher value for more random responses.
     *
     * If this method is not set, the default value will be 40.
     *
     * @return The instance of the [TextRequest] with the updated [topK].
     */
    public fun setTopK(topK: Int): TextRequest

    /**
     * Top-P changes how the model selects tokens for output. Tokens are selected from the most (see top-K)
     * to least probable until the sum of their probabilities equals the top-P value. For example,
     * if tokens A, B, and C have a probability of 0.3, 0.2, and 0.1 and the top-P value is 0.5,
     * then the model will select either A or B as the next token by using temperature and
     * excludes C as a candidate.
     *
     * Specify a lower value for less random responses and a higher value for more random responses.
     *
     * If this method is not set, the default value will be 0.8.
     *
     * @return The instance of the [TextRequest] with the updated [topP].
     */
    public fun setTopP(topP: Double): TextRequest

    /**
     * Execute text request.
     * @return one predicted completion of the given prompt.
     */
    public suspend fun execute(prompt: String): VertexResult<String>

    /**
     * Retrieve the predicted completion of the given [prompt], and passes it to the provided callback.
     *
     * @param callback The callback to receive the predicted completion of the given [prompt].
     */
    public fun execute(prompt: String, callback: VertexAI.Callback<String>)
}
