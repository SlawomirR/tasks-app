package pro.ruloff.tasks.config;

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
  private String companyName;

  @Value("${info.company.email}")
  private String companyEmail;

  @Value("${info.company.phone}")
  private String companyPhone;
}
