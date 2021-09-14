package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@Data
@Builder
@Table(name = "comments")
@NoArgsConstructor
@AllArgsConstructor
public class Comment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "comment_id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private long commentId;

    @ManyToOne
    @JoinColumn(name = "user_id", nullable = false, columnDefinition = "BIGINT")
    private User user;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "BIGINT")
    private Product product;

    @Column(name = "comment_text", nullable = false, columnDefinition = "TEXT")
    private String commentText;

    @Column(name = "created_at", nullable = false, columnDefinition = "TEXT")
    private Date createdAt;

    public Comment(User user, Product product, String commentText, Date createdAt) {
        this.user = user;
        this.product = product;
        this.commentText = commentText;
        this.createdAt = createdAt;
    }
}
