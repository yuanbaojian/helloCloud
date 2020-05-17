package com.ybj.crawler.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ybj.crawler.model.Folder;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author ybj
 * @since 2020-03-02
 */
public interface FolderMapper extends BaseMapper<Folder> {

    List<Folder> getAll();
}
