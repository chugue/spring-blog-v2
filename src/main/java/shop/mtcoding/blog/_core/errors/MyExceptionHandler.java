package shop.mtcoding.blog._core.errors;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding.blog._core.errors.exception.*;


//RuntimeException이 터지면 해당 파일로 오류가 모인다.
@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler
    public String ex400 (Exception400 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }
    @ExceptionHandler
    public String ex401 (Exception401 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }
    @ExceptionHandler
    public String ex402 (Exception402 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/402";
    }
    @ExceptionHandler
    public String ex403 (Exception403 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/403";
    }
    @ExceptionHandler
    public String ex404 (Exception404 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/404";
    }
    @ExceptionHandler
    public String ex500 (Exception500 e, HttpServletRequest request){
        request.setAttribute("msg", e.getMessage());
        return "err/500";
    }
}
