package com.ybj.crawler.Learn.jvm.Ch2Memory;

import org.omg.CORBA.OMGVMCID;

import javax.activation.CommandObject;
import java.util.ArrayList;
import java.util.List;

/**
 *  参数： -Xms5m -Xmx5m -XX:+HeapDumpOnOutOfMemoryError
 *  使之报错：OOM ,out of memory
 **/
public class OutOfMemoryDemo {

    static class OOMObject{

    }

    public static void main(String[] args) {
        List<OOMObject> list = new ArrayList<>();
        while (true){
            list.add(new OOMObject());
        }
    }
}
