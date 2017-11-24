package com.meiya;

//@ModelAttribute和 @SessionAttributes

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class BaseController {

    /*虽然此处注入的是三个不同的类型（Model model, Map model2, ModelMap model3），但三者是同一个对象。

    AnnotationMethodHandlerAdapter
    和RequestMappingHandlerAdapter将使用BindingAwareModelMap作为模型对象的实现，
    即此处我们的形参（Model model, Map model2, ModelMap model3）
    都是同一个BindingAwareModelMap实例。*/
    @RequestMapping("save")
    public String save( Model model, Map model2, ModelMap modelMap) {
        User user1 = new User();
        user1.setUserName("U love me");
        model.addAttribute("user",user1);
        model2.put("b","b");
        modelMap.put("c","c");
        System.out.println(model == model2);
        System.out.println(model2 == modelMap);
        return "save";
    }
    //几种不同模型数据替换规则
    @RequestMapping(value = "/mergeModel")
    public ModelAndView mergeModel(Model model) {
        model.addAttribute("a","a"); //①添加模型数据
        ModelAndView modelAndView = new ModelAndView("save");
        modelAndView.addObject("a", "update"); //②在视图渲染之前更新③处同名模型数据
        model.addAttribute("a", "new"); //③修改①处同名模型数据
        return modelAndView;
    }

    /**
     * @ModelAttribute 使用
     *  会添加一个key为"user"的user对象，还会添加一个key为"user1"的user对象
     *  第一种
     */
/*    @ModelAttribute("user1")
    public User addUser(User user) {
        user.setUserName("hehe");
        user.setId(1);
        return user;
    }*/
    @RequestMapping("modelAttributeShow")
    public String modelAttributeShow() {
        return "modelAttribute";
    }

    //第二种  此方法会先从model去获取key为"user"的对象,
    // 如果获取不到会通过反射实例化一个User对象,再从request里面拿值set到这个对象,
    // 然后把这个User对象添加到model(其中key为"user").
/*    @RequestMapping("saveData")
    public String saveData(@ModelAttribute User user) {
        user.setUserName("u love me");
        return "saveData";
    }*/

    //第三种   先产生两个User类型的对象  一个key是user 一个key是user1  一个改变另一个也改变
    @ModelAttribute("user1")
    public User addUser(User user) {
        user.setUserName("haha");
        user.setId(1);
        return user;
    }
 /*   @RequestMapping("saveData")  //先从model去找key为user的对象，如果能找到 并且重新set 会改变其属性值             三个user对象都一样  一个改变全部改变
    public String save(@ModelAttribute User user) {
        user.setUserName("hello everyDay");
        user.setId(2);
        return "saveData";
    }
*/



    //第四种
    //另类ModelAttribute的使用
    //会添加一个key为"user"的User对象到model，还会添加一个key为"string",value为"result"的对象到model,而视图名称则变为了"save"而不是"result"
    @ModelAttribute
    @RequestMapping("index")
    public String save(@ModelAttribute User user) {
        user.setUserName("zzzz");
        return "saveData";
    }






}
