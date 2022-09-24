package ru.koregin.redis.repository;

import org.springframework.stereotype.Repository;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Repository
public class MapRepository {
    private final Map<String, Map<String, String>> map = new ConcurrentHashMap<>();

    public int hset(String key, Map<String, String> fields) {
        if (map.containsKey(key)) {
            for (String field : fields.keySet()) {
                map.get(key).put(field, fields.get(field));
            }
        } else {
            map.put(key, fields);
        }
        return fields.size();
    }

    public String hget(String key, String field) {
        Map<String, String> getMap = map.get(key);
        String fieldValue = "";
        if (getMap != null) {
            fieldValue = getMap.getOrDefault(field, "");
        }
        return fieldValue;
    }
}
