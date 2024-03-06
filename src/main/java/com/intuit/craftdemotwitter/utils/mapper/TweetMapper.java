package com.intuit.craftdemotwitter.utils.mapper;

import com.intuit.craftdemotwitter.model.Tweet;
import com.intuit.craftdemotwitter.model.User;
import com.intuit.craftdemotwitter.response.TweetResponse;
import com.intuit.craftdemotwitter.response.UserResponse;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

@Component("TweetMapper")
public class TweetMapper implements Mapper<Tweet, TweetResponse> {
    @Override
    public TweetResponse transform(Tweet source) {
        var tweetR = new TweetResponse();
        BeanUtils.copyProperties(source, tweetR);
        return tweetR;
    }

    @Override
    public Tweet transformBack(TweetResponse source) {
        var tweet = new Tweet();
        BeanUtils.copyProperties(source, tweet);
        return tweet;
    }
}
