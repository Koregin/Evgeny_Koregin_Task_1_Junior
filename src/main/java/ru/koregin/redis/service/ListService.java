package ru.koregin.redis.service;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import ru.koregin.redis.repository.ListRepository;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ListService {

    private final ListRepository listRepository;

    public int lpush(String key, List<String> elements) {
        return listRepository.lpush(key, elements);
    }

    public String lget(String key, int index) {
        return listRepository.lget(key, index);
    }

    public String lset(String key, int index, String value) {
        return listRepository.lset(key, index, value);
    }

    public void clear() {
        listRepository.clear();
    }
}
