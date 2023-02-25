package com.tahafurkan.sandbox.blogapplication.repository;

import com.tahafurkan.sandbox.blogapplication.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
}
