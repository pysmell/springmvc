package com.meiya;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

/*@Controller
@ResponseBody*/
//@RestController

/**
 * requestMapping6个参数使用  value，method，consumes，produces，params，header使用
 *value：     指定请求的实际地址，指定的地址可以是URI Template 模式
 *method：  指定请求的method类型， GET、POST、PUT、DELETE等；
 *consumes： 指定处理请求的提交内容类型（Content-Type），例如application/json, text/html;
 * produces:    指定返回的内容类型，仅当request请求头中的(Accept)类型中包含该指定类型才返回；
 * params： 指定request中必须包含某些参数值是，才让该方法处理。
 *headers： 指定request中必须包含某些指定的header值，才能让该方法处理请求。
 *
 */
@Controller
public class HelloController {
    @ResponseBody
    @RequestMapping(value = "/index", method = RequestMethod.GET,consumes ="text/html",produces="text/plain;charset=UTF-8",headers = "Accept=text/plain")
    public String hello() {
        String jsonData = "{\"username\":\"zhang\", \"password\":\"123\"}";
//        return "index";
        return jsonData;
    }

/*    @Path("indexTwo")
    @GET
//    @Produces(MediaType.TEXT_PLAIN)
    public String indexTwo() {
        return "indexTwo";
    }*/

    /**
     * 控制header的content-type和返回的状态码
     * @return 返回文本内容和header 状态码
     */
    @RequestMapping("/entity/headers")
    public ResponseEntity<String> responseEntityCustomHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.TEXT_PLAIN);
        return new ResponseEntity<String>("The String ResponseBody with custom header Content-Type=text/plain", headers, HttpStatus.OK);
    }


}




