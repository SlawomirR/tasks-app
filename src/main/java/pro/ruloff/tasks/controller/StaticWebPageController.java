package pro.ruloff.tasks.controller;

import java.util.Map;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class StaticWebPageController {

  @GetMapping("/")
  public String index(Map<String, Object> model) {
    model.put("swagger_info", "https://tasks-app-slawomirr.herokuapp.com/swagger-ui.html");
    model.put("swagger_link", "https://tasks-app-slawomirr.herokuapp.com/swagger-ui.html");
    model.put("url_info", "https://slawomirr.github.io/tasks-app_front-end");
    model.put("url_link", "https://slawomirr.github.io/tasks-app_front-end");
    model.put("trello_info", "https://trello.com/b/r3PFBoTh");
    model.put("trello_link", "https://trello.com/b/r3PFBoTh");
    model.put("variable", "My sample Thymeleaf math calculations:");
    model.put("one", 1);
    model.put("two", 2);
    return "index";
  }
}
