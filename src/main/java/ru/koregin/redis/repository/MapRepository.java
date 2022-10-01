package ru.koregin.redis.repository;

import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapRepository {

    private static final String EMPTY_STRING = "";

    private final Map<String, Map<String, String>> map = new ConcurrentHashMap<>();

    public int hset(String key, Map<String, String> fields) {
        Map<String, String> shallowCopy = new HashMap<>(fields);
        if (map.containsKey(key)) {
            for (String field : shallowCopy.keySet()) {
                map.get(key).put(field, fields.get(field));
            }
        } else {
            map.put(key, shallowCopy);
        }
        return fields.size();
    }

    public String hget(String key, String field) {
        Map<String, String> getMap = map.get(key);
        String fieldValue = EMPTY_STRING;
        if (getMap != null) {
            fieldValue = getMap.getOrDefault(field, EMPTY_STRING);
        }
        return fieldValue;
    }

    public void clear() {
        map.clear();
    }
}
