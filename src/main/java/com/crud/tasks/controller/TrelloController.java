package com.crud.tasks.controller;

import com.crud.tasks.domain.CreatedTrelloCard;
import com.crud.tasks.domain.TrelloBoardDto;
import com.crud.tasks.domain.TrelloCardDto;
import com.crud.tasks.service.TrelloService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/trello")
@CrossOrigin(origins = "*")
public class TrelloController {
    private final Logger LOGGER = LoggerFactory.getLogger(getClass());

    @Autowired
    private TrelloService trelloService;

//    @RequestMapping(method = RequestMethod.GET, value = "getTrelloBoards")
    @GetMapping(value = "getTrelloBoards")
    public List<TrelloBoardDto> getTrelloBoards() {
        return trelloService.fetchTrelloBoards();
    }

//    @RequestMapping(method = RequestMethod.POST, value = "createTrelloCard")
    @PostMapping(value = "createTrelloCard")
    public CreatedTrelloCard createTrelloCard(@RequestBody TrelloCardDto trelloCardDto) {
        CreatedTrelloCard createNewCard = trelloService.createTrelloCard(trelloCardDto);
        LOGGER.debug("==> createNewCard: " + createNewCard.toString());
        return createNewCard;
    }
}
