package pro.ruloff.tasks.controller;

import java.util.List;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.trello.facade.TrelloFacade;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {

  private final TrelloFacade trelloFacade;

  public TrelloController(TrelloFacade trelloFacade) {
    this.trelloFacade = trelloFacade;
  }

  @GetMapping(value = "/boards")
  public List<TrelloBoardDto> getTrelloBoards() {
    return trelloFacade.fetchTrelloBoards();
  }

  @PostMapping(value = "/cards")
  public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
    return trelloFacade.createCard(trelloCardDto);
  }
}
