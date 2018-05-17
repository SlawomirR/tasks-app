package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @RequestMapping("/")
    public String index(Map<String, Object> model) {
        model.put("url_info", "https://slawomirr.github.io");
        model.put("url_link", "https://slawomirr.github.io");
        model.put("variable", "My sample Thymeleaf math calculations:");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }
}
