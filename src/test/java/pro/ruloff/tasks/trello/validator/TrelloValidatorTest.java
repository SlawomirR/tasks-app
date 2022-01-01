package pro.ruloff.tasks.trello.validator;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

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

@ExtendWith(SpringExtension.class)
@SpringBootTest
class TrelloValidatorTest {

  @Autowired
  private TrelloValidator trelloValidator;

  @Test
  void testValidateTrelloBoards() {
    // Given
    String listId = "List123456";
    String listName = "List Name";
    boolean listIsClosed = false;
    String boardName = "TeSt";
    String boardId = "Board123456";

    List<TrelloList> lists = new ArrayList<>();
    lists.add(new TrelloList(listId, listName, listIsClosed));

    List<TrelloBoard> trelloBoard = new ArrayList<>();
    trelloBoard.add(new TrelloBoard(boardId, boardName, lists));
    // When
    List<TrelloBoard> validateTrelloBoardList = trelloValidator.validateTrelloBoards(trelloBoard);
    //Then
    assertEquals(0, validateTrelloBoardList.size());
  }

  @Test
  void testValidateCardTrue() {
    // Given
    String cardName = "Card Name";
    String cardDescription = "Card Desc";
    String cardPos = "Card Pos";
    String cardListId = "ListId123456";
    TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
    // When
    boolean isValidate = trelloValidator.validateCard(trelloCard);
    // Then
    assertTrue(isValidate);
  }

  @Test
  void testValidateCardFalse() {
    // Given
    String cardName = "Card teSt";
    String cardDescription = "Card Desc";
    String cardPos = "Card Pos";
    String cardListId = "ListId123456";
    TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
    // When
    boolean isValidate = trelloValidator.validateCard(trelloCard);
    // Then
    assertFalse(isValidate);
  }
}
