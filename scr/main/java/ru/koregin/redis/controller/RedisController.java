package ru.koregin.redis.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.koregin.redis.repository.ListRepository;
import ru.koregin.redis.repository.MapRepository;
import ru.koregin.redis.repository.StringRepository;

import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api")
public class RedisController {

    private static Logger logger = Logger.getLogger(RedisController.class.getName());

    private final StringRepository stringRepository;
    private final MapRepository mapRepository;
    private final ListRepository listRepository;

    public RedisController(StringRepository stringRepository,
                           MapRepository mapRepository,
                           ListRepository listRepository) {
        this.stringRepository = stringRepository;
        this.mapRepository = mapRepository;
        this.listRepository = listRepository;
    }

    @PostMapping("/SET/{key}/{value}")
    public ResponseEntity<String> set(@PathVariable("key") String key,
                               @PathVariable("value") String value) {
        logger.info("Add element to dictionary. Key=" + key + ", value=" + value);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(stringRepository.set(key, value));
    }

    @GetMapping("/GET/{key}")
    public ResponseEntity<String> get(@PathVariable("key") String key) {
        logger.info("Get element with key=" + key);
        String result = stringRepository.get(key);
        logger.info("Element value is=" + result);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(result);
    }

    @DeleteMapping("/DEL")
    public ResponseEntity<Integer> delete(@RequestBody List<String> keys) {
        logger.info("Delete elements with keys: " + keys);
        return ResponseEntity
                .status(HttpStatus.OK)
                .body(stringRepository.del(keys));
    }

    @GetMapping("/KEYS/{pattern}")
    public List<String> keys(@PathVariable("pattern") String pattern) {
        logger.info("Find keys by pattern = " + pattern);
        return stringRepository.keys(pattern);
    }

    @PostMapping("/HSET/{key}")
    public int hset(@PathVariable("key") String key, @RequestBody Map<String, String> fields) {
        logger.info("Add element with key = " + key + ", and fields and values: " + fields);
        return mapRepository.hset(key, fields);
    }

    @GetMapping("/HGET/{key}/{field}")
    public ResponseEntity<String> hget(@PathVariable("key") String key, @PathVariable("field") String field) {
        logger.info("Get value from map with key = " + key + ", and field = " + field);
        HttpStatus httpStatus = HttpStatus.OK;
        String result = mapRepository.hget(key, field);
        if (result.isEmpty()) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }

    @PostMapping("/LPUSH/{key}")
    public ResponseEntity<Integer> lpush(@PathVariable("key") String key, @RequestBody List<String> elements) {
        logger.info("Add element with key = " + key + " and List elements = " + elements);
        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(listRepository.lpush(key, elements));
    }

    @GetMapping("/LGET/{key}/{index}")
    public ResponseEntity<String> lget(@PathVariable("key") String key, @PathVariable("index") int index) {
        logger.info("Get element with key = " + key + " and index = " + index);
        HttpStatus httpStatus = HttpStatus.OK;
        String result = "";
        try {
            result = listRepository.lget(key, index);
        } catch (IndexOutOfBoundsException e) {
            httpStatus = HttpStatus.NOT_FOUND;
        }
        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }

    @PatchMapping("/LSET/{key}/{index}/{value}")
    public ResponseEntity<String> lset(@PathVariable("key") String key,
                                       @PathVariable("index") int index,
                                       @PathVariable("value") String value) {
        logger.info("Set element with key = " + key + " and index = " + index);
        HttpStatus httpStatus = HttpStatus.OK;
        String result = "OK";
        try {
            listRepository.lset(key, index, value);
        } catch (IndexOutOfBoundsException e) {
            httpStatus = HttpStatus.NOT_FOUND;
            result = "";
        }
        return ResponseEntity
                .status(httpStatus)
                .body(result);
    }
}
