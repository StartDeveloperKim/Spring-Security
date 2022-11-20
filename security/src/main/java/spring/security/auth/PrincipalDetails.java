package spring.security.auth;

// 시큐리티가 /login 주소 요청이 오면 낚아채서 로그인을 진행시킨다.
// 로그인을 진행이 완료가 되면 시큐리티 session을 만들어준다.(Security ContextHolder)
// 오브젝트 => Authentication 타입 객체
// Authentication 안에 User 정보가 있어야 된다.
// User 오브젝트타입 => UserDetails 타입 객체

import lombok.Builder;
import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import spring.security.domain.Role;
import spring.security.domain.User;

import java.util.ArrayList;
import java.util.Collection;

// Security Session => Authentication => UserDetails
@Getter
public class PrincipalDetails implements UserDetails {

    private final String username;
    private final String password;
    private final Role role;

    @Builder
    public PrincipalDetails(String username, String password, Role role) {
        this.username = username;
        this.password = password;
        this.role = role;
    }

    // 해당 User의 권환을 리턴함
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        Collection<GrantedAuthority> collect = new ArrayList<>();
        collect.add(new GrantedAuthority() {
            @Override
            public String getAuthority() {
                return getRole().toString();
            }
        });
        return collect;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.username;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        // 만약 우리 사이트에서 1년동안 로그인을 하지 않았다면 휴먼계정으로 번환하기로 했다
        // 현재시간 - 최근로그인시간 => 1년을 초과하면 False
        return true;
    }
}
