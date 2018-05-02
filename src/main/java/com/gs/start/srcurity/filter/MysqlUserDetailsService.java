package com.gs.start.srcurity.filter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

/**
 * Created by Administrator on 2018/5/2.
 */
@Component
public class MysqlUserDetailsService implements UserDetailsService {


    @Autowired
    private PasswordEncoder passwordEncoder;

    private Logger logger = LoggerFactory.getLogger(MysqlUserDetailsService.class);

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        // 根据用户名查询用户信息
//        UserDetails userDetails = new User(username,
//                "123456",
//                AuthorityUtils.commaSeparatedStringToAuthorityList("admin"));

        // 查询是否被冻结etc..
        String password = passwordEncoder.encode("123456");
        logger.info("password:{}",password);
        UserDetails userDetails = new User(username,
                password,
                true,true,true,true,
                AuthorityUtils.commaSeparatedStringToAuthorityList("ROLE_ADMIN"));

        return userDetails;
    }

}
