package ru.koregin.redis.model;

import lombok.Data;

@Data
public class KeyIndexValueDto {
    private String key;
    private int index;
    private String value;
}
