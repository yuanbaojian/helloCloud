package com.ybj.es.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDate;

/**
 * @Author Log @Description //TODO $ @Date $ $ @Param $
 *
 * @return $
 */
@Data
@ToString
@Document(indexName = "om-system-log")
public class Log {
    @Id
    private String id;

    private String timestamp;

    private String message;

    private LocalDate localDate;

}
