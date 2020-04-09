package com.ybj.api.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * @Author Dept
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
@AllArgsConstructor
@NoArgsConstructor


public class Dept implements Serializable {

    private String deptNo;
    private String dName;
    private String dbSource;

}
