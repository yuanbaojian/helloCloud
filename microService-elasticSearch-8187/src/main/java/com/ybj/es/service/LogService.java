package com.ybj.es.service;

import com.ybj.es.model.IpBean;
import com.ybj.es.model.Log;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author LogService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface LogService extends CrudRepository<Log,String> {

    List<Log> findAllByMessageLike(String message);
}
