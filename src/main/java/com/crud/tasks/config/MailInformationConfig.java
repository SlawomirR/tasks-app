package com.crud.tasks.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class MailInformationConfig {

    @Value("${trello.main.board}")
    private String trelloMainBoard;

    @Value("${task.website.url}")
    private String taskWebsiteUrl;

    @Value("${info.company.name}")
    private String company_name;

    @Value("${info.company.email}")
    private String company_email;

    @Value("${info.company.phone}")
    private String company_phone;
}
