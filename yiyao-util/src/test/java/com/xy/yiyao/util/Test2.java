package com.xy.yiyao.util;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.xy.yiyao.util.http.HttpClientUtils;

public class Test2 {

	private static ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(5);
	
	public static void main(String[] args) throws Exception {
		scheduledThreadPoolExecutor.scheduleAtFixedRate(new Runnable() {
			
			@Override
			public void run() {
				try {
					System.out.println("--------------------");
					send();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}, 0, 1, TimeUnit.SECONDS);
	}
	
	public static void send() throws IOException {
		
		Map<String, String> map = new HashMap<String, String>();
		map.put("name", "大哥大");
		map.put("mobile", "4154613132");
		map.put("address", "xxfdsafsd" + new Random().nextInt(500000));
		map.put("password", Integer.toString(new Random().nextInt()));
		map.put("points", Integer.toString(new Random().nextInt(500)));
		
		Map<String, String> headerMap = new HashMap<String, String>();
		headerMap.put("channel", "O2O");
		headerMap.put("key1", "xxfdasf");
		headerMap.put("key2", "456481");
		
		HttpClientUtils.toPost("http://localhost:9090/reset/user/addUser", headerMap, map);
	}
	
}
