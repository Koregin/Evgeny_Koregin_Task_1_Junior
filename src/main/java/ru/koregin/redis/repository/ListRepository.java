package ru.koregin.redis.repository;

import org.springframework.stereotype.Repository;

import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class ListRepository {
    private final Map<String, LinkedList<String>> list = new ConcurrentHashMap<>();

    public int lpush(String key, List<String> elements) {
        if (list.containsKey(key)) {
            list.get(key).addAll(0, elements);
        } else {
            list.put(key, new LinkedList<>(elements));
        }
        return list.get(key).size();
    }

    public String lget(String key, int index) {
        String result;
        if (list.containsKey(key)) {
            if (index < list.get(key).size()) {
                result = list.get(key).get(index);
            } else {
                throw new IllegalArgumentException("Значение с индексом = " + index + " для ключа = " + key + " не найдено");
            }
        } else {
            throw new IllegalArgumentException("Ключ = " + key + " не найден");
        }
        return result;
    }

    public String lset(String key, int index, String element) {
        if (list.containsKey(key)) {
            if (index < list.get(key).size()) {
                list.get(key).set(index, element);
            } else {
                throw new IllegalArgumentException("Значение с индексом = " + index + " для ключа = " + key + " не найдено");
            }
        } else {
            throw new IllegalArgumentException("Ключ = " + key + " не найден");
        }
        return "OK";
    }

    public void clear() {
        list.clear();
    }
}
