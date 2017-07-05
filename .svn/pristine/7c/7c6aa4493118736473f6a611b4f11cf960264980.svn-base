package com.aisino.domain.zookeeper;

import org.apache.curator.framework.CuratorFramework;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * DESCRIPTION：zk connection  curator initialization base class
 *
 * @author LuckyH
 * @create 2017-03-28 9:18
 **/
public abstract class ZKBase {

    protected CuratorFramework curatorFramework;

    public ZKBase(CuratorFramework curatorFramework) {
        this.curatorFramework = curatorFramework;
    }
    public void init(){
        try {
            call();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    protected abstract void call() throws Exception;
}
