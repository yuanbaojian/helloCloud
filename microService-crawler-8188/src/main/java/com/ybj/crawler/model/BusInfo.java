package com.ybj.crawler.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

/**
 * @Author BusInfo
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/

@Data
public class BusInfo {

    String attributes;
    String terminal;
    long stopdis;
    String distance;
    String time;

}
