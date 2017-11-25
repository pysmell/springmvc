package com.meiya;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

@Controller
public class ParameterController {

    /**
     * 1.直接把表单的参数写在controller相应的方法形参中，适用于get方式提交，不适用post方式提交
     * @param userName
     * @param password
     * @return
     */
    @RequestMapping("/addUser1")
    public String addUser(String userName, String password) {
        System.out.println("username is:" + userName);
        System.out.println("password is:" + password);
        return "parameter1";
    }

    /**
     * 2.通过HttpServletRequest接收,post方式和get方式都可以
     * @param request
     * @return
     */
    @RequestMapping("/addUser2")
    public String addUser2(HttpServletRequest request) {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        System.out.println(username);
        System.out.println(password);
        return "parameter2";
    }

    /**
     * 3、通过一个bean来接收,post方式和get方式都可以
     *  @param user
     *  @return
     */
    @RequestMapping("/addUser3")
    public String addUser3(UserModel user) {
        System.out.println("username is:" + user.getUsername());
        System.out.println("password is:" + user.getPassword());
        return "parameter3";
    }


    /**
     * 4、通过@PathVariable获取路径中的参数
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/addUser4/{username}/{password}", method = RequestMethod.GET)
    public String addUser4(@PathVariable String username, @PathVariable String password) {
        System.out.println("username is:" + username);
        System.out.println("password is:" + password);
        return  "parameter4";
    }

    /**
     * 5、使用@ModelAttribute注解获取POST请求的FORM表单数据
     * @param user
     * @return
     */
    @RequestMapping(value = "/addUser5", method = RequestMethod.POST)
    public String addUser5(@ModelAttribute("user") UserModel user) {
        System.out.println("username is:" + user.getUsername());
        System.out.println("password is:" + user.getPassword());
        return "parameter5";
    }

    /**
     * 6、用注解@RequestParam绑定请求参数到方法入参
     * @param username
     * @param password
     * @return
     */
    @RequestMapping(value = "/addUser6", method = RequestMethod.GET)
    public String addUser6(@RequestParam("username") String username, @RequestParam("password") String password) {
        System.out.println("username is:" + username);
        System.out.println("password is:" + password);
        return "parameter6";
    }
}


































































