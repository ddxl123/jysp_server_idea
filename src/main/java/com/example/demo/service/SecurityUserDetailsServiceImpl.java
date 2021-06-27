package com.example.demo.service;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.AuthenticationException;
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
public class SecurityUserDetailsServiceImpl implements UserDetailsService {

    BCryptPasswordEncoder bCryptPasswordEncoder;

    // 只有 on_login 路由才会执行这个函数
    @Override
    public UserDetails loadUserByUsername(String email) throws AuthenticationException {

        // 假设已获取验证码

        // 查询数据库是否存在该邮箱
//        boolean isExistEmail = true;
//        String emailByDB = "1033839760@qq.com";
//
//        if (isExistEmail) {
//            // 查询数据库中该用户是否存在其验证码
//            String codeEncodedByDB = bCryptPasswordEncoder.encode("1111");
//            boolean isExistCode = true;
//            // 获取数据库中该用户对应的角色
//            String roleByDB = "normal,vip";
//            if (isExistCode) {
//                // 若验证成功，则会转发到成功 URL
//                return new User(emailByDB, codeEncodedByDB, AuthorityUtils.commaSeparatedStringToAuthorityList(roleByDB));
//            } else {
//
//            }
//
//        } else {
//
//        }

        // 用户未注册 —— 创建新用户、生成 token


        // 2. 用户已注册 —— 验证密码、生成 token
        System.out.println(email);

        // 假设输入 s 为 uuu
        if (!"uuu".equals(email)) {
            // 抛异常后会请求转发给失败 URL
            throw new UsernameNotFoundException(email + " 用户不存在!",new Throwable("有有有有有有"));
        }

        // 假设查询出来的 password(已被加密过) 为 pw
        String pw = bCryptPasswordEncoder.encode("123");

        System.out.println(pw);

        // 将查询到的 username 和 password 注入 new User() 中。
        // 后面的 vip 表示的是该 new User() 具备哪些权限。
        return new User(email, pw, AuthorityUtils.commaSeparatedStringToAuthorityList("vip"));
    }
}
