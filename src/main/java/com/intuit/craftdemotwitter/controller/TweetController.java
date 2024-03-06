package com.intuit.craftdemotwitter.controller;

import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.service.TweetService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tweets")
@Slf4j
public class TweetController {

    @Autowired
    private TweetService tweetService;

    @GetMapping("/getTweetById/{tweetId}")
    public ResponseEntity<TweetResponse> getTweetByID(@PathVariable("tweetId") Long tweetId) {
        TweetResponse tweetResponse = tweetService.getTweetByID(tweetId);
        return new ResponseEntity<>(tweetResponse, HttpStatus.OK);
    }

    @GetMapping("/getTweetByUser/{userId}")
    public ResponseEntity<TweetResponse> getTweetByUser(@PathVariable("userId") Long userId) {
        TweetResponse tweetResponse = tweetService.getTweetByUser(userId);
        return new ResponseEntity<>(tweetResponse, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<TweetResponse> addTweet(@RequestBody TweetResponse tweetResponse) {
        TweetResponse tweetR = tweetService.addTweet(tweetResponse);
        return new ResponseEntity<>(tweetR, HttpStatus.OK);
    }

    @DeleteMapping("/{tweetId}")
    public ResponseEntity<HttpStatus> deleteTweet(
            @PathVariable("tweetId") Long tweetId,@RequestParam Long userId) {
        tweetService.deleteTweet(tweetId,userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
