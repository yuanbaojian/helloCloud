package com.ybj.es.model;

import lombok.Data;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

/**
 * @Author User
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
@ToString
@Accessors(chain = true)
@Document(indexName = "database2", type = "TBL_USER")
public class User {

    @Id
    String userId;

    @Field(fielddata = true)
    String userName;

    String password;

    String email;
}
