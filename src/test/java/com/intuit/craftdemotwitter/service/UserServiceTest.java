package com.intuit.craftdemotwitter.service;

import com.intuit.craftdemotwitter.dao.UserRepository;
import com.intuit.craftdemotwitter.model.User;
import com.intuit.craftdemotwitter.response.UserResponse;
import com.intuit.craftdemotwitter.service.impl.UserServiceImpl;
import com.intuit.craftdemotwitter.utils.mapper.Mapper;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.ZonedDateTime;
import java.util.Optional;

import static org.mockito.BDDMockito.given;

@SpringBootTest
public class UserServiceTest {

    @Mock
    private UserRepository userRepository;
    @InjectMocks
    private UserServiceImpl userService;

    @Mock
    @Qualifier("UserMapper")
    private Mapper<User, UserResponse> userMapper;

    @Test
    void test_getUserById()
    {
        User currUser = new User();
        currUser.setUserId(1L);
        currUser.setName("Test_user1");
        currUser.setEmail("test_user1@gmail.com");
        currUser.setCreatedAt(ZonedDateTime.now());
        Mockito.when(userRepository.findByName("Test_user1")).thenReturn(currUser);
        Mockito.when(userMapper.transform(currUser)).thenReturn(
                new UserResponse(1L,"Test_user1","test_user1@gmail.com"));
        UserResponse actualUser = userService.getUserByName("Test_user1");
        Assert.assertNotNull(actualUser);
        Assert.assertTrue(actualUser.getEmail().compareTo("test_user1@gmail.com")==0);
    }

    @Test
    void add_follower_nonSelf_success(){
     Long followeeId = 1L;
     Long followerId = 2L;
     User currUser1 = new User();
     currUser1.setUserId(1L);
     currUser1.setName("Test_user1");
     currUser1.setEmail("test_user1@gmail.com");
     currUser1.setCreatedAt(ZonedDateTime.now());
     Mockito.when(userRepository.findById(followeeId)).thenReturn(Optional.of(currUser1));
     User currUser2 = new User();
     currUser2.setUserId(2L);
     currUser2.setName("Test_user2");
     currUser2.setEmail("test_user2@gmail.com");
     currUser2.setCreatedAt(ZonedDateTime.now());
     Mockito.when(userRepository.findById(followerId)).thenReturn(Optional.of(currUser2));
     boolean result = userService.addFollower(followerId,followeeId,false);
     Assert.assertTrue(result);

    }

    @Test
    void add_follower_nonSelf_failure(){
        Long followeeId = 1L;
        User currUser1 = new User();
        currUser1.setUserId(1L);
        currUser1.setName("Test_user1");
        currUser1.setEmail("test_user1@gmail.com");
        currUser1.setCreatedAt(ZonedDateTime.now());
        Mockito.when(userRepository.findById(followeeId)).thenReturn(Optional.of(currUser1));
        boolean result = userService.addFollower(followeeId,followeeId,false);
        Assert.assertFalse(result);
    }

    @Test
    void add_follower_Self_success(){
        Long followeeId = 1L;
        User currUser1 = new User();
        currUser1.setUserId(1L);
        currUser1.setName("Test_user1");
        currUser1.setEmail("test_user1@gmail.com");
        currUser1.setCreatedAt(ZonedDateTime.now());
        Mockito.when(userRepository.findById(followeeId)).thenReturn(Optional.of(currUser1));
        boolean result = userService.addFollower(followeeId,followeeId,true);
        Assert.assertTrue(result);
    }
}
