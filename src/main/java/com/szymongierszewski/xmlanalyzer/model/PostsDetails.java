package com.szymongierszewski.xmlanalyzer.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.time.LocalDateTime;
import java.util.Optional;

@Entity
@Table(name = "posts_details")
public class PostsDetails extends XmlDetails {

    @Column(name = "first_post")
    private LocalDateTime firstPostDateTime;
    @Column(name = "last_post")
    private LocalDateTime lastPostDateTime;
    @Column(name = "total_posts")
    private Integer totalPosts;
    @Column(name = "total_accepted_posts")
    private Integer totalAcceptedPosts;
    @Column(name = "average_score")
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
