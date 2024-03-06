package com.intuit.craftdemotwitter.response;

import com.intuit.craftdemotwitter.model.User;
import lombok.Data;

import java.time.ZonedDateTime;

@Data
public class TweetResponse {
    private Long tweetId;

    private String tweetContent;

    private ZonedDateTime createdAt;

    private User createdBy;
}
