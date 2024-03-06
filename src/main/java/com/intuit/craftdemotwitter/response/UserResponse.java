package com.intuit.craftdemotwitter.response;

import lombok.Data;

@Data
public class UserResponse {
    private Long userId;
    private String name;
    private String email;

}
