package pro.ruloff.tasks.mapper;

import java.util.List;
import java.util.stream.Collectors;
import org.springframework.stereotype.Component;
import pro.ruloff.tasks.domain.TrelloBoard;
import pro.ruloff.tasks.domain.TrelloCard;
import pro.ruloff.tasks.domain.TrelloList;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloListDto;

@Component
public class TrelloMapper {

  public List<TrelloBoard> mapToBoards(final List<TrelloBoardDto> trelloBoardDto) {
    return trelloBoardDto.stream()
        .map(trelloBoard -> new TrelloBoard(trelloBoard.getId(), trelloBoard.getName(),
            mapToList(trelloBoard.getLists())))
        .collect(Collectors.toList());
  }

  public List<TrelloBoardDto> mapToBoardsDto(final List<TrelloBoard> trelloBoards) {
    return trelloBoards.stream()
        .map(trelloBoard -> new TrelloBoardDto(trelloBoard.getId(), trelloBoard.getName(),
            mapToListDto(trelloBoard.getLists())))
        .collect(Collectors.toList());
  }

  public List<TrelloList> mapToList(final List<TrelloListDto> trelloListDto) {
    return trelloListDto.stream()
        .map(trelloList -> new TrelloList(trelloList.getId(), trelloList.getName(),
            trelloList.isClosed()))
        .collect(Collectors.toList());
  }

  public List<TrelloListDto> mapToListDto(final List<TrelloList> trelloListLists) {
    return trelloListLists.stream()
        .map(trelloList -> new TrelloListDto(trelloList.getId(), trelloList.getName(),
            trelloList.isClosed()))
        .collect(Collectors.toList());
  }

  public TrelloCard mapToCard(final TrelloCardDto trelloCardDto) {
    return new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(),
        trelloCardDto.getPos(), trelloCardDto.getListId());
  }

  public TrelloCardDto mapToCardDto(final TrelloCard trelloCard) {
    return new TrelloCardDto(trelloCard.getName(), trelloCard.getDescription(),
        trelloCard.getPos(), trelloCard.getListId());
  }
}
