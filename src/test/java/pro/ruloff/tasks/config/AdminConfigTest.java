package pro.ruloff.tasks.config;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class AdminConfigTest {

  @Autowired
  private AdminConfig adminConfig;

  @Test
  void testGetAdminMail() {
    assertThat(adminConfig.getAdminMail()).contains("@");
  }
}
