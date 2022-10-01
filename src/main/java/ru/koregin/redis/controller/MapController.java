package ru.koregin.redis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.redis.service.MapService;

import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/map")
@RequiredArgsConstructor
public class MapController {

    private final MapService mapService;

    @PostMapping("/HSET/{key}")
    public int hset(@PathVariable("key") String key, @RequestBody Map<String, String> fields) {
        log.info("Add element with key = {}, and fields and values: {}", key, fields);
        return mapService.hset(key, fields);
    }

    @GetMapping("/HGET/{key}/{field}")
    public ResponseEntity<String> hget(@PathVariable("key") String key, @PathVariable("field") String field) {
        log.info("Get value from map with key = {}, and field = {}", key, field);
        HttpStatus httpStatus = HttpStatus.OK;
        String result = mapService.hget(key, field);
        if (result.isEmpty()) {
            httpStatus = HttpStatus.BAD_REQUEST;
            result = "Значение для ключа = " + key + " и поля = " + field + " не нейдено";
        }
        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }

    @DeleteMapping("/CLEAR")
    public ResponseEntity<Void> clear() {
        log.info("Очищение кэша для Map");
        mapService.clear();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
