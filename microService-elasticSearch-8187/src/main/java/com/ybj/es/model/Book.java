package com.ybj.es.model;

import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;


@Data
@ToString
@Document( indexName = "database1" , type = "bookTable")
public class Book {

    private Integer id;
    private String bookName;
    private String author;

}
