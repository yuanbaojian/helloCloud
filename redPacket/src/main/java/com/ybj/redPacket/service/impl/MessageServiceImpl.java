package com.ybj.redPacket.service.impl;

import com.ybj.redPacket.model.Message;
import com.ybj.redPacket.dao.MessageMapper;
import com.ybj.redPacket.service.MessageServiceI;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author baojian.yuan
 * @since 2020-07-16
 */
@Service
public class MessageServiceImpl extends ServiceImpl<MessageMapper, Message> implements MessageServiceI {

}
