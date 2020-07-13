package com.ybj.es.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.time.LocalDate;

@Data
@ToString
@Document( indexName = "om-system-log")
public class TomcatLog {

    @Id
    private String id;

    private String timestamp;

    private String message;

    private LocalDate loclDate;
}
