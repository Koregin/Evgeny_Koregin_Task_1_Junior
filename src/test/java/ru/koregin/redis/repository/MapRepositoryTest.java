package ru.koregin.redis.repository;

import org.junit.jupiter.api.Test;
import java.util.Map;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.is;
import static org.junit.jupiter.api.Assertions.assertTrue;

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
        Map<String, String> fields = Map.of("color", "red");
        String key = "auto";
        repository.hset(key, fields);
        Map<String, String> newFields = Map.of("power", "220", "transmission", "auto");
        repository.hset(key, newFields);
        assertTrue(repository.hget(key, "color").equals("red")
                && repository.hget(key, "power").equals("220")
                && repository.hget(key, "transmission").equals("auto"));
    }
}
