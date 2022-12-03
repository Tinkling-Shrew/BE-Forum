package com.tinklingshrew.forum_be.repositories;

import com.tinklingshrew.forum_be.entities.Post;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Post,Long> {
}
