package com.intuit.craftdemotwitter.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import com.intuit.craftdemotwitter.response.UserResponse;
import com.intuit.craftdemotwitter.service.UserService;

import java.util.List;

@RestController
@RequestMapping("/users")
@Slf4j
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping("/userByName/{name}")
    public ResponseEntity<UserResponse> getUserByName(
            @PathVariable("name") String name){
        UserResponse userResponse;
        userResponse = userService.getUserByName(name);
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }

    @GetMapping("/userById/{userId}")
    public ResponseEntity<UserResponse> getUserByID(
            @PathVariable("userId") String userId){
        UserResponse userResponse;
        userResponse = userService.getUserByUserId(Long.valueOf(userId));
        return new ResponseEntity<>(userResponse, HttpStatus.OK);
    }
    @PostMapping
    public ResponseEntity<UserResponse> createUser(@RequestBody UserResponse userResponse) {
        UserResponse user = userService.addUser(userResponse);
        return new ResponseEntity<>(user, HttpStatus.CREATED);
    }

    @PatchMapping
    public UserResponse updateUser(@RequestBody UserResponse userResponse) {
        return userService.update(userResponse);
    }

    @PutMapping("/follow/{userId}")
    public ResponseEntity<HttpStatus> addFollower(@PathVariable Long userId,@RequestBody UserResponse userResponse) {
        userService.addFollower(userResponse.getUserId(),userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @DeleteMapping("/unfollow/{userId}")
    public ResponseEntity<HttpStatus> removeFollower(
            @PathVariable("userId") Long userId,@RequestBody UserResponse userResponse) {
        userService.removeFollower(userResponse.getUserId(),userId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("/followers/{userId}")
    public List<UserResponse> getFollowers(@PathVariable("userId") Long userId) {
        return userService.getFollowers(userId);
    }

    @GetMapping("/followings/{userId}")
    public List<UserResponse> getFollowings(@PathVariable("userId") Long userId) {
        return userService.getFollowings(userId);
    }
}
