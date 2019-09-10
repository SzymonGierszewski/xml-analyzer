package com.szymongierszewski.xmlanalyzer.controller;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockserver.integration.ClientAndServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import static org.mockserver.integration.ClientAndServer.startClientAndServer;
import static org.mockserver.model.HttpRequest.request;
import static org.mockserver.model.HttpResponse.response;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class XmlAnalyzerControllerTest {

    private ClientAndServer mockServer;

    @Autowired
    private MockMvc mockMvc;

    @Before
    public void startMockServer() {
        mockServer = startClientAndServer(8081);
    }

    @After
    public void stopMockServer() {
        mockServer.stop();
    }

    @Test
    public void createXmlAnalysis_returnsXmlAnalysisWithPostsDetails_ifRequestIsValid() throws Exception {
        // given
        mockServer.when(request()
                .withMethod("GET")
                .withPath("/test")
        ).respond(response()
                .withBody(getInputStreamAsString(getClass().getResourceAsStream("/valid-posts-sample.xml"))));

        // when & then
        mockMvc.perform(post("/api/v1/analyzes/posts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"url\": \"http://localhost:8081/test\"}"))
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(jsonPath("$.id").exists())
                .andExpect(jsonPath("$.analysisTimestamp").exists())
                .andExpect(jsonPath("$.xmlDetails.firstPostDateTime").value("2015-07-14T18:39:27.757"))
                .andExpect(jsonPath("$.xmlDetails.lastPostDateTime").value("2015-07-14T20:05:50.737"))
                .andExpect(jsonPath("$.xmlDetails.totalPosts").value(4))
                .andExpect(jsonPath("$.xmlDetails.totalAcceptedPosts").value(1))
                .andExpect(jsonPath("$.xmlDetails.postsAvgScore").value(0.5D));

    }

    @Test
    public void createXmlAnalysis_returnsHttpStatus422_ifXmlSourceIsInvalid() throws Exception {
        // given
        mockServer.when(request()
                .withMethod("GET")
                .withPath("/test")
        ).respond(response()
                .withBody(getInputStreamAsString(getClass().getResourceAsStream("/invalid-posts_missing-Score-attribute.xml"))));

        // when & then
        mockMvc.perform(post("/api/v1/analyzes/posts")
                .contentType(MediaType.APPLICATION_JSON_UTF8)
                .content("{\"url\": \"http://localhost:8081/test\"}"))
                .andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.statusCode").value(422))
                .andExpect(jsonPath("$.statusName").value("Unprocessable Entity"));

    }

    private String getInputStreamAsString(InputStream inputStream) throws IOException {
        StringBuilder resultStringBuilder = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new InputStreamReader(inputStream))) {
            String line;
            while ((line = br.readLine()) != null) {
                resultStringBuilder.append(line).append("\n");
            }
        }
        return resultStringBuilder.toString();
    }
}