package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import shop.mtcoding._core.errors.exception.Exception400;
import shop.mtcoding._core.errors.exception.Exception401;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;


    @PostMapping("/user/update")
    public String update(UserRequest.UpdateDTO reqDTO){
        User sessionUser = (User)session.getAttribute("sessionUser");
        User user = userRepository.updateById(sessionUser.getId(), reqDTO);

        return "redirect:/";
    }

    @GetMapping("/hello")
    public String hello (HttpServletRequest request){
        request.setAttribute("msg", "유저네임의 길이가 길어요");
        return "err/400";
    }

    @PostMapping("/join")
    public String join (UserRequest.JoinDTO reqDTO){
        try {
            userRepository.save(reqDTO.toEntity());
        } catch (DataIntegrityViolationException e) {
            throw new Exception400("동일한 유저네임이 존재합니다");
        }
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login (UserRequest.LoginDTO reqDTO) {
        try {
            User sessionUser = userRepository.findByUsernameAndPassword(reqDTO);
            session.setAttribute("sessionUser", sessionUser);
            return "redirect:/";
        }catch (EmptyResultDataAccessException e){
            throw new Exception401("유저네임 혹은 비밀번호가 틀렸어요");
        }
    }



    @GetMapping("/join-form")
    public String joinForm() {
        return "user/join-form";
    }

    @GetMapping("/login-form")
    public String loginForm() {


        return "user/login-form";
    }

    @GetMapping("/user/update-form") //id로 pathvariable를 하지 않은 까닭은 세션값을 불러오면 되기 때문이다.
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User)session.getAttribute("sessionUser");

        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
