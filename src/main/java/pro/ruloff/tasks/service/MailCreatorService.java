package pro.ruloff.tasks.service;

import java.util.ArrayList;
import java.util.List;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;
import pro.ruloff.tasks.config.AdminConfig;
import pro.ruloff.tasks.config.MailInformationConfig;

@Service
@Slf4j
public class MailCreatorService {

  private final AdminConfig adminConfig;
  private final MailInformationConfig mailInformationConfig;
  private final TemplateEngine templateEngine;

  public MailCreatorService(AdminConfig adminConfig,
      MailInformationConfig mailInformationConfig,
      @Qualifier("templateEngine") TemplateEngine templateEngine) {
    this.adminConfig = adminConfig;
    this.mailInformationConfig = mailInformationConfig;
    this.templateEngine = templateEngine;
  }

  public String buildTrelloCardEmail(String subject, String message) {
    List<String> functionality = new ArrayList<>();
    functionality.add("You can manage your tasks");
    functionality.add("Provides connection with Trello Account");
    functionality.add("Application allows sending tasks to Trello");

    Context context = new Context();
    context.setVariable("message", message);
    context.setVariable("tasks_url", mailInformationConfig.getTaskWebsiteUrl());
    context.setVariable("button", "Visit website");
    context.setVariable("button_to_card", "Check card website");
    context.setVariable("card_website", mailInformationConfig.getTrelloMainBoard());
//        context.setVariable("admin_name", adminConfig.getAdminName());
    context.setVariable("admin_config", adminConfig);
    context.setVariable("goodbye",
        "You'll be informed about future changes in the website service!");
    context.setVariable("company_name", mailInformationConfig.getCompanyName());
    context.setVariable("company_email", mailInformationConfig.getCompanyEmail());
    context.setVariable("company_phone", mailInformationConfig.getCompanyPhone());
    switch (subject.substring(0, 7)) {
      case "Tasks: ":
        log.info(" ===> Tasks:  ===> " + subject.substring(0, 7));
        context.setVariable("is_friend", true);
        context.setVariable("show_buttons", true);
        context.setVariable("show_task_url_button", true);
        context.setVariable("show_trello_url_button", true);
        context.setVariable("print_functionality", false);
        break;
      case "Schedul":
        log.info(" ===> Schedule ===> " + subject.substring(0, 7));
        context.setVariable("is_friend", false);
        context.setVariable("show_buttons", true);
        context.setVariable("show_task_url_button", true);
        context.setVariable("show_trello_url_button", false);
        context.setVariable("print_functionality", false);
        break;
      default:
        log.info(" ===> default ===> " + subject.substring(0, 7));
        context.setVariable("show_buttons", false);
        context.setVariable("print_functionality", true);
        context.setVariable("application_functionality", functionality);
    }
    return templateEngine.process("mail/created-trello-card-mail", context);
  }
}
