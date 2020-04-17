package com.example.financepeer.server;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.financepeer.model.UserPost;

import java.util.List;

@Dao
public interface DataDao {

    @Insert
    void insert(UserPost userPost);

    @Query("SELECT * FROM UserPost")
    List<UserPost> getAll();

    @Insert
    void insertAll(List<UserPost> userPosts);
}
