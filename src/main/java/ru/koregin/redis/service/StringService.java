package ru.koregin.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koregin.redis.repository.StringRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class StringService {

    private final StringRepository stringRepository;

    public String set(String key, String value) {
        return stringRepository.set(key, value);
    }

    public String get(String key) {
        return stringRepository.get(key);
    }

    public int del(List<String> keys) {
        return stringRepository.del(keys);
    }

    public List<String> keys(String pattern) {
        return stringRepository.keys(pattern);
    }

    public void clear() {
        stringRepository.clear();
    }
}
