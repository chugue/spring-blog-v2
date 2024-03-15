package shop.mtcoding.blog.user;

import lombok.Data;

public class UserRequest {

    @Data
    public static class UpdateDTO{
        private String password;
        private String email;
    }

    @Data
    public static class JoinDTO{
        private String username;
        private String password;
        private String email;

        public User toEntity() { //생성시에 빌더패턴을 사용한다.
            return User.builder()
                    .username(username)
                    .password(password)
                    .email(email)
                    .build();
        }
    }


    @Data
    public static class LoginDTO{
        private String username;
        private String password;
    }
}
