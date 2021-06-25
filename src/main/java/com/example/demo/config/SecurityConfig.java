package com.example.demo.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@Configuration
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Bean
    public BCryptPasswordEncoder bCryptPasswordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 自定义登陆页面
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.formLogin()
                // （GET）被重定向的登陆 url。
                // 重定向：浏览器的 URL 会变成重定向的 URL。
                // 因为是重定向，所以 url 可以是资源 /xxx.html，也可以是普通的请求路径 /xxx, 也可以是 https://www.baidu.com/。
                .loginPage("/login_page")

                ///////////////////////////////////////////////////////////////////
                ///
                ///

                // （POST）用户输入对应 usernameParameter 和 passwordParameter 键 的值 后点击提交，要提交到哪个 URL 上。
                //
                // 1. 请求只要是该 URL，若 body 参数不存在对应 username_key password_key, 或 password 错误，都会被重定向至 failureForwardUrl。
                // 2. 若请求带有 params，则 URL 会带上 "key=value" 内容，与 loginProcessingUrl 的 URL 不匹配，因此会被作拦截处理, 而并不会作提交处理。
                .loginProcessingUrl("/on_login")

                // 可自定义 POST 请求的 body 中 username 的 key。
                .usernameParameter("the_username_key")

                // 可自定义 POST 请求的 body 中 password 的 key。
                .passwordParameter("the_password_key")


                //  示例:
                //  request URL: POST http://localhost/on_login
                //  request body: the_username_key=the_username_value, the_password_key=the_password_value

                ///
                ///
                ///////////////////////////////////////////////////////////////////


                ///////////////////////////////////////////////////////////////////
                ///
                ///

                //  请求转发：浏览器的 URL 不会变。
                //  1. 因为是在 loginProcessingUrl 基础上的请求转发，所以 URL 不能是资源 URL /xx.html。
                //  2. 若要使用 重定向 的方式，则可以使用 .successHandler()/.failureHandler()。
                //  3. 因为是在 loginProcessingUrl 基础上的请求转发，所以下面的 successForwardUrl 和 loginProcessingUrl 也必须和 loginProcessingUrl 一样是 POST 请求。

                // （POST）登陆成功的请求转发。
                .successForwardUrl("/success")

                // （POST）登陆失败的请求转发。
                .failureForwardUrl("/fail");

        //      ///
        //      ///
        //      /////////////////////////////////////////////////////////////////

        // 下面【授权】拦截必须按照顺序, 例如：
        //
        // .antMatchers("/login_page").permitAll():
        // 1. 下面的 /login_page 请求被拦截之后，/login_page 就无法再进入 anyRequest() 了，能进入 anyRequest() 的只能是非 /login_page。
        // 2. 理解一下就是, /login_page 被 antMatchers 拦截, 而 .permitAll() 含义是该 /login_page 可以是已被认证的，也可以是未被认证的。
        // 3. 再理解一下就是, 无论该用户有没有在这个浏览器登陆过，只要访问 /login_page URL,
        //      都会被 .antMatchers("/login_page").permitAll() 拦截住, 而不会让它进入 .anyRequest().authenticated()。
        //
        // .anyRequest().authenticated():
        // 1. 除了被 antMatchers 拦截的 URL 外 , 其他 URL 都会被执行 .anyRequest().authenticated()。
        // 2. .authenticated() 的作用就是校验用户是否已被认证(登陆)过, 已登录状态则允许进入页面, 未登陆状态则会被跳转到登陆页面。
        //
        // 综上:
        // 1. permitAll() 就是该 antMatchers() 的 URLs 允许任何人 (permitAll) 进行访问。
        // 2. authenticated() 就是除了被 antMatchers() 外的 anyRequest() 的 URLs, 都必须进行被认证(authenticated)登陆后才能访问。
        http.authorizeRequests()
                // 因为请求转发的 URL 不会被拦截，而请求重定向 的 URL 会被拦截，因此不会拦截上面的 URL: loginProcessingUrl successForwardUrl failureForwardUrl
                .antMatchers("/login_page").permitAll()

                // 在用户登陆已登陆的状态下(在浏览器中以 cookie 的方式标记是否已登录),
                // 且在创建 User 时, 在 UserDetailsService 接口实现中的 AuthorityUtils.commaSeparatedStringToAuthorityList("vip") 中被标记为 vip 权限的用户,
                // 才能进入 /vip。
                .antMatchers("/vip").hasAuthority("vip")

                // 被拦截的 URL 都必须先被认证（被重定向至 loginPage），无论该 URL 是否有在你的 controller 中被定义。
                .anyRequest().authenticated();

        // 禁用 csrf
        http.csrf().disable();
    }
}
