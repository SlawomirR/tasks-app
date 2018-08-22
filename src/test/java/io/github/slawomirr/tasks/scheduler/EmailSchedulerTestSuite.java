package io.github.slawomirr.tasks.scheduler;

import io.github.slawomirr.tasks.config.AdminConfig;
import io.github.slawomirr.tasks.repository.TaskRepository;
import io.github.slawomirr.tasks.service.SimpleEmailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

@RunWith(MockitoJUnitRunner.class)
public class EmailSchedulerTestSuite {
    @InjectMocks
    private EmailScheduler emailScheduler;

    @Mock
    private SimpleEmailService simpleEmailService;

    @Mock
    private TaskRepository taskRepository;

    @Mock
    private AdminConfig adminConfig;

    @Test
    public void shouldSendInformationEmail() {
        emailScheduler.sendInformationEmail();
//        Mockito.verify(taskRepository, Mockito.times(1)).count();
        Mockito.verify(taskRepository).count(); // times(1) is default
    }
}
