package com.innopolis.eventgo.service;

import com.innopolis.eventgo.db.entity.PostStatus;

import java.util.List;

public interface PostStatusService {
    List<PostStatus> findAll();
}
