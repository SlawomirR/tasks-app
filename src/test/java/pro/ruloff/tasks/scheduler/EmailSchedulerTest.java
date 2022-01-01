package pro.ruloff.tasks.scheduler;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.ruloff.tasks.config.AdminConfig;
import pro.ruloff.tasks.repository.TaskRepository;
import pro.ruloff.tasks.service.SimpleEmailService;

@ExtendWith(MockitoExtension.class)
class EmailSchedulerTest {

  @InjectMocks
  private EmailScheduler emailScheduler;

  @Mock
  private SimpleEmailService simpleEmailService;

  @Mock
  private TaskRepository taskRepository;

  @Mock
  private AdminConfig adminConfig;

  @Test
  void shouldSendInformationEmail() {
    emailScheduler.sendInformationEmail();
//        Mockito.verify(taskRepository, Mockito.times(1)).count();
    Mockito.verify(taskRepository).count(); // times(1) is default
  }
}
