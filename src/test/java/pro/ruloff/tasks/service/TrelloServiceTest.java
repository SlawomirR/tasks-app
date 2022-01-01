package pro.ruloff.tasks.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.ruloff.tasks.config.AdminConfig;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloListDto;
import pro.ruloff.tasks.trello.client.TrelloClient;

@ExtendWith(MockitoExtension.class)
public class TrelloServiceTest {

  @InjectMocks
  private TrelloService trelloService;

  @Mock
  private TrelloClient trelloClient;

  @Mock
  private AdminConfig adminConfig;

  @Mock
  private SimpleEmailService emailService;

  @Test
  void shouldFetchTrelloBoards() {
    // Given
    List<TrelloListDto> lists = new ArrayList<>();
    lists.add(new TrelloListDto("2", "Test", false));
    List<TrelloBoardDto> boards = new ArrayList<>();
    boards.add(new TrelloBoardDto("123", "Test", lists));

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
    assertFalse(fetchedTrelloBoards.get(0).getLists().get(0).isClosed());
  }

  @Test
  void shouldCreateTrelloCardTest() {
    // Given
    TrelloCardDto trelloCardDto = new TrelloCardDto("First card", "Test card", "Top", "1");
    CreatedTrelloCardDto cardDtoStub = new CreatedTrelloCardDto("Test Id", "Testing", "Test URL");

    when(trelloClient.createNewCard(trelloCardDto)).thenReturn(cardDtoStub);
    // When
    CreatedTrelloCardDto createdTrelloCardDto = trelloService.createTrelloCard(trelloCardDto);
    // Then
    assertEquals("Test Id", createdTrelloCardDto.getId());
    assertEquals("Testing", createdTrelloCardDto.getName());
    assertEquals("Test URL", createdTrelloCardDto.getShortUrl());
  }
}
