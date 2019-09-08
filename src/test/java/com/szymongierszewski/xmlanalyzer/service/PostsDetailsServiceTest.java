package com.szymongierszewski.xmlanalyzer.service;

import com.szymongierszewski.xmlanalyzer.exceptions.XmlContentException;
import com.szymongierszewski.xmlanalyzer.model.PostsDetails;
import com.szymongierszewski.xmlanalyzer.model.XmlDetails;
import org.junit.Test;

import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.io.InputStream;
import java.time.LocalDateTime;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class PostsDetailsServiceTest {

    private final PostsDetailsService testObj = new PostsDetailsService();
    private final XMLInputFactory givenXmlInputFactory = XMLInputFactory.newFactory();

    @Test
    public void createXmlDetails_returnsXmlDetails_ifXmlContentIsValid() throws XMLStreamException {
        // given
        XMLStreamReader givenXmlStreamReader = createXmlStreamReader("/valid-posts-sample.xml");

        // when
        PostsDetails result = (PostsDetails) testObj.createXmlDetails(givenXmlStreamReader);

        // then
        assertThat(result).isInstanceOf(XmlDetails.class);
        assertThat(result.getFirstPostDateTime()).isEqualTo(Optional.of(LocalDateTime.parse("2015-07-14T18:39:27.757")));
        assertThat(result.getLastPostDateTime()).isEqualTo(Optional.of(LocalDateTime.parse("2015-07-14T20:05:50.737")));
        assertThat(result.getTotalPosts()).isEqualTo(Optional.of(4));
        assertThat(result.getTotalAcceptedPosts()).isEqualTo(Optional.of(1));
        assertThat(result.getPostsAvgScore()).isEqualTo(Optional.of(0.5D));
    }

    @Test
    public void createXmlDetails_throwsXmlContentException_ifScoreAttributeIsMissing() throws XMLStreamException {
        // given
        XMLStreamReader givenXmlStreamReader = createXmlStreamReader("/invalid-posts_missing-Score-attribute.xml");

        // when & then
        assertThatThrownBy(() -> testObj.createXmlDetails(givenXmlStreamReader))
                .isInstanceOf(XmlContentException.class);
    }

    @Test
    public void createXmlDetails_throwsXmlContentException_ifScoreAttributeValueParsingFails() throws XMLStreamException {
        // given
        XMLStreamReader givenXmlStreamReader = createXmlStreamReader("/invalid-posts_wrong-Score-attribute-value.xml");

        // when & then
        assertThatThrownBy(() -> testObj.createXmlDetails(givenXmlStreamReader))
                .isInstanceOf(XmlContentException.class);
    }

    @Test
    public void createXmlDetails_throwsXmlContentException_ifCreationDateIsMissing() throws XMLStreamException {
        // given
        XMLStreamReader givenXmlStreamReader = createXmlStreamReader("/invalid-posts_missing-CreationDate-attribute.xml");

        // when & then
        assertThatThrownBy(() -> testObj.createXmlDetails(givenXmlStreamReader))
                .isInstanceOf(XmlContentException.class);
    }

    @Test
    public void createXmlDetails_throwsXmlContentException_ifCreationDateValueParsingFails() throws XMLStreamException {
        // given
        XMLStreamReader givenXmlStreamReader = createXmlStreamReader("/invalid-posts_wrong-CreationDate-attribute-value.xml");

        // when & then
        assertThatThrownBy(() -> testObj.createXmlDetails(givenXmlStreamReader))
                .isInstanceOf(XmlContentException.class);
    }

    private XMLStreamReader createXmlStreamReader(String resource) throws XMLStreamException {
        InputStream givenInputStream = getClass().getResourceAsStream(resource);
        return givenXmlInputFactory.createXMLStreamReader(givenInputStream);
    }
}