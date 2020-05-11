package com.ybj.gateWay.model;


import lombok.Data;

import java.io.Serializable;

@Data
public class User implements Serializable {

    private String userId;

    private String loginName;

    private String password;

    private Integer status;

}
