package com.intuit.craftdemotwitter.controller;

import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.service.FeedService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/feed")
@Slf4j
public class FeedController {

    @Autowired
    private FeedService feedService;

    @GetMapping("/getTweetByUser/{userId}")
    public ResponseEntity<List<TweetResponse>> getFeedUser(@PathVariable("userId") Long userId) {
        List<TweetResponse> tweetResponses = feedService.getTweetFeed(userId);
        return new ResponseEntity<>(tweetResponses, HttpStatus.OK);
    }
}
