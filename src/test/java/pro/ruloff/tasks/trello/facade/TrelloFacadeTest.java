package pro.ruloff.tasks.trello.facade;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import pro.ruloff.tasks.domain.TrelloBoard;
import pro.ruloff.tasks.domain.TrelloCard;
import pro.ruloff.tasks.domain.TrelloList;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloListDto;
import pro.ruloff.tasks.mapper.TrelloMapper;
import pro.ruloff.tasks.service.TrelloService;
import pro.ruloff.tasks.trello.validator.TrelloValidator;

@ExtendWith(MockitoExtension.class)
public class TrelloFacadeTest {

  @InjectMocks
  private TrelloFacade trelloFacade;

  @Mock
  private TrelloService trelloService;

  @Mock
  private TrelloValidator trelloValidator;

  @Mock
  private TrelloMapper trelloMapper;

  @Test
  void shouldFetchEmptyList() {
    // Given
    List<TrelloListDto> trelloLists = new ArrayList<>();
    trelloLists.add(new TrelloListDto("1", "test_list", false));

    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    trelloBoards.add(new TrelloBoardDto("1", "test", trelloLists));

    List<TrelloList> mappedTrelloLists = new ArrayList<>();
    mappedTrelloLists.add(new TrelloList("1", "test_list", false));

    List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
    mappedTrelloBoards.add(new TrelloBoard("1", "test", mappedTrelloLists));

    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
    when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(Mockito.anyList())).thenReturn(new ArrayList<>());
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(new ArrayList<>());
    // When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
    // Then
    assertNotNull((trelloBoardDtos));
    assertEquals(0, trelloBoardDtos.size());
  }

  @Test
  void shouldFetchTrelloBoards() {
    // Given
    List<TrelloListDto> trelloLists = new ArrayList<>();
    trelloLists.add(new TrelloListDto("1", "my_list", false));

    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    trelloBoards.add(new TrelloBoardDto("1", "my_task", trelloLists));

    List<TrelloList> mappedTrelloLists = new ArrayList<>();
    mappedTrelloLists.add(new TrelloList("1", "my_list", false));

    List<TrelloBoard> mappedTrelloBoards = new ArrayList<>();
    mappedTrelloBoards.add(new TrelloBoard("1", "my_task", mappedTrelloLists));

    when(trelloService.fetchTrelloBoards()).thenReturn(trelloBoards);
    when(trelloMapper.mapToBoards(trelloBoards)).thenReturn(mappedTrelloBoards);
    when(trelloMapper.mapToBoardsDto(Mockito.anyList())).thenReturn(trelloBoards);
    when(trelloValidator.validateTrelloBoards(mappedTrelloBoards)).thenReturn(mappedTrelloBoards);
    // When
    List<TrelloBoardDto> trelloBoardDtos = trelloFacade.fetchTrelloBoards();
    // Then
    assertNotNull((trelloBoardDtos));
    assertEquals(1, trelloBoardDtos.size());

    trelloBoardDtos.forEach(trelloBoardDto -> {
      assertEquals("1", trelloBoardDto.getId());
      assertEquals("my_task", trelloBoardDto.getName());

      trelloBoardDto.getLists().forEach(trelloListDto -> {
        assertEquals("1", trelloListDto.getId());
        assertEquals("my_list", trelloListDto.getName());
        assertFalse(trelloListDto.isClosed());
      });
    });
  }

  @Test
  void shouldCreateCard() {
    // Given
    TrelloCardDto trelloCardDto = new TrelloCardDto("Test name", "Test description", "Top", "1");
    TrelloCard trelloCard = new TrelloCard(trelloCardDto.getName(), trelloCardDto.getDescription(),
        trelloCardDto.getPos(), trelloCardDto.getListId());
    CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto("1", "Test name",
        "shortUrl");

    when(trelloMapper.mapToCard(trelloCardDto)).thenReturn(trelloCard);
    when(trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard)))
        .thenReturn(createdTrelloCardDto);
    //When
    CreatedTrelloCardDto createdCard = trelloFacade.createCard(trelloCardDto);
    // Then
    assertEquals("1", createdCard.getId());
    assertEquals("Test name", createdCard.getName());
    assertEquals("shortUrl", createdCard.getShortUrl());
  }
}
