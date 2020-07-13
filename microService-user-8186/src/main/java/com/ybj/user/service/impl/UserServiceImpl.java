package com.ybj.user.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.user.dao.UserMapper;
import com.ybj.user.model.User;
import com.ybj.user.service.UserServiceI;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

/**
* @Author yabo.wang
* @Date 2020/1/13 13:48
* @Description
* IService:mp提供的接口。ServiceImpl:mp提供的接口实现类。
* ServiceImpl<BaseMapper<T>, T>是IService 的实现类。
*/
@Slf4j
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserServiceI {




}
