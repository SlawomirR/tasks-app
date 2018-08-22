package io.github.slawomirr.tasks.trello.facade;

import io.github.slawomirr.tasks.domain.TrelloBoard;
import io.github.slawomirr.tasks.domain.TrelloCard;
import io.github.slawomirr.tasks.domain.dto.CreatedTrelloCardDto;
import io.github.slawomirr.tasks.domain.dto.TrelloBoardDto;
import io.github.slawomirr.tasks.domain.dto.TrelloCardDto;
import io.github.slawomirr.tasks.mapper.TrelloMapper;
import io.github.slawomirr.tasks.service.TrelloService;
import io.github.slawomirr.tasks.trello.validator.TrelloValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class TrelloFacade {
    @Autowired
    private TrelloService trelloService;

    @Autowired
    private TrelloMapper trelloMapper;

    @Autowired
    private TrelloValidator trelloValidator;

    public List<TrelloBoardDto> fetchTrelloBoards() {
        List<TrelloBoard> trelloBoards = trelloMapper.mapToBoards(trelloService.fetchTrelloBoards());
        List<TrelloBoard> filteredBoards = trelloValidator.validateTrelloBoards(trelloBoards);
        return trelloMapper.mapToBoardsDto(filteredBoards);
    }

    public CreatedTrelloCardDto createCard(final TrelloCardDto trelloCardDto) {
        TrelloCard trelloCard = trelloMapper.mapToCard(trelloCardDto);
        trelloValidator.validateCard(trelloCard);
        return trelloService.createTrelloCard(trelloMapper.mapToCardDto(trelloCard));
    }
}
