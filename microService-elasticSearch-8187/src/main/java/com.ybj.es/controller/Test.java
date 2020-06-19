package com.ybj.es.controller;

import com.ybj.es.model.Book;
import com.ybj.es.service.BookService;
import org.springframework.beans.factory.annotation.Autowired;
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
public class Test {

    @Autowired
    BookService bookService;

    @PostMapping("/post")
    public void test(){
        Book book = new Book();
        book.setAuthor("曹雪芹");
        book.setBookName("红楼梦");
        book.setId(110);
        // bookService.index(book);
    }
}
