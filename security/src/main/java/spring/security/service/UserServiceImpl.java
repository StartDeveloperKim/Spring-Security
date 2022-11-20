package spring.security.service;

import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import spring.security.domain.User;
import spring.security.dto.UserJoinReq;
import spring.security.repository.UserRepository;

@RequiredArgsConstructor
@Service
@Transactional
public class UserServiceImpl implements UserService{

    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void joinUser(UserJoinReq userJoinReq) {
        userJoinReq.setPassword(bCryptPasswordEncoder.encode(userJoinReq.getPassword()));
        userRepository.save(userJoinReq.toEntity());
    }
}
