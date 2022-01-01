package pro.ruloff.tasks.trello.config;

import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrelloConfigTest {

  @Autowired
  private TrelloConfig trelloConfig;

  @Test
  void getTrelloApiEndpoint() {
    assertTrue(trelloConfig.getTrelloApiEndpoint().length() > 0);
  }

  @Test
  void getTrelloAppKey() {
    assertTrue(trelloConfig.getTrelloAppKey().length() > 0);
  }

  @Test
  void getTrelloToken() {
    assertTrue(trelloConfig.getTrelloToken().length() > 0);
  }

  @Test
  void getUsername() {
    assertTrue(trelloConfig.getUsername().length() > 0);
  }
}
