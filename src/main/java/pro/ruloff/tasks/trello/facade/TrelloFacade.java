package pro.ruloff.tasks.trello.facade;

import java.util.List;
import org.springframework.stereotype.Component;
import pro.ruloff.tasks.domain.TrelloBoard;
import pro.ruloff.tasks.domain.TrelloCard;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.mapper.TrelloMapper;
import pro.ruloff.tasks.service.TrelloService;
import pro.ruloff.tasks.trello.validator.TrelloValidator;

@Component
public class TrelloFacade {

  private final TrelloService trelloService;
  private final TrelloMapper trelloMapper;
  private final TrelloValidator trelloValidator;

  public TrelloFacade(TrelloService trelloService,
      TrelloMapper trelloMapper,
      TrelloValidator trelloValidator) {
    this.trelloService = trelloService;
    this.trelloMapper = trelloMapper;
    this.trelloValidator = trelloValidator;
  }

  public List<TrelloBoardDto> fetchTrelloBoards() {
    List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
    List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
    return trelloMapper.mapToBoardsDto(filteredBoards);
  }

  public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
    TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
    trelloValidator.validateCard(trelloCard);
    return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
  }
}
