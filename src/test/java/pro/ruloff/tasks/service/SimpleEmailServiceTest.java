package pro.ruloff.tasks.service;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessagePreparator;
import pro.ruloff.tasks.domain.Mail;

@ExtendWith(MockitoExtension.class)
public class SimpleEmailServiceTest {

  @InjectMocks
  private SimpleEmailService simpleEmailService;

  @Mock
  private JavaMailSender javaMailSender;

  @Test
  void shouldSendEmail() {
    // Given
    Mail mail = new Mail("test@test.com", "", "Test", "Test message");

    SimpleMailMessage mailMessage = new SimpleMailMessage();
    mailMessage.setTo(mail.getMailTo());
    mailMessage.setSubject(mail.getSubject());
    mailMessage.setText(mail.getMessage());
    // When
    simpleEmailService.send(mail);
    // Then
    Mockito.verify(javaMailSender, times(1)).send(any(MimeMessagePreparator.class));

  }
}
