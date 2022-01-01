package pro.ruloff.tasks.controller;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.google.gson.Gson;
import java.util.ArrayList;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentMatchers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloListDto;
import pro.ruloff.tasks.trello.facade.TrelloFacade;

@ExtendWith(SpringExtension.class)
@WebMvcTest(TrelloController.class)
public class TrelloControllerTest {

  private static final String PATH = "/v1/trello";
  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private TrelloFacade trelloFacade;

  @Test
  void shouldFetchEmptyTrelloBoards() throws Exception {
    // Given
    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
    // When & Then
    mockMvc.perform(get(PATH + "/boards").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().is(200)) // or isOk()
        .andExpect(jsonPath("$", hasSize(0)))
        .andDo(print());
  }

  @Test
  void shouldFetchTrelloBoards() throws Exception {
    // Given
    List<TrelloListDto> trelloLists = new ArrayList<>();
    trelloLists.add(new TrelloListDto("1", "Test List", false));

    List<TrelloBoardDto> trelloBoards = new ArrayList<>();
    trelloBoards.add(new TrelloBoardDto("1", "Test Task", trelloLists));

    when(trelloFacade.fetchTrelloBoards()).thenReturn(trelloBoards);
    // When & Then
    mockMvc.perform(get(PATH + "/boards").contentType(MediaType.APPLICATION_JSON))
        .andExpect(status().isOk())
        // Trello board fields
        .andExpect(jsonPath("$", hasSize(1)))
        .andExpect(jsonPath("$[0].id", is("1")))
        .andExpect(jsonPath("$[0].name", is("Test Task")))
        // Trello list fields
        .andExpect(jsonPath("$[0].lists", hasSize(1)))
        .andExpect(jsonPath("$[0].lists[0].id", is("1")))
        .andExpect(jsonPath("$[0].lists[0].name", is("Test List")))
        .andExpect(jsonPath("$[0].lists[0].closed", is(false)))
        .andDo(print());
  }

  @Test
  void shouldCreateTrelloCard() throws Exception {
    // Given
    TrelloCardDto trelloCardDto = new TrelloCardDto(
        "Test",
        "Test description",
        "top",
        "1"
    );
    CreatedTrelloCardDto createdTrelloCardDto = new CreatedTrelloCardDto(
        "323",
        "Test",
        "http://test.com"
    );
    when(trelloFacade.createCard(ArgumentMatchers.any(TrelloCardDto.class)))
        .thenReturn(createdTrelloCardDto);

    Gson gson = new Gson();
    String jsonContent = gson.toJson(trelloCardDto);
    // When & Then
    mockMvc.perform(post(PATH + "/cards")
            .contentType(MediaType.APPLICATION_JSON)
            .characterEncoding("UTF-8")
            .content(jsonContent))
        .andExpect(jsonPath("$.id", is("323")))
        .andExpect(jsonPath("$.name", is("Test")))
        .andExpect(jsonPath("$.shortUrl", is("http://test.com")))
        .andDo(print());
  }
}
