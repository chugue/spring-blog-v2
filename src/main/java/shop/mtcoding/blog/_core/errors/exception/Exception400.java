package shop.mtcoding.blog._core.errors.exception;

public class Exception400 extends RuntimeException{

    // 에러메시지를 msg에 담는다.
    public Exception400 (String msg){
        super(msg);
    }
}
