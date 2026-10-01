package com.helarras.codingplatform.execution.internal;

import org.springframework.http.MediaType;
import org.springframework.http.client.JdkClientHttpRequestFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestClient;

import java.net.http.HttpClient;

@Component
public class PistonCodeExecutor implements ICodeExecutor {
    private final RestClient client;

    public PistonCodeExecutor() {
        HttpClient jdkClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_1_1)
                .build();

        client = RestClient.builder()
                .baseUrl("http://localhost:2000")
                .defaultHeader("Accept", "application/json")
                .requestFactory(new JdkClientHttpRequestFactory(jdkClient))
                .build();
    }

    @Override
    public ExecutionResult run(String language, String sourceCode, String stdInput) {
        var request = setupRequest(language, sourceCode, stdInput);
        PistonResponse response;
        try {
            response = client.post()
                    .uri("/api/v2/execute")
                    .contentType(MediaType.APPLICATION_JSON)
                    .body(request)
                    .retrieve()
                    .body(PistonResponse.class);
        } catch (HttpClientErrorException e) {
            return ExecutionResult.failure(e.getResponseBodyAsString());
        }

        if (response == null || response.run() == null)
            return ExecutionResult.failure("Unknown error");

        return ExecutionResult.builder()
                .code(response.run().code())
                .output(response.run().output())
                .error(response.run().stderr())
                .build();
    }


    private PistonRequest setupRequest(String language, String sourceCode, String stdInput) {
        var files = new PistonRequest.PistonFile[]{new PistonRequest.PistonFile(null, sourceCode)};
        return PistonRequest.builder()
                .language(language)
                .version("*")
                .stdin(stdInput)
                .files(files)
                .build();
    }
}