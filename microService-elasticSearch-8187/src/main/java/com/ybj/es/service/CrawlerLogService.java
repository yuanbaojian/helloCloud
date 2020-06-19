package com.ybj.es.service;

import com.ybj.es.model.IdeaLog;
import com.ybj.es.model.TomcatLog;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author CrawlerLogService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

public interface CrawlerLogService extends CrudRepository<IdeaLog,String> {

    List<IdeaLog> getAllByMessageLike(String message);

}
