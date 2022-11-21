package spring.security.domain;

import lombok.Builder;
import lombok.Getter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Getter
@Entity
@SequenceGenerator(
        name = "USER_SEQ_GENERATOR",
        sequenceName = "USER_SEQ",
        initialValue = 1,
        allocationSize = 1
)
@Table(name = "Users")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "USER_SEQ_GENERATOR")
    private Long id;

    private String username;

    private String password;

    private String email;

    @Enumerated(EnumType.STRING)
    private Role role;

    private LocalDateTime createDate;

    private String provider;
    private String providerId;

    private User() {
    }

    @Builder
    protected User(String username, String password, String email) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.role = Role.ROLE_USER;
        this.createDate = LocalDateTime.now();
    }

    public static User of(String username, String password, String email) {
        return User.builder()
                .username(username)
                .password(password)
                .email(email)
                .build();
    }
}
