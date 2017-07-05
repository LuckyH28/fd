package com.aisino.domain.zookeeper.core;

import com.aisino.domain.common.util.GlobalVariable;
import com.aisino.domain.zookeeper.ZKBase;

import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

/**
 * DESCRIPTION：Leader 调度
 *
 * @author LuckyH
 * @create 2017-03-28 9:23
 **/
public class LeaderDispatch extends ZKBase {

    private static final Logger logger = LogManager.getLogger(LeaderDispatch.class);

    private boolean leader = false;

    public boolean isLeader(){
    	logger.info("--线程(ID:" + Thread.currentThread().getId()+"),"+GlobalVariable.clientId+"的主备状态为"+leader);
        return  leader;
    }

    private String path;

    public LeaderDispatch(CuratorFramework curatorFramework , String path){
        super(curatorFramework);
        this.path= path;

    }

    private void leaderSelector(){
        LeaderSelector selector = new LeaderSelector(curatorFramework, path, new LeaderSelectorListener() {
            @Override
            public void takeLeadership(CuratorFramework curatorFramework) throws Exception {
            	leader = true;
                logger.info("线程(ID:" + Thread.currentThread().getId()+"),"+GlobalVariable.clientId+"的主备状态为"+leader+",Become to leader!");
                while (true){
                    Thread.sleep(Integer.MAX_VALUE);
                }
            }

            @Override
            public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {

            }
        });
        selector.autoRequeue();
        selector.start();
    }
    @Override
    protected void call() throws Exception {
        leader = false;
        leaderSelector();
    }
}
