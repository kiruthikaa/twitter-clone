package com.intuit.craftdemotwitter.dao;

import com.intuit.craftdemotwitter.model.Tweet;
import com.intuit.craftdemotwitter.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TweetRepository extends JpaRepository<Tweet,Long> {
    /*@Query("select t from Tweet t where created_by_user_id =?1 order by updated_at desc")*/
    List<Tweet> findTweetsByCreatedBy(User createdBy);
}
