package ru.koregin.redis.repository;

import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class ListRepositoryTest {

    @Test
    public void whenAddNewElementWithListValues() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", Arrays.asList("one", "two", "three"));
        repository.lpush("numbers", List.of("four"));
        assertEquals("four", repository.lget("numbers", 0));
    }

    @Test
    public void whenSetElementWithExistIndex() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", Arrays.asList("one", "two", "three"));
        repository.lset("numbers", 0, "threenew");
        assertEquals("threenew", repository.lget("numbers", 0));
    }

    @Test
    public void whenSetElementWithoutExistIndex() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", List.of("one"));
        String result = repository.lset("numbers", 1, "two");
        assertEquals("Index Out of range", result);
    }

    @Test
    public void whenSetElementWithoutExistElement() {
        ListRepository repository = new ListRepository();
        repository.lpush("numbers", List.of("one"));
        String result = repository.lset("strings", 0, "string1");
        System.out.println(result);
        assertEquals("Key not found", result);
    }

}