package com.acui.springboot04web.Contrller;

import com.acui.springboot04web.exception.UserNotExitException;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.View;
import org.springframework.web.servlet.ViewResolver;
import java.util.Arrays;
import java.util.Locale;
import java.util.Map;

@Controller
public class HelloController {
//    @RequestMapping({"/", "index.html"})
//    public String index() {
//        return "index";
//    }

    @ResponseBody
    @RequestMapping("hello")
    public String hello(@RequestParam("user") String user) {
        if (user.equals("aaa")) {
            throw new UserNotExitException();
        }
        return "hello world!";
    }

    @RequestMapping("success")
    public String success(Map<String, Object> map) {
        map.put("name", "<h1>acui</h1>");
        map.put("users", Arrays.asList("zhangsan", "lisi", "wangwu"));
        //classpath:/templates/success.html
        return "success";
    }
    @Bean
    public ViewResolver myViewResolver() {
        return new MyViewResolver();
    }

    private static class MyViewResolver implements ViewResolver {

        @Override
        public View resolveViewName(String s, Locale locale) throws Exception {
            return null;
        }
    }
}
