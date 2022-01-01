package pro.ruloff.tasks.domain;

import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@AllArgsConstructor
public class TrelloBoard {

  private String id;
  private String name;
  private List<TrelloList> lists;
}
