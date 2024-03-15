package shop.mtcoding.blog._core.interceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import org.springframework.web.servlet.HandlerInterceptor;
import shop.mtcoding.blog._core.errors.exception.Exception401;
import shop.mtcoding.blog.user.User;

public class LoginInterceptor implements HandlerInterceptor{
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        System.out.println("preHandle............"); //위치 파악용도
        HttpSession session = request.getSession(); //세션을 가져온다.

        User sessionUser = (User) session.getAttribute("sessionUser");
        if(sessionUser == null){ // 세션유저가 null이라면 401에러페이지를 반환한다.
            throw new Exception401("로그인 하셔야 해요");
        }
        return true; //리턴이 true일때 Obejct handler가 쥐고있는 메소드가 정상적으로 작동한다. 여기서는 /login
    }


}