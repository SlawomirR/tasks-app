package pro.ruloff.tasks.domain.dto;

import lombok.Data;

@Data
public class TaskDto {

  private final Long id;
  private final String title;
  private final String content;
}
