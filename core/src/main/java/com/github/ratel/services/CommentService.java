package com.github.ratel.services;

import com.github.ratel.entity.Comment;
import com.github.ratel.repositories.CommentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommentService {

    @Autowired
    private CommentRepository commentRepository;

    public List<Comment> findCommentByProductId(long productId) {
        return this.commentRepository.findCommentByProductId(productId);
    }


    public Comment saveCommentByProductId(Comment comment, long productId) {
        comment.setProductId(productId);
        return this.commentRepository.save(comment);
    }

    public void updateCommentByProductId(Comment comment, long productId) {
        Comment updatedComment = this.commentRepository.findById(comment.getCommentId()).get();
        updatedComment.setProductId(productId);
        this.commentRepository.save(updatedComment);
    }
}
