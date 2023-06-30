package com.hexascribe.vertexai.config;

import com.hexascribe.vertexai.VertexAI;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;

@Configuration
public class VertexConfig {

    @Value("${projectId}")
    private String projectId;

    @Value("${accessToken}")
    private String accessToken;

    @Bean
    public VertexAI provideVertex() throws IOException {
        return new VertexAI.Builder()
                .setProjectId(projectId)
                .setAccessToken(accessToken)
                .build();
    }
}
