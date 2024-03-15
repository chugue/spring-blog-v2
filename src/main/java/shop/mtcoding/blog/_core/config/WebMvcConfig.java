package shop.mtcoding.blog._core.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import shop.mtcoding.blog._core.interceptor.LoginInterceptor;

@Configuration // IoC 등록
public class WebMvcConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        // loginInterceptor는 사용자가 지정한 클래스이고, 여기서
        // addInterceptor로 객체를 등록할 수 있다.
        // 그리고 addPathPatterns로 어떤 URL에 해당 인터셉터가 작동할 지 지정한다.
        // 제외하고 싶은 URL이있다면, excludePathPatterns로 등록한다.
        registry.addInterceptor(new LoginInterceptor())
                .addPathPatterns("/board/**", "/user/**")
                .excludePathPatterns("/board/{id:\\d+}");
    }
}