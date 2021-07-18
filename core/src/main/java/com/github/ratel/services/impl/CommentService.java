package com.github.ratel.services.impl;

import com.github.ratel.dto.CommentDto;
import com.github.ratel.entity.Comment;
import com.github.ratel.entity.Product;
import com.github.ratel.entity.User;
import com.github.ratel.repositories.CommentRepository;
import com.github.ratel.repositories.ProductRepository;
import com.github.ratel.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityExistsException;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final CommentRepository commentRepository;
    private final ProductRepository productRepository;
    private final UserRepository userRepository;

    public List<Comment> findAllComments() {
        return commentRepository.findAll();
    }

    public Optional<Comment> findCommentById(long id) {
        return commentRepository.findById(id);
    }

    public List<Comment> findCommentsByProductId(long productId) {
        return commentRepository.findCommentByProductId(productId);
    }

    public List<Comment> findCommentsByUserId(long userId) {
        return commentRepository.findCommentByUserId(userId);
    }

    public Comment saveComment(Comment comment) {
        return commentRepository.save(comment);
    }

    public Comment saveCommentByProductId(CommentDto dto, long id) {
        Comment comment = new Comment();

        Product product = productRepository.findById(dto.getProductId())
                .orElseThrow(RuntimeException::new);
        comment.setProduct(product);

        User user = userRepository.findById(dto.getUserId()).orElseThrow(() -> new RuntimeException(""));
        comment.setUser(user);
        comment.setCommentText(dto.getCommentText());
        comment.setCreatedAt(dto.getCreatedAt());

        return commentRepository.save(comment);
    }

    public void updateCommentByProductId(Comment comment, long id) {
        commentRepository.findById(comment.getCommentId()).orElseThrow(() -> new EntityExistsException("Comment not found"));
    }
}
