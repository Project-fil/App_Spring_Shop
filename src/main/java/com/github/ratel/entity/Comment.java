package com.github.ratel.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;
import org.springframework.data.annotation.CreatedBy;

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
    @Column(name = "id", nullable = false, columnDefinition = "BIGINT", unique = true)
    private Long id;

    @CreatedBy
    @ManyToOne
    @JoinColumn(name = "author_id")
    private User author;

    @ManyToOne
    @JoinColumn(name = "product_id", nullable = false, columnDefinition = "BIGINT")
    private Product product;

    @Column(name = "comment_text", nullable = false, columnDefinition = "TEXT")
    private String text;

    @Column(name = "is_removed")
    private boolean removed;

    @CreationTimestamp
    @Column(name = "last_modified_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date lastModifiedDate;

    @UpdateTimestamp
    @Column(name = "created_date")
    @Temporal(TemporalType.TIMESTAMP)
    private Date createdDate;


    public Comment(User author, Product product, String text, Date createdDate) {
        this.author = author;
        this.product = product;
        this.text = text;
        this.createdDate = createdDate;
    }
}
