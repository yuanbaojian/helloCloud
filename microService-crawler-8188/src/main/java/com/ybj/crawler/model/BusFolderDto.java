package com.ybj.crawler.model;

import lombok.Data;

/**
 * @Author BusFolderDto
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
public class BusFolderDto {
    private BusInfo busInfo = new BusInfo();
    private Folder folder = new Folder();
}
