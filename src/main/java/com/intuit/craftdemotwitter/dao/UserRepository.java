package com.intuit.craftdemotwitter.dao;

import com.intuit.craftdemotwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByName(String name);
}
