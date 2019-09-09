package com.szymongierszewski.xmlanalyzer.service;

import com.szymongierszewski.xmlanalyzer.exceptions.XmlContentException;
import com.szymongierszewski.xmlanalyzer.model.PostsDetails;
import com.szymongierszewski.xmlanalyzer.model.XmlDetails;

import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.Optional;

public class PostsDetailsService implements XmlDetailsService {

    private static final String POST_TAG = "row";
    private static final String CREATION_DATE_ATTRIBUTE = "CreationDate";
    private static final String SCORE_ATTRIBUTE = "Score";
    private static final String ACCEPTED_ANSWER_ID_ATTRIBUTE = "AcceptedAnswerId";

    private PostsDetails postsDetails;

    @Override
    public XmlDetails createXmlDetails(XMLStreamReader xmlStreamReader) throws XMLStreamException {
        postsDetails = new PostsDetails();
        Integer postsTotalScore = 0;
        while (xmlStreamReader.hasNext()) {
            xmlStreamReader.next();
            if (xmlStreamReader.isStartElement() && POST_TAG.equals(xmlStreamReader.getLocalName())) {
                LocalDateTime postDateTime = getPostDateTime(xmlStreamReader);
                setFirstPostDateTime(postDateTime);
                setLastPostDateTime(postDateTime);
                incrementTotalPosts();
                incrementTotalAcceptedPosts(xmlStreamReader);
                postsTotalScore += getPostScore(xmlStreamReader);
            }
        }
        setPostsAvgScore(postsTotalScore);
        return postsDetails;
    }

    private LocalDateTime getPostDateTime(XMLStreamReader xmlStreamReader) {
        try {
            return LocalDateTime.parse(xmlStreamReader.getAttributeValue(null, CREATION_DATE_ATTRIBUTE));
        } catch (NullPointerException | DateTimeParseException e) {
            throw new XmlContentException(String.format("Invalid CreationDate attribute value: '%s' encountered", xmlStreamReader.getAttributeValue(null, CREATION_DATE_ATTRIBUTE)), e);
        }
    }

    private void setFirstPostDateTime(LocalDateTime postDateTime) {
        if (!postsDetails.getFirstPostDateTime().isPresent() || postsDetails.getFirstPostDateTime().get().isAfter(postDateTime)) {
            postsDetails.setFirstPostDateTime(postDateTime);
        }
    }

    private void setLastPostDateTime(LocalDateTime postDateTime) {
        if (!postsDetails.getLastPostDateTime().isPresent() || postsDetails.getLastPostDateTime().get().isBefore(postDateTime)) {
            postsDetails.setLastPostDateTime(postDateTime);
        }
    }

    private void incrementTotalPosts() {
        Integer totalPosts = postsDetails.getTotalPosts().orElse(0);
        postsDetails.setTotalPosts(++totalPosts);
    }

    private void incrementTotalAcceptedPosts(XMLStreamReader xmlStreamReader) {
        if (xmlStreamReader.getAttributeValue(null, ACCEPTED_ANSWER_ID_ATTRIBUTE) != null) {
            Integer totalAcceptedPosts = postsDetails.getTotalAcceptedPosts().orElse(0);
            postsDetails.setTotalAcceptedPosts(++totalAcceptedPosts);
        }
    }

    private Integer getPostScore(XMLStreamReader xmlStreamReader) {
        try {
            return Integer.valueOf(xmlStreamReader.getAttributeValue(null, SCORE_ATTRIBUTE));
        } catch (NullPointerException | NumberFormatException e) {
            throw new XmlContentException(String.format("Invalid Score attribute value: '%s' encountered", xmlStreamReader.getAttributeValue(null, SCORE_ATTRIBUTE)), e);
        }
    }

    private void setPostsAvgScore(Integer scoreCounter) {
        Optional<Integer> totalPosts = postsDetails.getTotalPosts();
        if (totalPosts.isPresent()) {
            Double postsAvgScore = Double.valueOf(scoreCounter) / totalPosts.get();
            postsDetails.setPostsAvgScore(postsAvgScore);
        }
    }

}
