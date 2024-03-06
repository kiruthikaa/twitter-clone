package com.intuit.craftdemotwitter.service;

import com.intuit.craftdemotwitter.response.TweetResponse;

import java.util.List;

public interface TweetService {

    public TweetResponse getTweetByID(Long tweetId);

    public TweetResponse getTweetByUser(Long userId);

    public TweetResponse addTweet(TweetResponse postModel);

    public boolean deleteTweet(Long tweetId, Long userId);
}
