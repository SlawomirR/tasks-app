package pro.ruloff.tasks.trello.client;

import java.net.URI;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.trello.config.TrelloConfig;

@Slf4j
@Component
public class TrelloClient {

  private final TrelloConfig trelloConfig;
  private final RestTemplate restTemplate;

  public TrelloClient(TrelloConfig trelloConfig, RestTemplate restTemplate) {
    this.trelloConfig = trelloConfig;
    this.restTemplate = restTemplate;
  }

  public CreatedTrelloCardDto createNewCard(TrelloCardDto trelloCardDto) {
    log.debug("==> postForObject: " + urlTrelloCard(trelloCardDto));
    CreatedTrelloCardDto createNewCard = restTemplate
        .postForObject(urlTrelloCard(trelloCardDto), null, CreatedTrelloCardDto.class);
    log.debug("==> createNewCard: {}", createNewCard);
    // without TRY for now to let front-end to do error verification
    return createNewCard;
  }

  public List<TrelloBoardDto> getTrelloBoards() {
    try {
      log.debug("==> getForObject: {}", urlTrelloBoards());
      final TrelloBoardDto[] boardsResponse = restTemplate
          .getForObject(urlTrelloBoards(), TrelloBoardDto[].class);
      return Arrays.asList(Optional.ofNullable(boardsResponse).orElse(new TrelloBoardDto[0]));
    } catch (RestClientException e) {
      log.error(e.getMessage(), e);
      return new ArrayList<>();
    }
  }

  private URI urlTrelloBoards() {
    final String pathOnServer = "/members/" + trelloConfig.getUsername() + "/boards";
    final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
    authenticationQueryParam(uriComponentsBuilder);
    uriComponentsBuilder
        .queryParam("fields", "id,name,closed")
        .queryParam("lists", "all");
    return uriComponentsBuilder.buildAndExpand(pathOnServer).encode().toUri();
  }

  private URI urlTrelloCard(TrelloCardDto trelloCardDto) {
    log.debug("==> trelloCardDto contains: {}", trelloCardDto.toString());
    final UriComponentsBuilder uriComponentsBuilder = UriComponentsBuilder.newInstance();
    authenticationQueryParam(uriComponentsBuilder);
    uriComponentsBuilder
        .queryParam("name", trelloCardDto.getName())
        .queryParam("desc", trelloCardDto.getDescription())
        .queryParam("pos", trelloCardDto.getPos())
        .queryParam("idList", trelloCardDto.getListId());
    final String pathOnServer = "/cards";
    return uriComponentsBuilder.buildAndExpand(pathOnServer).encode().toUri();
  }

  private UriComponentsBuilder authenticationQueryParam(UriComponentsBuilder uriComponentsBuilder) {
    final String[] trelloAppEndpoint = trelloConfig.getTrelloApiEndpoint().split("/", 4);
    log.debug("==> trelloAppEndpoint[]: {}", Arrays.asList(trelloAppEndpoint));
    return uriComponentsBuilder
        .scheme(trelloAppEndpoint[0]
            .substring(0, trelloAppEndpoint[0].length() - 1)) // https part without ":" at the end
        .host(trelloAppEndpoint[2])
        .path(trelloAppEndpoint[3])
        .path("{path}")
        .queryParam("key", trelloConfig.getTrelloAppKey())
        .queryParam("token", trelloConfig.getTrelloToken());
  }
}
