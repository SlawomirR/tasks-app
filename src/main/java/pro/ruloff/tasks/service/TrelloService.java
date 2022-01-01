package pro.ruloff.tasks.service;

import java.util.List;
import java.util.Optional;
import org.springframework.stereotype.Service;
import pro.ruloff.tasks.config.AdminConfig;
import pro.ruloff.tasks.domain.Mail;
import pro.ruloff.tasks.domain.dto.CreatedTrelloCardDto;
import pro.ruloff.tasks.domain.dto.TrelloBoardDto;
import pro.ruloff.tasks.domain.dto.TrelloCardDto;
import pro.ruloff.tasks.trello.client.TrelloClient;

@Service
public class TrelloService {

  private static final String SUBJECT = "Tasks: New Trello card";

  private final AdminConfig adminConfig;
  private final TrelloClient trelloClient;
  private final SimpleEmailService emailService;

  public TrelloService(AdminConfig adminConfig,
      TrelloClient trelloClient, SimpleEmailService emailService) {
    this.adminConfig = adminConfig;
    this.trelloClient = trelloClient;
    this.emailService = emailService;
  }

  public List<TrelloBoardDto> fetchTrelloBoards() {
    return trelloClient.getTrelloBoards();
  }

  public CreatedTrelloCardDto createTrelloCard(final TrelloCardDto trelloCardDto) {
    CreatedTrelloCardDto newCard = trelloClient.createNewCard(trelloCardDto);
    Optional.ofNullable(newCard).ifPresent(card -> emailService.send(new Mail(
        adminConfig.getAdminMail(),
        "",
        SUBJECT,
        "New card: [" + card.getName() + "] has been created on your Trello account"
            + System.lineSeparator() + "Direct card address: " + card.getShortUrl()
    )));
    return newCard;
  }
}
