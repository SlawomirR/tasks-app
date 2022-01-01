package pro.ruloff.tasks.trello.validator;

import java.util.List;
import java.util.stream.Collectors;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import pro.ruloff.tasks.domain.TrelloBoard;
import pro.ruloff.tasks.domain.TrelloCard;

@Component
@Slf4j
public class TrelloValidator {

  public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
    log.info("Starting filtering boards ...");
    List<TrelloBoard> filteredBoards = trelloBoards.stream()
//                .filter(trelloBoard -> ! trelloBoard.getName().equalsIgnoreCase("test"))
        .filter(trelloBoard -> !trelloBoard.getName().toLowerCase().contains("test"))
        .collect(Collectors.toList());
    log.info("Boards have been filtered. Current list size: {} of {}",
        filteredBoards.size(), trelloBoards.size());
    return filteredBoards;
  }

  public boolean validateCard(final TrelloCard trelloCard) {
    if (trelloCard.getName().toLowerCase().contains("test")) {
      log.info("Someone is testing my application!");
      return false;
    } else {
      log.info("Seems that my application id used in proper way.");
      return true;
    }
  }
}
