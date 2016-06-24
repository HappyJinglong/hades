package com.flyrish.hades.util;

import com.flyrish.hades.service.ext.IRedisServiceExt;

public class ThreadDemo extends Thread {
	public IRedisServiceExt redisServiceExt;

	public ThreadDemo(IRedisServiceExt redisServiceExt) {
		this.redisServiceExt = redisServiceExt;
	}

	@Override
	public void run() {
		while(true){
			redisServiceExt.readSingle("aaa");
			try {
				redisServiceExt.save("dd","bb");
				Thread.sleep(2000);
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
}
