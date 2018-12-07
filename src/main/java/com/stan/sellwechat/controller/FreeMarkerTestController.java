package com.stan.sellwechat.controller;

import com.stan.sellwechat.domain.FreeMarkTest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class FreeMarkerTestController {

    //@RequestParam url参数   @RequestBody 请求中body里的参数
    //不加@RequestParam注解，也能直接取出URL后面的参数，即参数可以与定义的类,或是字段互相自动转化。
    //不只是Get可以，Post也可以接受url参数
    //@RequestParam可用于设置参数默认值，以及是否必要，还有对应传入来的json某字段名字与参数名字不同, @RequestParam("username") String userName 这样子
    // ，因为前后端命名可能规则不同,当然直接在bean中用@JsonProperty("username")来对应也行

    //@RequestBody这个一般处理的是在ajax请求中声明contentType: “application/json; charset=utf-8”时候。
    // 也就是json数据格式或者 xml 数据格式
    // 1、@requestBody注解常用来处理content-type不是默认的application/x-www-form-urlcoded编码的内容，
    // 比如说：application/json或者是application/xml等。一般情况下来说常用其来处理application/json类型。

    //@ResponseBody注解的作用是将controller的方法返回的对象通过适当的转换器转换为指定的格式之后，
    // 写入到response对象的body区，通常用来返回JSON数据或者是XML

    //@PathVariable：如参数为@PathVariable String id,则对应@xxxMapping("xxx/{id}/xxx") ，这只是个例子

    //@RestController = @ResponseBody + @Controller



    /**
     * 在url中传入id=123和name=你煞笔，会分别注入到参数id和freeMarkTest中,在post情况下，在body中传入的也可以注入到参数中
     * 我用post请求，在body中传过来一个名为bodykey=wuha和名为id=456的字段
     *
     * 结果为参数id=123，456      freeMarkTest.id=123,456  freeMarkTest.name=你煞笔      bodykey=wuha
     * 方法中前两个参数加和不加@RquestParam结果都一样，都收到了url和body中的id，所以我认为不加的话应该默认为@RequestParam
     */
    @PostMapping("/freemarker")
    public ModelAndView test(@RequestParam String id, FreeMarkTest freeMarkTest,String bodykey) {
        ModelAndView modelAndView = new ModelAndView();
        modelAndView.addObject("idtest",id);
        modelAndView.addObject("bean",freeMarkTest);
        modelAndView.addObject("bodykey",bodykey);
        modelAndView.setViewName("freemarkertest");
        return modelAndView;
    }
}
