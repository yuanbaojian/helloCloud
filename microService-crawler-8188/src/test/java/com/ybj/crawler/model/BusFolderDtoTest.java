package com.ybj.crawler.model;

import org.junit.jupiter.api.Test;
import org.springframework.beans.BeanUtils;

import static org.junit.jupiter.api.Assertions.*;

class BusFolderDtoTest {

    @Test
    public void test(){
        BusFolderDto busFolderDto = new BusFolderDto();
        BusInfo busInfo = new BusInfo();
        busInfo.setDistance("1000");
        BeanUtils.copyProperties(busInfo, busFolderDto.getBusInfo());
        System.out.println("busFolderDto.toString() = " + busFolderDto.getBusInfo().toString());
    }
}