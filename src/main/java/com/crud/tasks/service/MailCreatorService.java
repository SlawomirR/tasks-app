package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.MailInformationConfig;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.util.ArrayList;
import java.util.List;

@Service
public class MailCreatorService {

    public static final Logger LOGGER = LoggerFactory.getLogger(MailCreatorService.class);

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private MailInformationConfig mailInformationConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

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
        context.setVariable("goodbye", "You'll be informed about future changes in the website service!");
        context.setVariable("company_name", mailInformationConfig.getCompany_name());
        context.setVariable("company_email", mailInformationConfig.getCompany_email());
        context.setVariable("company_phone", mailInformationConfig.getCompany_phone());
        switch (subject.substring(0, 7)) {
            case "Tasks: ":
                LOGGER.info(" ===> Tasks:  ===> " + subject.substring(0, 7));
                context.setVariable("is_friend", true);
                context.setVariable("show_buttons", true);
                context.setVariable("show_task_url_button", true);
                context.setVariable("show_trello_url_button", true);
                context.setVariable("print_functionality", false);
                break;
            case "Schedul":
                LOGGER.info(" ===> Schedul ===> " + subject.substring(0, 7));
                context.setVariable("is_friend", false);
                context.setVariable("show_buttons", true);
                context.setVariable("show_task_url_button", true);
                context.setVariable("show_trello_url_button", false);
                context.setVariable("print_functionality", false);
                break;
            default:
                LOGGER.info(" ===> default ===> " + subject.substring(0, 7));
                context.setVariable("show_buttons", false);
                context.setVariable("print_functionality", true);
                context.setVariable("application_functionality", functionality);
        }
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
