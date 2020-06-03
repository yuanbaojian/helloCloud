package com.ybj.es.service;

import com.ybj.es.model.Book;
import com.ybj.es.model.User;
import org.elasticsearch.repositories.Repository;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author BookService2
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface UserService extends ElasticsearchRepository<User , String> {

    List<User> findByUserNameAndEmail(String userName, String email);

    List<User> findByUserName(String userName);

    List<User> findAllByUserNameLike(String userName);

    List<User> findUsersByUserNameLike(String userName);

    List<User> findByUserNameLike(String userName);

    List<User> findAllByUserNameLikeAndEmailLike(String userName, String email);

}
