package ru.koregin.redis.repository;

import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Repository
public class StringRepository {

    private final Map<String, String> dictionary = new ConcurrentHashMap<>();

    public String set(String key, String value) {
        return dictionary.put(key, value);
    }

    public String get(String key) {
        return dictionary.get(key);
    }

    public List<String> keys(String keysPattern) {
        Pattern pattern = Pattern.compile(keysPattern, Pattern.CASE_INSENSITIVE);
        List<String> keys = new ArrayList<>();
        for (String key : dictionary.keySet()) {
            Matcher matcher = pattern.matcher(key);
            if (matcher.find()) {
                keys.add(key);
            }
        }
        return keys;
    }

    public int del(List<String> keys) {
        int counter = 0;
        for (String key : keys) {
            if (dictionary.remove(key) != null) {
                counter++;
            }
        }
        return counter;
    }

    public void clear() {
        dictionary.clear();
    }
}
