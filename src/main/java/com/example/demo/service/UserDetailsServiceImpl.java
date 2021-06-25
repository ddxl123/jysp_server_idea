package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


// Security 自动拦截需认证的请求，通过该 service 的 loadUserByUsername 方法进行认证，返回一个 user 认证结果进行处理。

@Service
@AllArgsConstructor(onConstructor = @__(@Autowired))
public class UserDetailsServiceImpl implements UserDetailsService {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public UserDetails loadUserByUsername(String s) throws UsernameNotFoundException {

        System.out.println(s);

        // 假设输入 s 为 uuu
        if (!"uuu".equals(s)) {
            throw new UsernameNotFoundException(s + " 用户不存在!");
        }

        // 假设查询出来的 password(已被加密过) 为 pw
        String pw = bCryptPasswordEncoder.encode("123");

        System.out.println(pw);

        // 将查询到的 username 和 password 注入 new User() 中。
        // 后面的 vip 表示的是该 new User() 具备哪些权限。
        return new User(s, pw, AuthorityUtils.commaSeparatedStringToAuthorityList("vip"));
    }
}
