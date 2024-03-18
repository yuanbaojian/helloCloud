package com.ybj.crawler.Learn.DesignPattern.singleton;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @Author DoubleCheck
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
@Data
@Builder
public class DoubleCheck {

    // 1. 使用volatile ，避免指令重排
    private static volatile DoubleCheck singleton;

    public static  DoubleCheck getInstance(){
        // 2. 初次判空
        if(singleton == null){
            // 3. synchronized加锁
            synchronized (DoubleCheck.class){
                // 4. 双重检查
                if(singleton == null){
                    singleton = new DoubleCheck();
                }
            }
        }
        return singleton;
    }
}
