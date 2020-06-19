package com.ybj.es.service;

import com.ybj.es.model.Book;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author BookService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface BookService extends CrudRepository<Book,Integer> {

    List<Book> findBooksByBookName(String bookName);

    List<Book> findBooksByBookNameLike(String bookName);

    List<Book> findAllByAuthor(String authorName);

    List<Book> findBooksByBookNameAndAuthor(String bookName, String author);

    List<Book> findAllByBookNameLikeAndAndAuthorIsLike(String bookName, String author);

}
