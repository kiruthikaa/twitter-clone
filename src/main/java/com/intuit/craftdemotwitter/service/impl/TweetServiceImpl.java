package com.intuit.craftdemotwitter.service.impl;

import com.intuit.craftdemotwitter.dao.TweetRepository;
import com.intuit.craftdemotwitter.model.Tweet;
import com.intuit.craftdemotwitter.model.User;
import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.response.UserResponse;
import com.intuit.craftdemotwitter.service.TweetService;
import com.intuit.craftdemotwitter.service.UserService;
import com.intuit.craftdemotwitter.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Service
public class TweetServiceImpl implements TweetService {

    @Autowired
    private TweetRepository tweetRepository;

    @Autowired
    private UserService userService;

    @Autowired
    @Qualifier("TweetMapper")
    private Mapper<Tweet, TweetResponse> tweetMapper;

    @Autowired
    @Qualifier("UserMapper")
    private Mapper<User, UserResponse> userMapper;



    @Override
    public TweetResponse getTweetByID(Long tweetId) {
        var tweet = tweetRepository.findById(tweetId);
        if (tweet.isPresent()) return tweetMapper.transform(tweet.get());
        throw new RuntimeException("Tweet id is invalid"); //TODO: Replace exception handling
    }

    @Override
    public List<TweetResponse> getTweetByUser(Long userId) {
        User user = userMapper.transformBack(userService.getUserByUserId(userId));
        List<Tweet> tweets = tweetRepository.findTweetsByCreatedBy(user);
        List<TweetResponse> tweetResponses = tweets.stream().map(t -> tweetMapper.transform(t)).collect(Collectors.toList());
        return tweetResponses;
    }

    @Override
    @Transactional
    public TweetResponse addTweet(TweetResponse tweetResponse) {
        Tweet tweet = tweetMapper.transformBack(tweetResponse);
        User currUser = tweet.getCreatedBy();
        Long currUserId = currUser.getUserId();
        userService.addFollower(currUserId,currUserId,true);
        return tweetMapper.transform(tweetRepository.save(tweet));
    }

    @Override
    @Transactional
    public boolean deleteTweet(Long tweetId, Long userId) {
        Tweet tweet = tweetMapper.transformBack(getTweetByID(tweetId));
        if(Objects.nonNull(tweet))
        {
            tweetRepository.delete(tweet);
            return true;
        }
        return false;
    }

}
