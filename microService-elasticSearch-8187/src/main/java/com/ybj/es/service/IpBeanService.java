package com.ybj.es.service;

import com.ybj.es.model.Book;
import com.ybj.es.model.IpBean;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

/**
 * @Author IpBeanService
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public interface IpBeanService extends CrudRepository<IpBean,String> {
    //
    // List<IpBean> findAllByIp_addressLike(String ipAddress);
    //
    // List<IpBean> findAllByIpPortLike(String ipPort);

    List<IpBean> findAllByIdLike(String id);

}
