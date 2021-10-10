package com.ybj.es.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.stereotype.Component;


@Data
@ToString
@Document( indexName = "database1")
@Component
@AllArgsConstructor
@NoArgsConstructor
public class Book {

    private Integer id;

    private String bookName;

    private String author;

}
