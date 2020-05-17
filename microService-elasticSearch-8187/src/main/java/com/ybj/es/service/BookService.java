package com.ybj.es.service;

import com.ybj.es.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author BookService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface BookService extends ElasticsearchRepository<Book,Integer> {

    List<Book> findBooksByBookName(String bookName);

    List<Book> findBooksByBookNameLike(String bookName);

    List<Book> findAllByAuthor(String authorName);

    List<Book> findBooksByBookNameAndAuthor(String bookName, String author);

    List<Book> findAllByBookNameLikeAndAndAuthorIsLike(String bookName, String author);
}
