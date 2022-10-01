package ru.koregin.redis.controller;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.redis.model.KeyIndexValueDto;
import ru.koregin.redis.service.ListService;

import java.util.List;

@Slf4j
@RestController
@RequestMapping("/list")
@RequiredArgsConstructor
public class ListController {

    private final ListService listService;

    @PostMapping("/LPUSH/{key}")
    public ResponseEntity<Integer> lpush(@PathVariable("key") String key, @RequestBody List<String> elements) {
        log.info("Add element with key = {} and List elements = {}", key, elements);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(listService.lpush(key, elements));
    }

    @GetMapping("/LGET/{key}/{index}")
    public ResponseEntity<String> lget(@PathVariable("key") String key, @PathVariable("index") int index) {
        log.info("Get element with key = {} and index = {}", key, index);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listService.lget(key, index));
    }

    @PatchMapping("/LSET")
    public ResponseEntity<String> lset(@RequestBody KeyIndexValueDto kivDto) {
        log.info("Set element with key = {}, index = {} and value = {}",
                kivDto.getKey(), kivDto.getIndex(), kivDto.getValue());
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(listService.lset(kivDto.getKey(), kivDto.getIndex(), kivDto.getValue()));
    }

    @DeleteMapping("/CLEAR")
    public ResponseEntity<Void> clear() {
        log.info("Очищение кэша для List");
        listService.clear();
        return ResponseEntity
                .status(HttpStatus.OK)
                .build();
    }
}
