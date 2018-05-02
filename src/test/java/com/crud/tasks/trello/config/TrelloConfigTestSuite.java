package com.crud.tasks.trello.config;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertTrue;

@RunWith(SpringRunner.class)
@SpringBootTest
public class TrelloConfigTestSuite {
    @Autowired
    private TrelloConfig trelloConfig;

    @Test
    public void getTrelloApiEndpoint() {
        assertTrue(trelloConfig.getTrelloApiEndpoint().length() > 0);
    }

    @Test
    public void getTrelloAppKey() {
        assertTrue(trelloConfig.getTrelloAppKey().length() > 0);
    }

    @Test
    public void getTrelloToken() {
        assertTrue(trelloConfig.getTrelloToken().length() > 0);
    }

    @Test
    public void getUsername() {
        assertTrue(trelloConfig.getUsername().length() > 0);
    }
}
