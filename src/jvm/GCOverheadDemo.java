package jvm;

import java.util.ArrayList;
import java.util.List;

/**
 * jvm参数配置
 * -Xms10m -Xmx10m -XX:+PrintGCDetails -XX:MaxDirectMemorySize=5m
 *
 *GC回收时间过长会抛OOM:overhead limit exceed。过长的定义是：超过98%的时间用来GC并且回收了不到2%的堆内存
 * 连续多次GC都只回收了不到2%的极端情况下才会抛出。
 * 假如不抛出GC overhead limit，那么GC清理的内存很快就会再次填满，迫使GC再次执行，这样就形成恶性循环，cpu使用率一直是100%，而GC却没有任何成果。
 */
public class GCOverheadDemo {

    public static void main(String[] args){
        int i = 0;
        List<String> list = new ArrayList<>();

        try{
            while(true){
                list.add(String.valueOf(++i).intern());
            }
        }catch (Throwable e){
            System.out.println("***************i:"+i);
            e.printStackTrace();
            throw e;
        }

    }
}
