package com.tinklingshrew.forum_be.repositories;

import com.tinklingshrew.forum_be.entities.Comment;
import com.tinklingshrew.forum_be.entities.Post;
import com.tinklingshrew.forum_be.entities.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;
import java.util.Set;

public interface CommentRepository extends JpaRepository<Comment,Long> {
    Set<Comment> findCommentsByPost(Post postEntity);

    Set<Comment> findCommentsByUser(User userEntity);

    void deleteCommentById(Long id);
}
