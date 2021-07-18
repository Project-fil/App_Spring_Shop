package com.github.ratel.controllers;

import com.github.ratel.dto.CommentDto;
import com.github.ratel.entity.Comment;
import com.github.ratel.services.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping(path = "/comments")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @GetMapping
    public List<Comment> findAllComments() {
        return this.commentService.findAllComments();
    }

    @GetMapping("/{commentsId}")
    public Optional<Comment> findCommentById(@PathVariable long commentsId) {
        return this.commentService.findCommentById(commentsId);
    }

    @GetMapping("/product/{productId}")
    public List<Comment> findAllCommentsByProductId(@PathVariable long productId) {
        return this.commentService.findCommentsByProductId(productId);
    }

    @GetMapping("/user/{userId}")
    public List<Comment> findAllCommentsByUserId(@PathVariable long userId) {
        return this.commentService.findCommentsByUserId(userId);
    }

    @PostMapping("/product/{productId}")
    public Comment createCommentByProductId(@RequestBody CommentDto commentDto, @PathVariable long productId) {
        return this.commentService.saveCommentByProductId(commentDto, productId);
    }

    @PutMapping("/product/{productId}")
    public void editCommentByProductId(@RequestBody Comment comment, @PathVariable long productId) {
        this.commentService.updateCommentByProductId(comment, productId);
    }
}
