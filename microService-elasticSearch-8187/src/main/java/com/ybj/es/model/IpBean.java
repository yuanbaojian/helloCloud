package com.ybj.es.model;

import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import lombok.Data;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;
import org.springframework.data.elasticsearch.annotations.FieldType;

/**
 * @Author ipBean
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
@ToString
@JsonNaming(PropertyNamingStrategy.SnakeCaseStrategy.class)
@Document( indexName = "database-ip" , type = "ip")
public class IpBean {

    @Id
    private String id;

    private String ip_address;

    private Integer ipPort;

    private String server_address;

    private String anony_type;

    private String protocol_type;

}
