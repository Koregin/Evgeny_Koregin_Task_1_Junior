package ru.koregin.redis.repository;

import org.junit.Test;

import java.util.HashMap;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.Assert.assertTrue;

public class MapRepositoryTest {

    @Test
    public void whenAddNewElementWithOneField() {
        MapRepository repository = new MapRepository();
        Map<String, String> fields = Map.of("color", "red");
        repository.hset("auto", fields);
        assertThat("red", is(repository.hget("auto", "color")));
    }

    @Test
    public void whenAddNewFieldInExistElement() {
        MapRepository repository = new MapRepository();
        Map<String, String> fields = new HashMap<>();
        fields.put("color", "red");
        String key = "auto";
        repository.hset(key, fields);
        Map<String, String> newFields = new HashMap<>();
        newFields.put("power", "220");
        newFields.put("transmission", "auto");
        repository.hset(key, newFields);
        assertTrue(repository.hget(key, "color").equals("red")
                && repository.hget(key, "power").equals("220")
                && repository.hget(key, "transmission").equals("auto"));
    }

}