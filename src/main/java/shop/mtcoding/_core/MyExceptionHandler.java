package shop.mtcoding._core;


import jakarta.servlet.http.HttpServletRequest;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import shop.mtcoding._core.errors.*;

@ControllerAdvice
public class MyExceptionHandler {

    @ExceptionHandler(Exception400.class)
    public String ex400(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/400";
    }

    @ExceptionHandler(Exception401.class)
    public String ex401(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());
        return "err/401";
    }

    @ExceptionHandler(Exception402.class)
    public String ex402(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());

        return "err/402";
    }

    @ExceptionHandler(Exception403.class)
    public String ex403(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());

        return "err/403";
    }

    @ExceptionHandler(Exception404.class)
    public String ex404(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());

        return "err/404";
    }

    @ExceptionHandler(Exception500.class)
    public String ex500(RuntimeException e, HttpServletRequest request) {
        request.setAttribute("msg", e.getMessage());

        return "err/500";
    }

    @ExceptionHandler(Exception.class)
    public String unknown(RuntimeException e, HttpServletRequest request){
        return "err/unknown";
    }


}