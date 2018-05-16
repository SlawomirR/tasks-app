package com.crud.tasks.service;

import com.crud.tasks.config.AdminConfig;
import com.crud.tasks.config.MailInformationConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

@Service
public class MailCreatorService {

    @Autowired
    private AdminConfig adminConfig;

    @Autowired
    private MailInformationConfig mailInformationConfig;

    @Autowired
    @Qualifier("templateEngine")
    private TemplateEngine templateEngine;

    public String buildTrelloCardEmail(String message) {
        Context context = new Context();
        context.setVariable("message", message);
        context.setVariable("tasks_url", mailInformationConfig.getTaskWebsiteUrl());
        context.setVariable("button", "Visit website");
        context.setVariable("button_to_card", "Check card website");
        context.setVariable("card_website", mailInformationConfig.getTrelloMainBoard());
        context.setVariable("admin_name", adminConfig.getAdminName());
        context.setVariable("goodbye", "You'll be informed about future changes in the website service!");
        context.setVariable("company_name", mailInformationConfig.getCompany_name());
        context.setVariable("company_email", mailInformationConfig.getCompany_email());
        context.setVariable("company_phone", mailInformationConfig.getCompany_phone());
        return templateEngine.process("mail/created-trello-card-mail", context);
    }
}
