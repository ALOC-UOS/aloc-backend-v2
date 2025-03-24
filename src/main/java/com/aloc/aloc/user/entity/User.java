package com.aloc.aloc.user.entity;

import com.aloc.aloc.global.domain.AuditingTimeEntity;
import com.aloc.aloc.user.enums.Authority;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.security.crypto.password.PasswordEncoder;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(name = "aloc_user")
public class User extends AuditingTimeEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String username;

    @Column(nullable = false)
    private String githubId;

    private Integer rank;

    @Column(nullable = false)
    private String password = "password";

    @Enumerated(EnumType.STRING)
    private Authority authority;

    @Column(length = 1000)
    private String refreshToken;

    @OneToOne(mappedBy = "user", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private UserProfile userProfile;

    public void updateRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public void destroyRefreshToken() {
        this.refreshToken = null;
    }

    public void encodePassword(PasswordEncoder passwordEncoder) {
        this.password = passwordEncoder.encode(this.password);
    }

    public boolean matchPassword(PasswordEncoder passwordEncoder, String password) {
        return passwordEncoder.matches(password, this.password);
    }

    public void setProfileImageFileName(String fileName) {
        this.userProfile.setProfileImageFileName(fileName);
    }

    @Builder
    public User(
            String username,
            String githubId,
            String studentId,
            String password,
            String discordId,
            Integer rank,
            String notionEmail) {
        this.username = username;
        this.githubId = githubId;
        this.password = password;
        this.authority = Authority.ROLE_GUEST;
        this.rank = rank;
        this.userProfile =
                UserProfile.builder()
                        .user(this)
                        .profileColor("Blue")
                        .studentId(studentId)
                        .discordId(discordId)
                        .notionEmail(notionEmail)
                        .build();
    }
}