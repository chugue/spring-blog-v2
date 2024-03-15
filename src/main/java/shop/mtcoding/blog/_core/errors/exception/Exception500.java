package shop.mtcoding.blog._core.errors.exception;

public class Exception500 extends RuntimeException{

    // 에러메시지를 msg에 담는다.
    public Exception500(String msg){
        super(msg);
    }
}
