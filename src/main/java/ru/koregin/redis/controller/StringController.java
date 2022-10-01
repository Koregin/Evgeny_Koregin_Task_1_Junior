package ru.koregin.redis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.redis.model.KeyValueDto;
import ru.koregin.redis.service.StringService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/string")
@RequiredArgsConstructor
public class StringController {

    private final StringService stringService;

    @PostMapping("/SET")
    public ResponseEntity<String> set(@RequestBody KeyValueDto keyValueDto) {
        log.info("Add element to dictionary. Key = {}, value = {}", keyValueDto.getKey(), keyValueDto.getValue());
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stringService.set(keyValueDto.getKey(), keyValueDto.getValue()));
    }

    @GetMapping("/GET/{key}")
    public ResponseEntity<String> get(@PathVariable("key") String key) {
        log.info("Get element with key = {}", key);
        String result = stringService.get(key);
        log.info("Element value is = {}", result);
        HttpStatus httpStatus = HttpStatus.OK;
        if (result == null) {
            httpStatus = HttpStatus.NOT_FOUND;
            result = "Значение для ключа = " + key + " не нейдено";
        }
        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }

    @DeleteMapping("/DEL")
    public ResponseEntity<Integer> delete(@RequestBody List<String> keys) {
        log.info("Delete elements with keys: {}", keys);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stringService.del(keys));
    }

    @GetMapping("/KEYS/{pattern}")
    public ResponseEntity<List<String>> keys(@PathVariable("pattern") String pattern) {
        log.info("Find keys by pattern = {}", pattern);
        HttpStatus httpStatus = HttpStatus.OK;
        List<String> result = stringService.keys(pattern);
        if (result.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }

    @DeleteMapping("/CLEAR")
    public ResponseEntity<Void> clear() {
        log.info("Очищение кэша для String");
        stringService.clear();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
