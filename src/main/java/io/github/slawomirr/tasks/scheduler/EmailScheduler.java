package io.github.slawomirr.tasks.scheduler;

import io.github.slawomirr.tasks.config.AdminConfig;
import io.github.slawomirr.tasks.domain.Mail;
import io.github.slawomirr.tasks.repository.TaskRepository;
import io.github.slawomirr.tasks.service.SimpleEmailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class EmailScheduler {

    private static final String SUBJECT_DAILY = "Scheduled task: Once a day information email.";
    private static final String SUBJECT_PERIODICALLY = "Scheduled task: Detailed information sent on schedule.";
    private static final String SUBJECT_APP_INFO = "Test: Sample application information.";

    @Autowired
    private SimpleEmailService simpleEmailService;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private AdminConfig adminConfig;

//    @Scheduled(fixedDelay = 30_000)
    @Scheduled(cron = "0 0 10 * * *")
    public void sendInformationEmail() {
        long size = taskRepository.count();
        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT_DAILY,
                "Currently in database you got: " + size + " task" + (size == 1L ? "." : "s."))
        );
    }

    @Scheduled(fixedDelay = 186_400_000)
//    @Scheduled(cron = "0 0 10 * * *")
    public void sendDetailedInformationEmail() {
        long size = taskRepository.count();

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT_PERIODICALLY,
                "Currently in database you got: " + size + " task" + (size == 1L ? "." : "s."))
        );
    }

    @Scheduled(fixedDelay = 186_400_000)
//    @Scheduled(cron = "0 0 10 * * *")
    public void sendAppInfoEmail() {
        long size = taskRepository.count();

        simpleEmailService.send(new Mail(
                adminConfig.getAdminMail(),
                "",
                SUBJECT_APP_INFO,
                "")
        );
    }
}
