package com.ybj.es.service;

import com.ybj.es.model.Book;
import com.ybj.es.model.TomcatLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

/**
 * @Author TomcatService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface TomcatService  extends ElasticsearchRepository<TomcatLog,String> {

    List<TomcatLog> findAllByMessageLike(String message);

}
