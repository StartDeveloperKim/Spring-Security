package spring.security.auth;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import spring.security.domain.User;
import spring.security.repository.UserRepository;

// 시큐리티 설정에서 loginProcessingUrl("/login");
// login 요청이 오면 자동으로 UserDetailsService 타입으로 IoC되어 있는 loadUserByUsername 함수가 실행
@RequiredArgsConstructor
@Slf4j
@Service
public class PrincipalDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    // 시큐리티 session = Authentication = UserDetails 타입
    // loadUserByUsername이 리턴된다면 Authentication 객체 내부에 저장되고 이 Authentication 객체는 시큐리티 session 내부에 저장된다.
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        log.info("loadUserByUsername {}", username);
        User findUser = userRepository.findByUsername(username);
        if (findUser != null) {
            return PrincipalDetails.builder()
                    .username(findUser.getUsername())
                    .password(findUser.getPassword())
                    .role(findUser.getRole())
                    .build();
        }
        return null;
    }
}
