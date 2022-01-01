package pro.ruloff.tasks.service;

import static java.lang.String.format;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;
import org.springframework.stereotype.Service;
import pro.ruloff.tasks.domain.Mail;

@Service
@Slf4j
public class SimpleEmailService {

  private final JavaMailSender javaMailSender;
  private final MailCreatorService mailCreatorService;

  public SimpleEmailService(JavaMailSender javaMailSender,
      MailCreatorService mailCreatorService) {
    this.javaMailSender = javaMailSender;
    this.mailCreatorService = mailCreatorService;
  }

  public void send(final Mail mail) {
    log.info("Starting email preparation...");
    try {
      javaMailSender.send(createMimeMessage(mail));
      log.info("Email has been sent.");
    } catch (MailException e) {
      log.error(format("Failed to process email sending: %s", e.getMessage()), e);
    }
  }

  private MimeMessagePreparator createMimeMessage(final Mail mail) {
    return mimeMessage -> {
      MimeMessageHelper messageHelper = new MimeMessageHelper(mimeMessage);
      messageHelper.setTo(mail.getMailTo());
      messageHelper.setSubject(mail.getSubject());
      messageHelper.setText(
          mailCreatorService.buildTrelloCardEmail(mail.getSubject(), mail.getMessage()), true);
    };
  }

/*
    private SimpleMailMessage createMailMessage(final Mail mail) {
        SimpleMailMessage mailMessage = new SimpleMailMessage();
        mailMessage.setTo(mail.getMailTo());
        if ( ! mail.getToCc().isEmpty()) {
            mailMessage.setCc(mail.getToCc());
        }
        mailMessage.setSubject(mail.getSubject());
        mailMessage.setText(mail.getMessage());
        return mailMessage;
    }
*/
}
