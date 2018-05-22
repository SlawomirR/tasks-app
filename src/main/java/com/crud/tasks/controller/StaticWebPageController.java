package com.crud.tasks.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.Map;

@Controller
public class StaticWebPageController {

    @GetMapping("/")
    public String index(Map<String, Object> model) {
        model.put("swagger_info", "https://mysterious-dusk-98078.herokuapp.com/swagger-ui.html");
        model.put("swagger_link", "https://mysterious-dusk-98078.herokuapp.com/swagger-ui.html");
        model.put("url_info", "https://slawomirr.github.io");
        model.put("url_link", "https://slawomirr.github.io");
        model.put("trello_info", "https://trello.com/b/r3PFBoTh");
        model.put("trello_link", "https://trello.com/b/r3PFBoTh");
        model.put("variable", "My sample Thymeleaf math calculations:");
        model.put("one", 1);
        model.put("two", 2);
        return "index";
    }
}
