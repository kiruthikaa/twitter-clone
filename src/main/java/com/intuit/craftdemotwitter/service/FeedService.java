package com.intuit.craftdemotwitter.service;

import com.intuit.craftdemotwitter.response.TweetResponse;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface FeedService {
    public List<TweetResponse> getTweetFeed(Long userId);
}
