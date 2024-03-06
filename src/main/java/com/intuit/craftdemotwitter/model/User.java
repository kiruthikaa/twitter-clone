package com.intuit.craftdemotwitter.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.ZonedDateTime;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "user")
@Data
public class User {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    @NotNull
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "email", nullable = false)
    private String email;

    @CreationTimestamp
    @Column(name = "created_at", nullable = false)
    private ZonedDateTime createdAt;

    @UpdateTimestamp
    @Column(name = "updated_at", nullable = false)
    private ZonedDateTime updatedAt;

    @OneToMany(mappedBy = "createdBy")
    private Set<Tweet> tweets = new HashSet<>();

    @ElementCollection
    private Set<Long> following = new HashSet<>();

    @ElementCollection
    private Set<Long> follower = new HashSet<>();

    public User addTweet(Tweet tweet) {
        this.tweets.add(tweet);
        tweet.setCreatedBy(this);
        return this;
    }

    public User removeTweet(Tweet tweet) {
        this.tweets.remove(tweet);
        tweet.setCreatedBy(null);
        return this;
    }

    public void setFollower(Long userId) {
        follower.add(userId);
    }

    public void setFollowing(Long userId) {
        following.add(userId);
    }

    public void removeFollower(Long userId) {
        follower.remove(userId);
    }

    public void removeFollowing(Long userId) {
        following.remove(userId);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        if (user.getUserId() == null || getUserId() == null) {
            return false;
        }
        return Objects.equals(getUserId(), user.getUserId());
    }

    @Override
    public int hashCode() {
        return Objects.hashCode(getUserId());
    }
}
