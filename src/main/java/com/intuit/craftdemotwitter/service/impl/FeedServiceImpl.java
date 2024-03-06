package com.intuit.craftdemotwitter.service.impl;

import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.service.FeedService;
import org.springframework.http.ResponseEntity;

import java.util.List;

public class FeedServiceImpl implements FeedService {
    @Override
    public List<TweetResponse> getTweetFeed(Long userId) {
        return null;
    }
}
