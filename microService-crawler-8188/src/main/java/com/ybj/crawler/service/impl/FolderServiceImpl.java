package com.ybj.crawler.service.impl;

import com.ybj.crawler.model.Folder;
import com.ybj.crawler.dao.FolderMapper;
import com.ybj.crawler.service.FolderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ybj.crawler.service.IpBeanAsyncService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author ybj
 * @since 2020-03-02
 */
@Service
@RequiredArgsConstructor
public class FolderServiceImpl extends ServiceImpl<FolderMapper, Folder> implements FolderService {

    // final IpBeanAsyncService ipBeanAsyncService;

}
