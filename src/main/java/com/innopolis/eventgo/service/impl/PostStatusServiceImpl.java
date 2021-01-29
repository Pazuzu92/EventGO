package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.PostStatus;
import com.innopolis.eventgo.db.repository.PostStatusDAO;
import com.innopolis.eventgo.service.PostStatusService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PostStatusServiceImpl implements PostStatusService {

    private final PostStatusDAO postStatusDAO;

    public PostStatusServiceImpl(PostStatusDAO postStatusDAO) {
        this.postStatusDAO = postStatusDAO;
    }


    @Override
    public List<PostStatus> findAll() {
        return postStatusDAO.findAll();
    }
}
