package pro.ruloff.tasks.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrelloCard {

  private String name;
  private String description;
  private String pos;
  private String listId;
}
