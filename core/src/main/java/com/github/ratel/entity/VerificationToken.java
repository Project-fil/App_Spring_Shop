package com.github.ratel.entity;

import com.github.ratel.entity.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_token")
public class VerificationToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private EntityStatus status = EntityStatus.on;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP")
    private Date createAt = new Date();

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
