package pro.ruloff.tasks.mapper;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import pro.ruloff.tasks.domain.TrelloBoard;
import pro.ruloff.tasks.domain.TrelloCard;
import pro.ruloff.tasks.domain.TrelloList;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloListDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrelloMapperTest {

  @Autowired
  private TrelloMapper trelloMapper;

  @Test
  void testMapToBoards() {
    // Given
    String listId = "List123456";
    String listName = "List Name";
    boolean listIsClosed = false;
    String boardName = "Board Name";
    String boardId = "Board123456";

    List<TrelloListDto> lists = new ArrayList<>();
    lists.add(new TrelloListDto(listId, listName, listIsClosed));

    List<TrelloBoardDto> trelloBoardDto = new ArrayList<>();
    trelloBoardDto.add(new TrelloBoardDto(boardId, boardName, lists));
    // When
    List<TrelloBoard> boardList = trelloMapper.mapToBoards(trelloBoardDto);
    // Then
    assertEquals(boardId, boardList.get(0).getId());
    assertEquals(boardName, boardList.get(0).getName());
    assertEquals(1, boardList.get(0).getLists().size());
  }

  @Test
  void testMapToBoardsDto() {
    // Given
    String listId = "List123456";
    String listName = "List Name";
    boolean listIsClosed = false;
    String boardName = "Board Name";
    String boardId = "Board123456";

    List<TrelloList> lists = new ArrayList<>();
    lists.add(new TrelloList(listId, listName, listIsClosed));

    List<TrelloBoard> trelloBoard = new ArrayList<>();
    trelloBoard.add(new TrelloBoard(boardId, boardName, lists));
    // When
    List<TrelloBoardDto> boardListDto = trelloMapper.mapToBoardsDto(trelloBoard);
    // Then
    assertEquals(boardId, boardListDto.get(0).getId());
    assertEquals(boardName, boardListDto.get(0).getName());
    assertEquals(1, boardListDto.get(0).getLists().size());
  }

  @Test
  void testMapToList() {
    // Given
    String listId = "List123456";
    String listName = "List Name";
    boolean listIsClosed = false;

    List<TrelloListDto> lists = new ArrayList<>();
    lists.add(new TrelloListDto(listId, listName, listIsClosed));
    // When
    List<TrelloList> trelloLists = trelloMapper.mapToList(lists);
    // Then
    assertEquals(listId, trelloLists.get(0).getId());
    assertEquals(listName, trelloLists.get(0).getName());
    assertEquals(listIsClosed, trelloLists.get(0).isClosed());
  }

  @Test
  void testMapToListDto() {
    // Given
    String listId = "List123456";
    String listName = "List Name";
    boolean listIsClosed = false;

    List<TrelloList> lists = new ArrayList<>();
    lists.add(new TrelloList(listId, listName, listIsClosed));
    // When
    List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(lists);
    // Then
    assertEquals(listId, trelloLists.get(0).getId());
    assertEquals(listName, trelloLists.get(0).getName());
    assertEquals(listIsClosed, trelloLists.get(0).isClosed());
  }

  @Test
  void testMapToCard() {
    // Given
    String cardName = "Card Name";
    String cardDescription = "Card Desc";
    String cardPos = "Card Pos";
    String cardListId = "ListId123456";
    TrelloCardDto trelloCardDto = new TrelloCardDto(cardName, cardDescription, cardPos, cardListId);
    // When
    TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
    // Then
    assertEquals(cardName, trelloCard.getName());
    assertEquals(cardDescription, trelloCard.getDescription());
    assertEquals(cardPos, trelloCard.getPos());
    assertEquals(cardListId, trelloCard.getListId());
  }

  @Test
  void testMapToCardDto() {
    // Given
    String cardName = "Card Name";
    String cardDescription = "Card Desc";
    String cardPos = "Card Pos";
    String cardListId = "ListId123456";
    TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
    // When
    TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
    // Then
    assertEquals(cardName, trelloCardDto.getName());
    assertEquals(cardDescription, trelloCardDto.getDescription());
    assertEquals(cardPos, trelloCardDto.getPos());
    assertEquals(cardListId, trelloCardDto.getListId());
  }
}
