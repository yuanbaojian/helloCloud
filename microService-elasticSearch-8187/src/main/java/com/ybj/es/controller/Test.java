package com.ybj.es.controller;

import com.ybj.es.model.Book;
import com.ybj.es.service.BookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.elasticsearch.core.ElasticsearchOperations;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author Test
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@RestController
@Slf4j
public class Test {

    @Autowired
    BookService bookService;

    @Autowired
    ElasticsearchOperations elasticsearchOperations;

    @PostMapping("/post")
    public void test(){
        Book book = new Book();
        book.setAuthor("曹雪芹");
        book.setBookName("红楼梦");
        for (int i = 0; i < 1000; i++) {
            book.setId(i);
            Book save = bookService.save(book);
            log.info("正在保存第 {} 项"  ,i);
        }
    }





}
