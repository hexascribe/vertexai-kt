package com.hexascribe.vertexai.service;

import com.hexascribe.vertexai.VertexAI;

import org.jetbrains.annotations.NotNull;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;

@Service
public class VertexService {

    private final VertexAI vertexAI;

    @Autowired
    public VertexService(VertexAI vertexAI) {
        this.vertexAI = vertexAI;
    }

    private static final int MAX_TOKENS = 512;

    public String getPrediction(String prompt) throws Exception {
        final CompletableFuture<String> future = new CompletableFuture<>();
        vertexAI.textRequest()
                .setMaxTokens(MAX_TOKENS)
                .execute(prompt, new CompletionCallbackResult<>(future));
        return future.get();
    }

    private record CompletionCallbackResult<T>(
            CompletableFuture<T> future
    ) implements VertexAI.Callback<T> {

        @Override
        public void onSuccess(@NotNull T result) {
            future.complete(result);
        }

        @Override
        public void onError(@NotNull Throwable throwable) {
            future.completeExceptionally(throwable);
        }
    }
}
