package com.github.ratel.services;

import com.github.ratel.entity.Comment;
import com.github.ratel.repositories.CommentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findCommentById(long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findCommentByProductId(long productId) {
        return commentRepository.findCommentByProductId(productId);
    }

    public List<Comment> findCommentByUserId(long userId) {
        return commentRepository.findCommentByUserId(userId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment saveCommentByProductId(Comment comment, long id) {
        comment.setProductId(id);
        return commentRepository.save(comment);
    }

    public void updateCommentByProductId(Comment comment, long id) {
        commentRepository.findById(comment.getCommentId()).orElseThrow(() -> new EntityExistsException("Comment not found"));
    }
}
