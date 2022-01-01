package pro.ruloff.tasks.controller;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.HashMap;
import java.util.Map;
import org.junit.jupiter.api.Test;

class StaticWebPageControllerTests {

  @Test
  void index() {
    // Given
    StaticWebPageController staticWebPageController = new StaticWebPageController();
    Map<String, Object> testMap = new HashMap<>();
    testMap.put("New", "value");
    // When
    String index = staticWebPageController.index(testMap);
    // Then
    assertTrue(index.length() > 0);
  }
}
