package com.crud.tasks.service;

import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloListDto;
import com.crud.tasks.trello.client.TrelloClient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class TrelloServiceTestSuite {
    @InjectMocks
    private TrelloService trelloService;

    @Mock
    private TrelloClient trelloClient;

    @Test
    public void shouldFetchTrelloBoards() {
        // Given
        List<TrelloListDto> lists = new ArrayList<>();
        lists.add(new TrelloListDto("2", "Test", false));
        List<TrelloBoardDto> boards = new ArrayList<>();
        boards.add(new TrelloBoardDto("123", "Test",lists));

        when(trelloClient.getTrelloBoards()).thenReturn(boards);

        // When
        List<TrelloBoardDto> fetchedTrelloBoards = trelloService.fetchTrelloBoards();

        // Then
        assertEquals(1, fetchedTrelloBoards.size());
        assertEquals("123", fetchedTrelloBoards.get(0).getId());
        assertEquals("Test", fetchedTrelloBoards.get(0).getName());
        assertEquals(1, fetchedTrelloBoards.get(0).getLists().size());
        assertEquals("2", fetchedTrelloBoards.get(0).getLists().get(0).getId());
        assertEquals("Test", fetchedTrelloBoards.get(0).getLists().get(0).getName());
        assertEquals(false, fetchedTrelloBoards.get(0).getLists().get(0).isClosed());
    }
}