package com.ybj.crawler.Learn.ThinkingInJava.Ch12Exception;

/**
 * @Author Exception
 * @Description //TODO $
 * @Date $ $
 * @Param $
 * @return $
 **/
public class ExceptionDemo {

    public static void main(String[] args) {
        try{
            throw new Exception("我出错了");
        } catch (Exception e){
           e.printStackTrace();
        }
    }

}

