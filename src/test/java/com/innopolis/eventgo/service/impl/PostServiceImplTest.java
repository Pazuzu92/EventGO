package com.innopolis.eventgo.service.impl;

import com.innopolis.eventgo.db.entity.Post;
import com.innopolis.eventgo.db.repository.*;
import com.innopolis.eventgo.dto.PostDto;
import com.innopolis.eventgo.exceptions.NotFoundException;
import com.innopolis.eventgo.mappers.PostMapper;
import com.innopolis.eventgo.service.PostService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PostServiceImplTest {

    private PostService postService;
    private PostRepository postRepository;

    @BeforeEach
    public void setUp() {
        postRepository = mock(PostRepository.class);
        postService = new PostServiceImpl(postRepository,
                mock(PostDAO.class), mock(CityDAO.class), new PostMapper(),
                mock(CategoryDAO.class),
                mock(PostStatusDAO.class));
    }

    @Test
    void getPost() {
        Long id = -1L;
        when(postRepository.getPost(id)).thenReturn(null);
        Assertions.assertThrows(NotFoundException.class, () -> {
            postService.getPost(id);
        });
    }

    @Test
    void createPost() {
    }

    @Test
    void updatePost() {
    }
}