package spring.security.dto;

import lombok.*;
import spring.security.domain.User;

@ToString
@Getter
@Setter
public class UserJoinReq {

    private String username;
    private String password;
    private String email;

    public User toEntity() {
        return User.of(username, password, email);
    }
}
