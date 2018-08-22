package io.github.slawomirr.tasks.controller;

import io.github.slawomirr.tasks.domain.dto.CreatedTrelloCardDto;
import io.github.slawomirr.tasks.domain.dto.TrelloBoardDto;
import io.github.slawomirr.tasks.domain.dto.TrelloCardDto;
import io.github.slawomirr.tasks.trello.facade.TrelloFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {
    @Autowired
    private TrelloFacade trelloFacade;

//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
@GetMapping(value = "/boards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloFacade.fetchTrelloBoards();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
@PostMapping(value = "/cards")
    public CreatedTrelloCardDto createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        return trelloFacade.createCard(trelloCardDto);
    }
}
