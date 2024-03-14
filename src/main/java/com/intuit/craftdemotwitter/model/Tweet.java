package com.intuit.craftdemotwitter.model;


import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.Objects;


@Entity
@Table(name = "tweet")
@Setter
@Getter
public class Tweet implements Comparable<Tweet> {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long tweetId;

    @NotNull
    @Size(max = 280)
    @Column(name = "tweet_content", length = 280, nullable = false)
    private String tweetContent;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @ManyToOne(optional = false)
    @NotNull
    private User createdBy;

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Tweet tweet = (Tweet) o;
        if (tweet.getTweetId() == null || getTweetId() == null) {
            return false;
        }
        return Objects.equals(getTweetId(), tweet.getTweetId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getTweetId());
    }

    @Override
    public int compareTo(Tweet o) {
        return getCreatedAt().compareTo(o.getCreatedAt());
    }
}
