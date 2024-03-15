package shop.mtcoding.blog.user;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;


@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserRepository userRepository;
    private final HttpSession session;

    @PostMapping("/join")
    public String join(UserRequest.JoinDTO reqDTO) {
        User user = userRepository.save(reqDTO.toEntity());
        session.setAttribute("sessionUser", user);
        return "redirect:/";
    }


    @PostMapping("/login")
    public String login(UserRequest.LoginDTO reqDTO) {
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

    @GetMapping("/user/update-form")
    public String updateForm(HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.findById(sessionUser.getId());
        request.setAttribute("user", user);
        return "user/update-form";
    }

    @PostMapping("user/update")
    public String update(UserRequest.UpdateDTO reqDTO, HttpServletRequest request) {
        User sessionUser = (User) session.getAttribute("sessionUser");
        User user = userRepository.updateById(sessionUser.getId(), reqDTO);
        request.setAttribute("user", user);
        return "redirect:/user/" + user.getId() + "/updateForm";
    }

    @GetMapping("/logout")
    public String logout() {
        session.invalidate();
        return "redirect:/";
    }
}
