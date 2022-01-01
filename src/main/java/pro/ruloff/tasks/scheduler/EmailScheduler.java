package pro.ruloff.tasks.scheduler;

import static java.lang.String.format;

import java.util.List;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import pro.ruloff.tasks.config.AdminConfig;
import pro.ruloff.tasks.domain.Mail;
import pro.ruloff.tasks.domain.Task;
import pro.ruloff.tasks.repository.TaskRepository;
import pro.ruloff.tasks.service.SimpleEmailService;

@Component
public class EmailScheduler {

  private static final String SUBJECT_DAILY = "Scheduled task: Once a day information email.";
  private static final String SUBJECT_PERIODICALLY = "Scheduled task: Detailed information sent on schedule.";
  private static final String SUBJECT_APP_INFO = "Test: Sample application information.";

  private final SimpleEmailService simpleEmailService;
  private final TaskRepository taskRepository;
  private final AdminConfig adminConfig;

  public EmailScheduler(SimpleEmailService simpleEmailService,
      TaskRepository taskRepository, AdminConfig adminConfig) {
    this.simpleEmailService = simpleEmailService;
    this.taskRepository = taskRepository;
    this.adminConfig = adminConfig;
  }

  //    @Scheduled(fixedDelay = 30_000)
  @Scheduled(cron = "0 0 10 * * *")
  public void sendInformationEmail() {
    long size = taskRepository.count();
    final List<Task> taskList = taskRepository.findAll();
    simpleEmailService.send(new Mail(
        adminConfig.getAdminMail(),
        "",
        SUBJECT_DAILY,
        format("(cron scheduled) Currently in database you got: %s task%s.%nThere are: %s",
            size, size == 1L ? "" : "s", taskList))
    );
  }

  @Scheduled(fixedDelay = 186_400_000)
//    @Scheduled(cron = "0 0 10 * * *")
  public void sendDetailedInformationEmail() {
    final long size = taskRepository.count();
    final List<Task> taskList = taskRepository.findAll();
    simpleEmailService.send(new Mail(
        adminConfig.getAdminMail(),
        "",
        SUBJECT_PERIODICALLY,
        format("(cron fixedDelay) Currently in database you got: %s task%s.%nThere are: %s",
            size, size == 1L ? "" : "s", taskList))
    );
  }

  @Scheduled(fixedDelay = 186_400_000)
//    @Scheduled(cron = "0 0 10 * * *")
  public void sendAppInfoEmail() {
    simpleEmailService.send(new Mail(
        adminConfig.getAdminMail(),
        "",
        SUBJECT_APP_INFO,
        "")
    );
  }
}
