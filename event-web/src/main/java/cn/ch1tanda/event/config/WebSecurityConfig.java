package cn.ch1tanda.event.config;

import cn.ch1tanda.event.manager.user.UserManager;
import cn.ch1tanda.event.manager.user.req.AuthReq;
import cn.ch1tanda.event.manager.user.req.GetAuthoritiesReq;
import cn.ch1tanda.event.manager.user.resp.AuthResp;
import cn.ch1tanda.event.manager.user.resp.GetAuthoritiesResp;
import cn.ch1tanda.event.utils.variable.StringUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class WebSecurityConfig {
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeHttpRequests()
                .mvcMatchers("/html/**", "/css/**", "/js/**").permitAll()
                .mvcMatchers("/register","/register/sendVerifyCode").permitAll()
                .anyRequest().authenticated()
                .and()
                .formLogin()
                .loginPage("/html/user-login.html")
                .usernameParameter("username")
                .passwordParameter("password")
                .loginProcessingUrl("/auth")
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
                                            String.join(",", getAuthoritiesResp.getData()
                                            )
                                    )
                    ).build();

        };

    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }
}
