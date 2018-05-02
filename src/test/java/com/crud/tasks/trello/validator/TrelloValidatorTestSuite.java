package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloValidatorTestSuite {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    @Autowired
    private TrelloValidator trelloValidator;

    @Test
    public void testValidateTrelloBoards() {
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
        Assert.assertEquals(0, validateTrelloBoardList.size());
    }

    @Test
    public void testValidateCardTrue() {
        // Given
        String cardName = "Card Name";
        String cardDescription = "Card Desc";
        String cardPos = "Card Pos";
        String cardListId = "ListId123456";
        TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
        // When
        boolean isValidate = trelloValidator.validateCard(trelloCard);
        // Then
        Assert.assertTrue(isValidate);
    }

    @Test
    public void testValidateCardFalse() {
        // Given
        String cardName = "Card teSt";
        String cardDescription = "Card Desc";
        String cardPos = "Card Pos";
        String cardListId = "ListId123456";
        TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
        // When
        boolean isValidate = trelloValidator.validateCard(trelloCard);
        // Then
        Assert.assertFalse(isValidate);
    }
}
