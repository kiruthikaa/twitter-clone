package com.intuit.craftdemotwitter.response;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserResponse {
    private Long userId;
    private String name;
    private String email;

}
