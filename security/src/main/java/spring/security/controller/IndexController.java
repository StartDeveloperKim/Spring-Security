package spring.security.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import spring.security.auth.PrincipalDetails;
import spring.security.dto.UserJoinReq;
import spring.security.service.UserService;

@Slf4j
@RequiredArgsConstructor
@Controller
public class IndexController {

    private final UserService userService;

    @GetMapping("/test/login")
    @ResponseBody
    public String loginTest(Authentication authentication,
                            @AuthenticationPrincipal OAuth2User oAuth2User) {
        log.info("/test/login=======================");
        //PrincipalDetails principalDetails = (PrincipalDetails) authentication.getPrincipal();
        log.info("authentication: {}", authentication.getPrincipal());
        log.info("oAuth2User: {}", oAuth2User.getAttributes());

        return "세션 정보 확인하기";
    }

    @GetMapping({"", "/"})
    public String index() {
        return "index";
    }

    @GetMapping("/user")
    @ResponseBody
    public String user() {
        return "user";
    }

    @GetMapping("/admin")
    @ResponseBody
    public String admin() {
        return "admin";
    }

    @GetMapping("/manager")
    @ResponseBody
    public String manager() {
        return "manager";
    }

    // 스프링 시큐리티가 해당 URL을 낚아채서 기본적인 스프링시큐리티 로그인 페이지를 보여준다.
    @GetMapping("/loginForm")
    public String loginForm() {
        return "loginForm";
    }

    @GetMapping("/joinForm")
    public String joinForm() {
        return "joinForm";
    }

    @PostMapping("/join")
    public String join(@ModelAttribute UserJoinReq userJoinReq) {
        log.info("회원가입 POST {}", userJoinReq.toString());
        userService.joinUser(userJoinReq); // 근데 비밀번호가 그대로 DB에 저장된다 DB가 유출되면 암호가 그대로 유출

        return "redirect:/loginForm";
    }

    @GetMapping("/joinProc")
    @ResponseBody
    public String joinProc() {
        return "회원가입 완료";
    }

    @Secured("ROLE_ADMIN")
    @GetMapping("/info")
    @ResponseBody
    public String info() {
        return "개인정보";
    }
}
