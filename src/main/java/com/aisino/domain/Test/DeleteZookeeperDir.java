package com.aisino.domain.Test;


import java.util.List;

import org.apache.zookeeper.KeeperException;
import org.apache.zookeeper.WatchedEvent;
import org.apache.zookeeper.Watcher;
import org.apache.zookeeper.ZooKeeper;

public class DeleteZookeeperDir {
//	static String zkServer = "192.168.15.250:2181";
	static String zkServer = "192.168.15.179:2181";
    
    static ZooKeeper zk = null;
	public static void main(String[] args) throws Exception {
		zk = new ZooKeeper(zkServer,6000,new Watcher(){
            public void process(WatchedEvent event) {                        
            }
      });
//		zk.addAuthInfo("digest", "51IMFD:aisino@123".getBytes());
		zk.addAuthInfo("digest", "Admin:password".getBytes());
//		String dir = "/schedule";
		String dir = "/uncode109/schedule";
//		String dir = "/uncode_MQTT/schedule/server/192.168.15.179$69C5BA8B69FA4CD48EC1388E8778A53E$0000000005";
	    List<String> lists = zk.getChildren(dir, true);
	    System.out.println(lists.size());
	    deleteDir(dir, -1);
	}
	public static void deleteDir(String dir,int a) throws KeeperException, InterruptedException{
		 List<String> lists = zk.getChildren(dir, true);
		 if (lists!=null&&lists.size()>0) {
			 for (int i = 0; i < lists.size(); i++) {
				 deleteDir(dir+"/"+lists.get(i), -1);
			}
		}
		 zk.delete(dir, -1);
  }
}
