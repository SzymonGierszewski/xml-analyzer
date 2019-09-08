package com.szymongierszewski.xmlanalyzer.model;

import java.time.LocalDateTime;

public class PostsDetails extends XmlDetails {

    private LocalDateTime firstPostDateTime;
    private LocalDateTime lastPostDateTime;
    private Integer totalPosts;
    private Integer totalAcceptedPosts;
    private Double postsAvgScore;

    public LocalDateTime getFirstPostDateTime() {
        return firstPostDateTime;
    }

    public void setFirstPostDateTime(LocalDateTime firstPostDateTime) {
        this.firstPostDateTime = firstPostDateTime;
    }

    public LocalDateTime getLastPostDateTime() {
        return lastPostDateTime;
    }

    public void setLastPostDateTime(LocalDateTime lastPostDateTime) {
        this.lastPostDateTime = lastPostDateTime;
    }

    public Integer getTotalPosts() {
        return totalPosts;
    }

    public void setTotalPosts(Integer totalPosts) {
        this.totalPosts = totalPosts;
    }

    public Integer getTotalAcceptedPosts() {
        return totalAcceptedPosts;
    }

    public void setTotalAcceptedPosts(Integer totalAcceptedPosts) {
        this.totalAcceptedPosts = totalAcceptedPosts;
    }

    public Double getPostsAvgScore() {
        return postsAvgScore;
    }

    public void setPostsAvgScore(Double postsAvgScore) {
        this.postsAvgScore = postsAvgScore;
    }
}
