package com.example.antrasdarbas.repos;

import com.example.antrasdarbas.model.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRep extends JpaRepository<Comment, Integer> {
}
