package com.ybj.rabbit.service.impl;

import com.ybj.rabbit.model.Message;
import com.ybj.rabbit.dao.MessageMapper;
import com.ybj.rabbit.service.MessageServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-15
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageServiceI {

}
