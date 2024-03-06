package com.intuit.craftdemotwitter.service.impl;

import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.service.TweetService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class TweetServiceImpl implements TweetService {


    @Override
    public TweetResponse getTweetByID(Long tweetId) {
        return null;
    }

    @Override
    public TweetResponse getTweetByUser(Long userId) {
        return null;
    }

    @Override
    public TweetResponse addTweet(TweetResponse postModel) {
        return null;
    }

    @Override
    public boolean deleteTweet(Long tweetId, Long userId) {
        return false;
    }
}
