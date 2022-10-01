package ru.koregin.redis.repository;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ListRepositoryTest {

    @Test
    public void whenAddNewElementWithListValues() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", List.of("one", "two", "three"));
        repository.lpush("numbers", List.of("four"));
        assertEquals("four", repository.lget("numbers", 0));
    }

    @Test
    public void whenSetElementWithExistIndex() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", List.of("one", "two", "three"));
        repository.lset("numbers", 0, "threenew");
        assertEquals("threenew", repository.lget("numbers", 0));
    }

    @Test
    public void whenSetElementWithoutExistIndex() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", List.of("one"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repository.lset("numbers", 1, "two");
        });
    }

    @Test
    public void whenSetElementWithoutExistElement() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", List.of("one"));
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            repository.lset("strings", 0, "string1");
        });
    }
}
