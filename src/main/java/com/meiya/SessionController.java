package com.meiya;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import java.util.Map;

/**
 * @SessionAttribute 作用是在controller共享model，
 * 直到调用org.springframework.web.bind.support.SessionStatus#setComplete会清除此session值.
 * 否则长期保留(session过期,这个值也不再保留)
 *
 */
@Controller
@RequestMapping("user")
@SessionAttributes("test")
public class SessionController {

    @RequestMapping("test1")
    public String test1(Map<String, Object> model, SessionStatus sessionStatus) {
        model.put("test", "something");
        return "sessionIndex";
    }
    @RequestMapping("test2")
    public String test2() {
        return "sessionIndexTwo";
    }
    @RequestMapping("test3")
    public String test3(Map<String, Object> model) {
        Object test = model.get("test");
        System.out.println(test);
        model.put("test", "nothing");
        return "sessionIndexTwo";
    }

}

















