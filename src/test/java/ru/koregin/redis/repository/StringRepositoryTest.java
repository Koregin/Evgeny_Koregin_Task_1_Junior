package ru.koregin.redis.repository;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.Matchers.containsInAnyOrder;
import static org.hamcrest.Matchers.is;

public class StringRepositoryTest {

    @Test
    public void whenSetNewElementThenGetValueByKey() {
        StringRepository repository = new StringRepository();
        String key = "Hello";
        String value = "world";
        repository.set(key, value);
        assertThat(value, is(repository.get(key)));
    }

    @Test
    public void whenSetOneNewElementThenDeleteIt() {
        StringRepository repository = new StringRepository();
        String key = "Hello";
        String value = "world";
        repository.set(key, value);
        assertThat(1, is(repository.del(List.of(key))));
    }

    @Test
    public void whenSetSeveralNewElementsThenDeleteThem() {
        StringRepository repository = new StringRepository();
        String key1 = "Hello1";
        String key2 = "Hello2";
        String key3 = "Hello3";
        String value = "world";
        repository.set(key1, value);
        repository.set(key2, value);
        repository.set(key3, value);
        assertThat(3, is(repository.del(List.of(key1, key2, key3))));
    }

    @Test
    public void whenFindKeysAndGetResult() {
        StringRepository repository = new StringRepository();
        String key1 = "Hello";
        String key2 = "Hallo";
        String key3 = "Helloween";
        String value = "world";
        repository.set(key1, value);
        repository.set(key2, value);
        repository.set(key3, value);
        List<String> first = List.of(key1, key2);
        List<String> second = repository.keys("H[ea]llo$");
        assertThat(second, containsInAnyOrder(first.toArray()));
    }
}
