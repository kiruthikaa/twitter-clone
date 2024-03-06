package com.intuit.craftdemotwitter.service;

import com.intuit.craftdemotwitter.response.UserResponse;

import java.util.List;

public interface UserService {
    public UserResponse getUserByName(String name);

    public UserResponse getUserByUserId(Long userId);


    public UserResponse addUser(UserResponse userResponse);

    public UserResponse update(UserResponse userResponse);

    public boolean addFollower(Long followerId, Long userId);

    public boolean removeFollower(Long followerId, Long userId);

    public List<UserResponse> getFollowers(Long userId);

    public List<UserResponse> getFollowings(Long userId);
}
