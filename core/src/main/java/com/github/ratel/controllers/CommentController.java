package com.github.ratel.controllers;

import com.github.ratel.dto.CommentDto;
import com.github.ratel.entity.Comment;
import com.github.ratel.services.impl.CommentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    @Autowired
    private CommentServiceImpl commentServiceImpl;

    @GetMapping
    public List<Comment> findAllComments() {
        return this.commentServiceImpl.findAllComments();
    }

    @Secured("ROLE_USER")
    @GetMapping("/{commentsId}")
    public Optional<Comment> findCommentById(@PathVariable long commentsId) {
        return this.commentServiceImpl.findCommentById(commentsId);
    }

    @Secured("ROLE_USER")
    @GetMapping("/product/{productId}")
    public List<Comment> findAllCommentsByProductId(@PathVariable long productId) {
        return this.commentServiceImpl.findCommentsByProductId(productId);
    }

    @Secured("ROLE_USER")
    @GetMapping("/user/{userId}")
    public List<Comment> findAllCommentsByUserId(@PathVariable long userId) {
        return this.commentServiceImpl.findCommentsByUserId(userId);
    }

    @Secured("ROLE_USER")
    @PostMapping("/product/{productId}")
    public Comment createCommentByProductId(@RequestBody CommentDto commentDto, @PathVariable long productId) {
        return this.commentServiceImpl.saveCommentByProductId(commentDto, productId);
    }

    @Secured("ROLE_USER")
    @PutMapping("/product/{productId}")
    public void editCommentByProductId(@RequestBody Comment comment, @PathVariable long productId) {
        this.commentServiceImpl.updateCommentByProductId(comment, productId);
    }
}
