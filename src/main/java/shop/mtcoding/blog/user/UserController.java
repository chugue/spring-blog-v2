package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import static org.springframework.data.jpa.domain.AbstractPersistable_.id;


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
        User user = userRepository.save(reqDTO.toEntity());
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }

    @PostMapping("/login")
    public String login (UserRequest.LoginDTO reqDTO) {
        User sesssionUser = userRepository.findByUsernameAndPassword(reqDTO);

        session.setAttribute("sessionUser", sesssionUser);
        return "redirect:/";
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
