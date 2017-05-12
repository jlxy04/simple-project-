package com.xy.yiyao.util;

import java.io.IOException;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

import com.xy.yiyao.util.http.HttpClientUtils;

public class Test {

	private static ScheduledExecutorService scheduledThreadPoolExecutor = Executors.newScheduledThreadPool(5);
	
	public static void main(String[] args) {
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
		HttpClientUtils.toGet("http://localhost:9090/reset/user/findAll");
	}
}
