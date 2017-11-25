package com.meiya;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import javax.servlet.http.HttpServletRequest;

/**
 * 一样先调用/user/test1,再调用/user/test2.在test2方法能得到
 * test1方法set进去的值.使用了@SessionAttributes,如果在controller的方法参数上
 * 有@ModelAttribute,那么此方法会确保属性test的session会存在,
 * 否则会抛org.springframework.web.HttpSessionRequiredException异常,
 * 即上面代码先不调/user/test1,直接调/user/test2就会抛此异常
 */

@Controller
@RequestMapping("userTwo")
@SessionAttributes("test")
public class SessionControllerTwo {
    @RequestMapping(value = "test1")
    public String test1(HttpServletRequest request) {
        User user = new User(1,"lqw512");
        request.getSession().setAttribute("test", user);
        return "sessionIndex";
    }
    @RequestMapping(value = "test2")
    public String test2(@ModelAttribute("test") User user,SessionStatus sessionStatus) {
        System.out.println(user.getUserName());
        sessionStatus.setComplete();
        return "sessionIndexTwo";
    }
}






















