package com.crud.tasks.mapper;

import com.crud.tasks.domain.TrelloBoard;
import com.crud.tasks.domain.TrelloCard;
import com.crud.tasks.domain.TrelloList;
import com.crud.tasks.domain.dto.TrelloBoardDto;
import com.crud.tasks.domain.dto.TrelloCardDto;
import com.crud.tasks.domain.dto.TrelloListDto;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloMapperTestSuite {
    @Autowired
    private TrelloMapper trelloMapper;

    @Test
    public void testMapToBoards() {
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
        Assert.assertEquals(boardId, boardList.get(0).getId());
        Assert.assertEquals(boardName, boardList.get(0).getName());
        Assert.assertEquals(1, boardList.get(0).getLists().size());
    }

    @Test
    public void testMapToBoardsDto() {
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
        Assert.assertEquals(boardId, boardListDto.get(0).getId());
        Assert.assertEquals(boardName, boardListDto.get(0).getName());
        Assert.assertEquals(1, boardListDto.get(0).getLists().size());
    }

    @Test
    public void testMapToList() {
        // Given
        String listId = "List123456";
        String listName = "List Name";
        boolean listIsClosed = false;

        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto(listId, listName, listIsClosed));
        // When
        List<TrelloList> trelloLists = trelloMapper.mapToList(lists);
        // Then
        Assert.assertEquals(listId, trelloLists.get(0).getId());
        Assert.assertEquals(listName, trelloLists.get(0).getName());
        Assert.assertEquals(listIsClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToListDto() {
        // Given
        String listId = "List123456";
        String listName = "List Name";
        boolean listIsClosed = false;

        List<TrelloList> lists = new ArrayList<>();
        lists.add(new TrelloList(listId, listName, listIsClosed));
        // When
        List<TrelloListDto> trelloLists = trelloMapper.mapToListDto(lists);
        // Then
        Assert.assertEquals(listId, trelloLists.get(0).getId());
        Assert.assertEquals(listName, trelloLists.get(0).getName());
        Assert.assertEquals(listIsClosed, trelloLists.get(0).isClosed());
    }

    @Test
    public void testMapToCard() {
        // Given
        String cardName = "Card Name";
        String cardDescription = "Card Desc";
        String cardPos = "Card Pos";
        String cardListId = "ListId123456";
        TrelloCardDto trelloCardDto = new TrelloCardDto(cardName, cardDescription, cardPos, cardListId);
        // When
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        // Then
        Assert.assertEquals(cardName, trelloCard.getName());
        Assert.assertEquals(cardDescription, trelloCard.getDescription());
        Assert.assertEquals(cardPos, trelloCard.getPos());
        Assert.assertEquals(cardListId, trelloCard.getListId());
    }

    @Test
    public void testMapToCardDto() {
        // Given
        String cardName = "Card Name";
        String cardDescription = "Card Desc";
        String cardPos = "Card Pos";
        String cardListId = "ListId123456";
        TrelloCard trelloCard = new TrelloCard(cardName, cardDescription, cardPos, cardListId);
        // When
        TrelloCardDto trelloCardDto = trelloMapper.mapToCardDto(trelloCard);
        // Then
        Assert.assertEquals(cardName, trelloCardDto.getName());
        Assert.assertEquals(cardDescription, trelloCardDto.getDescription());
        Assert.assertEquals(cardPos, trelloCardDto.getPos());
        Assert.assertEquals(cardListId, trelloCardDto.getListId());
    }
}
