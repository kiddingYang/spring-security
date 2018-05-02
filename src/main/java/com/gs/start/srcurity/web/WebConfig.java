package com.gs.start.srcurity.web;

import com.gs.start.srcurity.filter.CustomAccessDecision;
import com.gs.start.srcurity.filter.MysqlUserDetailsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Administrator on 2018/5/2.
 */
@Configuration
public class WebConfig extends WebSecurityConfigurerAdapter {


    @Autowired
    private MysqlUserDetailsService userDetailsService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {

//        http.httpBasic()
        http.formLogin()
//                .loginPage("/gs-login.html") // .loginPage("/gs-login.html")
//                .loginProcessingUrl("/mylogin") // 登录url

                // 成功
                .successHandler(new AuthenticationSuccessHandler() {
                    @Override
                    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws IOException, ServletException {

                    }
                })
                // 失败
                .failureHandler(new AuthenticationFailureHandler() {

                    @Override
                    public void onAuthenticationFailure(HttpServletRequest request, HttpServletResponse response, AuthenticationException exception) throws IOException, ServletException {

                    }})

                .and()
                .authorizeRequests()
                .antMatchers("/gs-login.html").permitAll() // 匹配器匹配gs-login.html不需要认证
//                .antMatchers("/test2").hasRole("user")
//                .antMatchers(HttpMethod.GET,"/test").hasRole("ADMIN")
                .anyRequest()
                .authenticated()
                .and().csrf().disable();

    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService);
    }

    /**
     * 用户加密 Match
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}
