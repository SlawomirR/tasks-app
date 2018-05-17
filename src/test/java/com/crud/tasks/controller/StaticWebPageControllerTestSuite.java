package com.crud.tasks.controller;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.Assert.assertTrue;

public class StaticWebPageControllerTestSuite {

    @Test
    public void index() {
        // Given
        StaticWebPageController staticWebPageController = new StaticWebPageController();
        Map<String, Object> testMap = new HashMap<>();
        testMap.put("New", "value");
        // When
        String index = staticWebPageController.index(testMap);
        // Then
        assertTrue(index.length() > 0);
    }
}