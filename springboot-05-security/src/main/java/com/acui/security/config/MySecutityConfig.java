package com.acui.security.config;

import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

/**
 * @author acui
 */
@EnableWebSecurity
public class MySecutityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        super.configure(http);
        //定制请求的授权规则
        http.authorizeRequests().antMatchers("/").permitAll()
                .antMatchers("/level1/**").hasRole("VIP1")
                .antMatchers("/level2/**").hasRole("VIP2")
                .antMatchers("/level3/**").hasRole("VIP3");
        http.formLogin().usernameParameter("user").passwordParameter("pwd")
                .loginPage("/userlogin");
        //1、login开到登录页
        //2、重定向到/login/error表示登录失败
        /**
         * 3、更多详细规定
         * 4、默认post形式的/login代表处理登录
         * 5、一旦定制loginPage，那么loginPage的POST请求时登录
         */

        //开启自动配置的注销功能
        http.logout().logoutSuccessUrl("/");//注销成功后返回首页
        //1、访问/logout并退出
        //2、注销成功会返回/login?logout 页面

        //开启记住我功能
        http.rememberMe().rememberMeParameter("remember");
        //登录成功后，将cookie发给浏览器保存，以后登录带上这个cookie，只要通过检查据可以免登录
        //logout会删除cookie
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.inMemoryAuthentication()
                .passwordEncoder(new BCryptPasswordEncoder())
                .withUser("zhangsan").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP1", "VIP2")
                .and()
                .withUser("lisi").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3")
                .and()
                .withUser("wangwu").password(new BCryptPasswordEncoder().encode("123456")).roles("VIP2", "VIP3");


    }
}
