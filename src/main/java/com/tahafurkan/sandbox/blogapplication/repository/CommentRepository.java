package com.tahafurkan.sandbox.blogapplication.repository;

import com.tahafurkan.sandbox.blogapplication.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long> {
}



