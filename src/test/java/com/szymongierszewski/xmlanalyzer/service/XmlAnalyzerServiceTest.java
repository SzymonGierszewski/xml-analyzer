package com.szymongierszewski.xmlanalyzer.service;

import com.szymongierszewski.xmlanalyzer.exceptions.XmlContentException;
import com.szymongierszewski.xmlanalyzer.model.PostsDetails;
import com.szymongierszewski.xmlanalyzer.model.XmlAnalysis;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLStreamHandler;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class XmlAnalyzerServiceTest {

    private final XmlAnalyzerService testObj = new XmlAnalyzerService();
    private final XmlDetailsService xmlDetailsService = new PostsDetailsService();
    private final URLConnection mockUrlConnection = Mockito.mock(URLConnection.class);
    private URL givenUrl;


    @Before
    public void init() throws MalformedURLException {
        URLStreamHandler stubUrlStreamHandler = new URLStreamHandler() {
            @Override
            protected URLConnection openConnection(URL url) throws IOException {
                return mockUrlConnection;
            }
        };

        givenUrl = new URL("http", "localhost", 8080, "test", stubUrlStreamHandler);
    }

    @Test
    public void createXmlAnalysis_returnsXmlAnalysis_ifXmlContentIsValid() throws IOException {
        // given
        Mockito.when(mockUrlConnection.getInputStream()).thenReturn(getClass().getResourceAsStream("/valid-posts-sample.xml"));

        // when
        XmlAnalysis result = testObj.createXmlAnalysis(givenUrl, xmlDetailsService);

        // then
        assertThat(result.getXmlDetails())
                .isNotNull()
                .isInstanceOf(PostsDetails.class)
                .hasNoNullFieldsOrPropertiesExcept("id");
        Mockito.verify(mockUrlConnection, Mockito.times(1)).getInputStream();

    }

    @Test
    public void createXmlAnalysis_throwsXmlContentException_ifXmlContentIsInvalid() throws IOException {
        // given
        Mockito.when(mockUrlConnection.getInputStream()).thenReturn(new ByteArrayInputStream("".getBytes()));

        // when & then
        assertThatThrownBy(() -> testObj.createXmlAnalysis(givenUrl, xmlDetailsService))
                .isInstanceOf(XmlContentException.class);

    }

}