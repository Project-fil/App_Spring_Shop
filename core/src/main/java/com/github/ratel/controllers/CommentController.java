package com.github.ratel.controllers;

import com.github.ratel.dto.CommentDto;
import com.github.ratel.entity.Comment;
import com.github.ratel.services.impl.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static com.github.ratel.utils.TransferObj.toComment;

@CrossOrigin
@RestController
@RequestMapping(path = "/product/{product-id}")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @Secured("ROLE_USER")
    @GetMapping("/comment")
    public List<Comment> findAllCommentByProductId(@PathVariable("product-id") long productId) {
        return this.commentService.findCommentByProductId(productId);
    }

    @Secured("ROLE_USER")
    @PostMapping("/comment")
    public Comment createCommentByProductId(@RequestBody CommentDto commentDto, @PathVariable("product-id") long productId) {
        return this.commentService.saveCommentByProductId(toComment(commentDto), productId);
    }

    @Secured("ROLE_USER")
    @PutMapping("/comment")
    public void editCommentByProductId(@RequestBody CommentDto commentDto, @PathVariable("product-id") long productId) {
        this.commentService.updateCommentByProductId(toComment(commentDto), productId);
    }
}
