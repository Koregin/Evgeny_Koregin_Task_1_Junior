package ru.koregin.redis.model;

import lombok.Data;

@Data
public class KeyValueDto {
    private String key;
    private String value;
}
