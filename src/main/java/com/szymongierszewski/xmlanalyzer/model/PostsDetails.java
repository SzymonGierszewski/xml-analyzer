package com.szymongierszewski.xmlanalyzer.model;

import java.time.LocalDateTime;
import java.util.Optional;

public class PostsDetails extends XmlDetails {

    private LocalDateTime firstPostDateTime;
    private LocalDateTime lastPostDateTime;
    private Integer totalPosts;
    private Integer totalAcceptedPosts;
    private Double postsAvgScore;

    public Optional<LocalDateTime> getFirstPostDateTime() {
        return Optional.ofNullable(firstPostDateTime);
    }

    public void setFirstPostDateTime(LocalDateTime firstPostDateTime) {
        this.firstPostDateTime = firstPostDateTime;
    }

    public Optional<LocalDateTime> getLastPostDateTime() {
        return Optional.ofNullable(lastPostDateTime);
    }

    public void setLastPostDateTime(LocalDateTime lastPostDateTime) {
        this.lastPostDateTime = lastPostDateTime;
    }

    public Optional<Integer> getTotalPosts() {
        return Optional.ofNullable(totalPosts);
    }

    public void setTotalPosts(Integer totalPosts) {
        this.totalPosts = totalPosts;
    }

    public Optional<Integer> getTotalAcceptedPosts() {
        return Optional.ofNullable(totalAcceptedPosts);
    }

    public void setTotalAcceptedPosts(Integer totalAcceptedPosts) {
        this.totalAcceptedPosts = totalAcceptedPosts;
    }

    public Optional<Double> getPostsAvgScore() {
        return Optional.ofNullable(postsAvgScore);
    }

    public void setPostsAvgScore(Double postsAvgScore) {
        this.postsAvgScore = postsAvgScore;
    }
}
