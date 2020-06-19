package com.ybj.es.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.time.LocalDate;

/**
 * @Author IdeaLog
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
@ToString
@Document( indexName = "crawler-log-2020.06.19"  )
public class IdeaLog {

    @Id
    private String id;

    private String timestamp;

    @Field(analyzer = "")
    private String message;

    private LocalDate localDate;

}
