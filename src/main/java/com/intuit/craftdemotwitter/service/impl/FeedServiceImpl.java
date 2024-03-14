package com.intuit.craftdemotwitter.service.impl;

import com.intuit.craftdemotwitter.dao.UserRepository;
import com.intuit.craftdemotwitter.model.Tweet;
import com.intuit.craftdemotwitter.model.User;
import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.response.UserResponse;
import com.intuit.craftdemotwitter.service.FeedService;
import com.intuit.craftdemotwitter.service.TweetService;
import com.intuit.craftdemotwitter.service.UserService;
import com.intuit.craftdemotwitter.utils.mapper.Mapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.stream.Collectors;

@Service
public class FeedServiceImpl implements FeedService {
    @Autowired
    private UserService userService;

    @Autowired
    private TweetService tweetService;

    @Autowired
    @Qualifier("UserMapper")
    private Mapper<User, UserResponse> userMapper;

    @Autowired
    @Qualifier("TweetMapper")
    private Mapper<Tweet, TweetResponse> tweetMapper;

    @Value("${feed.tweet.limit}")
    private Integer feedTweetLimit;


    @Override
    public List<TweetResponse> getTweetFeed(Long userId) {
        List<User> followings = userService.getFollowings(userId)
                .stream().map(t -> userMapper.transformBack(t)).collect(Collectors.toList());
        PriorityQueue<Tweet> pq = new PriorityQueue<>((a, b)-> a.compareTo(b));
        for(User u : followings)
        {
            List<Tweet> twwetsList = tweetService.getTweetByUser(u.getUserId()).stream().map(t -> tweetMapper.transformBack(t)).collect(Collectors.toList());
            if(twwetsList != null)
            {
                int start = 0;
                if(twwetsList.size()>feedTweetLimit)
                    start = twwetsList.size()-feedTweetLimit;
                for( int i = start;i<twwetsList.size();i++)
                {
                    pq.add(twwetsList.get(i));
                    if(pq.size()>feedTweetLimit)
                        pq.poll();
                }
            }
        }
        List<TweetResponse> result = new ArrayList<>();
        while(!pq.isEmpty())
        {
            //add element at index 0 so that twwets are from most recent to least recent
            result.add(0,tweetMapper.transform(pq.poll()));
        }
        return result;
    }
}
