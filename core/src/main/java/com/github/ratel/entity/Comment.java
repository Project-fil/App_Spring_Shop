package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long commentId;

    @Column(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    private long userId;

    @Column(name = "product_id", nullable = false, columnDefinition = "BIGINT")
    private long productId;

    @Column(name = "comment_text", nullable = false, columnDefinition = "TEXT")
    private String commentText;

    @Column(name = "created_at", nullable = false, columnDefinition = "TEXT")
    private Date createdAt;

    public Comment(long userId, long productId, String commentText, Date createdAt) {
        this.userId = userId;
        this.productId = productId;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }
}
