package com.aloc.aloc.user.entity;

import com.aloc.aloc.global.domain.AuditingTimeEntity;
import com.aloc.aloc.user.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.UUID;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "aloc_user")
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(columnDefinition = "UUID")
    private UUID id;

    private String oauthId;
    private String name;
    private String email;
    private String profileImageFileName;
    private Integer coin;

    private String profileColor;

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(length = 1000)
    private String refreshToken;


}
