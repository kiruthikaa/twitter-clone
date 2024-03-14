package com.intuit.craftdemotwitter.response;

import com.intuit.craftdemotwitter.model.User;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.time.ZonedDateTime;

@Getter
@Setter
public class TweetResponse {
    private Long tweetId;

    private String tweetContent;

    private ZonedDateTime createdAt;

    private UserResponse createdBy;
}
