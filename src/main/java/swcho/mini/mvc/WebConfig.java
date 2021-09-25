package swcho.mini.mvc;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import swcho.mini.mvc.web.interceptor.LoggingInterceptor;
import swcho.mini.mvc.web.interceptor.LoginCheckInterceptor;

@Component
public class WebConfig implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {

        // 로깅
        registry.addInterceptor(new LoggingInterceptor())
                .order(1)
                .addPathPatterns("/**")
                .excludePathPatterns("/lib/**", "/images/**");

        // 로그인 인증 체크
        registry.addInterceptor(new LoginCheckInterceptor())
                .order(2)
                .addPathPatterns("/**")
                .excludePathPatterns(
                        "/error/**",   // 에러 페이지
                        "/", "/index",  // HomeController
                        "/item/list",  // ItemController
                        "/member/**",  // MemberController
                        "/lib/**", "/images/**" // 정적 리소스
                );

    }
}
