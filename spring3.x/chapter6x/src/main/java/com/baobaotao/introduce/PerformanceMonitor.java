package com.baobaotao.introduce;

public class PerformanceMonitor {
	private static ThreadLocal<MethodPerformance> performaceRecord = new ThreadLocal<MethodPerformance>();
	public static void begin(String method) {
		System.out.println("begin monitor...");
		MethodPerformance mp = performaceRecord.get();
		if(mp == null){
			mp = new MethodPerformance(method);
			performaceRecord.set(mp);
		}else{
		    mp.reset(method);	
		}
	}
	public static void end() {
		System.out.println("end monitor...");
		MethodPerformance mp = performaceRecord.get();
		mp.printPerformance();
	}
}
