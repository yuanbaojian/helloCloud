package com.ybj.es.service;

import com.ybj.es.model.Book;
import com.ybj.es.model.User;
import org.elasticsearch.index.query.MatchAllQueryBuilder;
import org.elasticsearch.index.query.QueryBuilders;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.elasticsearch.core.query.NativeSearchQuery;
import org.springframework.data.elasticsearch.core.query.SearchQuery;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;
import java.util.Optional;
import java.util.Random;

@RunWith(SpringRunner.class)
@SpringBootTest
class BookServiceTest {

    @Autowired
    BookService bookService;


    //保存信息
    @Test
    public void test(){
        Book book = new Book();
        book.setAuthor("曹雪芹");
        book.setBookName("红楼梦");
        book.setId(110);
        bookService.index(book);

        Random random = new Random();
        for(int i = 0; i < 100; i++) {
            book.setAuthor("唐伯虎");
            book.setBookName("小鸡啄米" + random.nextInt(100));
            book.setId(i);
            bookService.index(book);
        }

    }

    //查询数据1
    @Test
    public void test2(){
        List<Book> bookList =  bookService.findBooksByBookName("唐");
        for (Book book : bookList) {
            System.out.println("book = " + book);
        }
    }

    //查询数据
    @Test
    public void test3(){
        Optional<Book> book = bookService.findById(1);
        Book book1 = book.get();
        System.out.println("book1.toString() = " + book1.toString());
    }

    @Test
    void findBooksByBookName() {}

    @Test
    void findBooksByBookNameLike() {}

    @Test
    void findAllByAuthor() {}

    @Test
    void findBooksByBookNameAndAuthor() {}

    @Test
    void findAllByBookNameLikeAndAndAuthorIsLike() {
        List<Book> bookList = bookService.findAllByBookNameLikeAndAndAuthorIsLike("米", "虎");
        System.out.println("bookList = " + bookList);
    }

    //    分页
    @Test
    public void pageSearch(){
        MatchAllQueryBuilder matchAllQueryBuilder = QueryBuilders.matchAllQuery();
        SearchQuery searchQuery = new NativeSearchQuery(matchAllQueryBuilder)
                .setPageable(PageRequest.of(1,10));
        Page<Book> books = bookService.search(searchQuery);
        for (Book book : books) {
            System.out.println("book.toString() = " + book.toString());
        }
    }


}