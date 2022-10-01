package ru.koregin.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koregin.redis.repository.MapRepository;

import java.util.Map;

@Service
@RequiredArgsConstructor
public class MapService {

    private final MapRepository mapRepository;

    public int hset(String key, Map<String, String> fields) {
        return mapRepository.hset(key, fields);
    }

    public String hget(String key, String field) {
        return mapRepository.hget(key, field);
    }

    public void clear() {
        mapRepository.clear();
    }
}
