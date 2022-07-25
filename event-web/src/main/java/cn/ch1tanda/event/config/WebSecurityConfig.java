package cn.ch1tanda.event.config;

import cn.ch1tanda.event.convention.response.Results;
import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.manager.user.req.AuthReq;
import cn.ch1tanda.event.manager.user.req.GetAuthoritiesReq;
import cn.ch1tanda.event.manager.user.resp.AuthResp;
import cn.ch1tanda.event.manager.user.resp.GetAuthoritiesResp;
import com.alibaba.fastjson2.support.spring.http.converter.FastJsonHttpMessageConverter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServletServerHttpResponse;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

@Configuration
public class WebSecurityConfig {

    public static String[] permittedPath;

    @Bean
    public SecurityFilterChain securityFilterChain(
            HttpSecurity httpSecurity,
            FastJsonHttpMessageConverter httpMessageConverter
    ) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .mvcMatchers(WebSecurityConfig.permittedPath).permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/user/login")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth")
                .failureHandler(authenticationFailureHandler(httpMessageConverter))
                .successHandler(authenticationSuccessHandler(httpMessageConverter))
                .and()
                .csrf().disable();

        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(UserManager manager) {
        return username -> {
            AuthResp authResp = manager.auth(new AuthReq(username));
            GetAuthoritiesResp getAuthoritiesResp = manager.getAuthorities(new GetAuthoritiesReq(username));

            if (!authResp.getCode().equals("0")) {
                throw new UsernameNotFoundException(authResp.getMessage());
            }

            if (!getAuthoritiesResp.getCode().equals("0")) {
                throw new UsernameNotFoundException(getAuthoritiesResp.getMessage());
            }

            return User.builder()
                    .username(authResp.getData().getUsername())
                    .password(authResp.getData().getPassword())
                    .authorities(
                            AuthorityUtils
                                    .commaSeparatedStringToAuthorityList(
                                            String.join(",", getAuthoritiesResp.getData())
                                    )
                    ).build();
        };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    AuthenticationFailureHandler authenticationFailureHandler(FastJsonHttpMessageConverter httpMessageConverter) {
        return (request, response, exception) -> {
            ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);
            servletServerHttpResponse.setStatusCode(HttpStatus.UNAUTHORIZED);

            httpMessageConverter.write(
                    Results.failure("1", "用户名或密码错误"),
                    MediaType.APPLICATION_JSON,
                    servletServerHttpResponse
            );

        };
    }

    AuthenticationSuccessHandler authenticationSuccessHandler(FastJsonHttpMessageConverter httpMessageConverter) {
        return (request, response, authentication) -> {
            ServletServerHttpResponse servletServerHttpResponse = new ServletServerHttpResponse(response);
            servletServerHttpResponse.setStatusCode(HttpStatus.OK);

            httpMessageConverter.write(Results.success(null), MediaType.APPLICATION_JSON, servletServerHttpResponse);
        };
    }


    static {
        permittedPath = new String[] {
                // 静态资源
                "/static/**",
                // 用户登录注册接口
                "/user/login",
                "/user/register",
                "/user/register/sendVerifyCode"
        };
    }
}
