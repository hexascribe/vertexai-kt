package com.hexascribe.vertexai.controller;

import com.hexascribe.vertexai.service.VertexService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/vertex")
public class VertexController {

    private final VertexService vertexService;

    @Autowired
    public VertexController(VertexService vertexService) {
        this.vertexService = vertexService;
    }

    @GetMapping("prediction")
    public ResponseEntity<String> getPrediction(@RequestParam(value = "prompt") String prompt
    ) throws Exception {
        String result = vertexService.getPrediction(prompt);
        return ResponseEntity.ok(result);
    }
}
