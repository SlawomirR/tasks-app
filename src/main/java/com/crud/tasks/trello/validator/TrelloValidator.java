package com.crud.tasks.trello.validator;

import com.crud.tasks.domain.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class TrelloValidator {
    private static final Logger LOGGER = LoggerFactory.getLogger(TrelloValidator.class);

    public List<TrelloBoard> validateTrelloBoards(final List<TrelloBoard> trelloBoards) {
        LOGGER.info("Starting filtering boards ...");
        List<TrelloBoard> filteredBoards = trelloBoards.stream()
//                .filter(trelloBoard -> ! trelloBoard.getName().equalsIgnoreCase("test"))
                .filter(trelloBoard -> ! trelloBoard.getName().toLowerCase().contains("test"))
                .collect(Collectors.toList());
        LOGGER.info("Boards have been filtered. Current list size: " + filteredBoards.size() + " of " + trelloBoards.size());
        return filteredBoards;
    }

    public boolean validateCard(final TrelloCard trelloCard) {
        if (trelloCard.getName().toLowerCase().contains("test")) {
            LOGGER.info("Someone is testing my application!");
            return false;
        } else {
            LOGGER.info("Seems that my application id used in proper way.");
            return true;
        }
    }
}
