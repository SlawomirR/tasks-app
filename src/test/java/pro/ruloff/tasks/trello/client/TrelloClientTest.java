package pro.ruloff.tasks.trello.client;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.client.RestTemplate;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.trello.config.TrelloConfig;

@ExtendWith(MockitoExtension.class)
class TrelloClientTest {

  @InjectMocks
  private TrelloClient trelloClient;

  @Mock
  private RestTemplate restTemplate;

  @Mock
  private TrelloConfig trelloConfig;

  @BeforeEach
  public void init() {
    when(trelloConfig.getTrelloApiEndpoint()).thenReturn("http://test.com/1");
    when(trelloConfig.getTrelloAppKey()).thenReturn("test");
    when(trelloConfig.getTrelloToken()).thenReturn("test");
  }

  @Test
  void shouldFetchTrelloBoards() throws URISyntaxException {
    // Given
    TrelloBoardDto[] trelloBoards = new TrelloBoardDto[1];
    trelloBoards[0] = new TrelloBoardDto("test_id", "test_board", new ArrayList<>());

    URI uri = new URI(
        "http://test.com/1/members/USERNAME/boards?key=test&token=test&fields=id,name,closed&lists=all");

    when(trelloConfig.getUsername()).thenReturn("USERNAME");
    when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(trelloBoards);
    // When
    List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
    // Then
    assertEquals(1, fetchedTrelloBoards.size());
    assertEquals("test_id", fetchedTrelloBoards.get(0).getId());
    assertEquals("test_board", fetchedTrelloBoards.get(0).getName());
    assertEquals(new ArrayList<>(), fetchedTrelloBoards.get(0).getLists());
  }

  @Test
  void shouldCreateNewCard() throws URISyntaxException {
    // Given
    TrelloCardDto trelloCardDto = new TrelloCardDto(
        "Test task name",
        "Test Description",
        "top",
        "test_id"
    );

    URI uri = new URI(
        "http://test.com/1/cards?key=test&token=test&name=Test%20task%20name&desc=Test%20Description&pos=top&idList=test_id");

    CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
        "1",
        "Test task name",
        "http://test.com"
    );

    when(restTemplate.postForObject(uri, null, CreatedTrelloCardDto.class))
        .thenReturn(createdTrelloCardDto);
    // When
    CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
    // Then
    assertEquals("1", newCard.getId());
    assertEquals("Test task name", newCard.getName());
    assertEquals("http://test.com", newCard.getShortUrl());
  }

  @Test
  void shouldReturnEmptyList() throws URISyntaxException {
    // Given
    URI uri = new URI(
        "http://test.com/1/members/USERNAME/boards?key=test&token=test&fields=id,name,closed&lists=all");
    when(trelloConfig.getUsername()).thenReturn("USERNAME");
    when(restTemplate.getForObject(uri, TrelloBoardDto[].class)).thenReturn(null);
    // When
    List<TrelloBoardDto> fetchedTrelloBoards = trelloClient.getTrelloBoards();
    // Then
    assertEquals(0, fetchedTrelloBoards.size());
  }
}
