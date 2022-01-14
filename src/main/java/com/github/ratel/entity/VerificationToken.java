package com.github.ratel.entity;

import com.github.ratel.entity.enums.EntityStatus;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialArray;
import java.io.Serializable;
import java.util.Date;
import java.util.UUID;

@Data
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "verification_token")
public class VerificationToken implements Serializable {

    private static final long serialVersionUID = -2634418014000059593L;

    @Id
    @Column(name = "id")
    private String id = UUID.randomUUID().toString();

    @OneToOne(targetEntity = User.class, fetch = FetchType.LAZY)
    @JoinColumn(name = "User_id", referencedColumnName = "id")
    private User user;

    @Column(name = "token", nullable = false, unique = true)
    private String token;

    @Column(nullable = false)
    @Enumerated(EnumType.STRING)
    private EntityStatus status = EntityStatus.on;

    @Column(name = "create_at", columnDefinition = "TIMESTAMP")
    private Date createAt = new Date();

    public VerificationToken(User user, String token) {
        this.user = user;
        this.token = token;
    }
}
