package com.intuit.craftdemotwitter.utils.mapper;

import com.intuit.craftdemotwitter.model.User;
import org.springframework.beans.BeanUtils;
import org.springframework.context.annotation.ComponentScan;
import com.intuit.craftdemotwitter.response.UserResponse;
import org.springframework.stereotype.Component;

@Component("UserMapper")
public class UserMapper implements Mapper<User,UserResponse>{
    @Override
    public UserResponse transform(User user) {
        var userR = new UserResponse();
        BeanUtils.copyProperties(user, userR);
        return userR;
    }

    @Override
    public User transformBack(UserResponse userModel) {
        var user = new User();
        BeanUtils.copyProperties(userModel, user);
        return user;
    }
}
