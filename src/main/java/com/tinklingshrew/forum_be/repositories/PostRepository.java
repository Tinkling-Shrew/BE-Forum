package com.tinklingshrew.forum_be.repositories;

import com.tinklingshrew.forum_be.entities.Post;
import com.tinklingshrew.forum_be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Set;

public interface PostRepository extends JpaRepository<Post,Long> {
    Set<Post> findPostsByUser(User userEntity);

    void deletePostById(Long postId);
}
