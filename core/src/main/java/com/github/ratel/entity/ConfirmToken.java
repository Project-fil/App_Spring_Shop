package com.github.ratel.entity;

import com.github.ratel.payload.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "confirm_token")
public class ConfirmToken {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id", referencedColumnName = "id")
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(name = "new_pass")
    private String newPass;

    @Enumerated(value = EnumType.STRING)
    @Column(name = "status")
    private EntityStatus status = EntityStatus.on;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP")
    private Date createAt = new Date();

    public ConfirmToken(User user, String token, String newPass) {
        this.user = user;
        this.token = token;
        this.newPass = newPass;
    }
}
