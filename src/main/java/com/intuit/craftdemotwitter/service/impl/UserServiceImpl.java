package com.intuit.craftdemotwitter.service.impl;

import com.intuit.craftdemotwitter.dao.UserRepository;
import com.intuit.craftdemotwitter.model.User;
import com.intuit.craftdemotwitter.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.intuit.craftdemotwitter.response.UserResponse;
import com.intuit.craftdemotwitter.utils.mapper.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Qualifier("UserMapper")
    private Mapper<User, UserResponse> userMapper;
    @Override
    public UserResponse getUserByName(String name) {
        return userMapper.transform(userRepository.findByName(name));
    }

    @Override
    public  UserResponse getUserByUserId(Long userId) {
        User user =  userRepository.findById(userId).orElse(new User());
        return userMapper.transform(user);
    }

    @Override
    @Transactional
    public UserResponse addUser(UserResponse userResponse) {
        User user = userMapper.transformBack(userResponse);
        return userMapper.transform(userRepository.save(user));
    }

    @Override
    @Transactional
    public UserResponse update(UserResponse userResponse) {
        User user = userMapper.transformBack(userResponse);
        return userMapper.transform(userRepository.save(user));
    }

    @Override
    @Transactional
    public boolean addFollower(Long followerId, Long userId,boolean isSelf) {
        User followee = userRepository.findById(userId).orElse(null);
        User follower = userRepository.findById(followerId).orElse(null);

        if(Objects.nonNull(followee) && Objects.nonNull(follower))
        {
            if(isSelf || follower.getUserId() != followee.getUserId())
            {
                followee.setFollower(followerId);
                follower.setFollowing(userId);
                userRepository.save(follower);
                userRepository.save(followee);
                return true;
            }
        }
        return false;
    }

    @Override
    @Transactional
    public boolean removeFollower(Long followerId, Long userId) {
        User followee = userRepository.findById(userId).orElse(null);
        User follower = userRepository.findById(followerId).orElse(null);
        if(Objects.nonNull(followee) && Objects.nonNull(follower))
        {
            if(follower.getUserId() != followee.getUserId())
            {
                followee.removeFollower(followerId);
                follower.removeFollowing(userId);
                userRepository.save(follower);
                userRepository.save(followee);
                return true;
            }
        }
        return false;
    }

    @Override
    public List<UserResponse> getFollowers(Long userId) {
        List<UserResponse> followers = new ArrayList<>();
        User user = userRepository.getById(userId);
        if(Objects.nonNull(user))
        {
            List<User> users = userRepository.findAllById(user.getFollower().stream()
                    .collect(Collectors.toList()));
            if(users.size()>0)
            {
                Optional.ofNullable(users)
                    .ifPresent(
                            usersList ->
                                    usersList.forEach(eachUser -> followers.add(userMapper.transform(eachUser))));
                return followers;
            }
        }
        return null;
    }

    @Override
    public List<UserResponse> getFollowings(Long userId) {
        List<UserResponse> followings = new ArrayList<>();
        User user = userRepository.getById(userId);
        List<User> users = userRepository.findAllById(user.getFollowing().stream()
                .collect(Collectors.toList()));
        Optional.ofNullable(users)
                .ifPresent(
                        usersList ->
                                usersList.forEach(eachUser -> followings.add(userMapper.transform(eachUser))));
        return followings;
    }
}
