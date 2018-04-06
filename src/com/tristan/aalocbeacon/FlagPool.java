package com.tristan.aalocbeacon;

/**
 * 
 * @author TristanHuang
 * 2018-2-25  下午8:14:09
 */
public class FlagPool {
	/*
	 * 开始录音的反馈标志位，每次建立连接后每个beacon会发送过来一个"520"信号
	 * 然后这里的@param feedBackStart就会++，5个结点的话就会在TimerTask中判断是否到了5
	 */
	public static int feedBackStart = 0; //开始录音的反馈标志位
	public static boolean isRecord = false; //记录录音状态的标志位
	
	private static boolean connect = false;
	
	private static boolean compute = false;

	
	//********************构造器区域***********************
	private static FlagPool instance = new FlagPool();
	
	private FlagPool(){
		super();
	}
	
	public static FlagPool getInstance(){
		return instance;
	}
	//*****************************************************

	
	public void resetCompute(){
		compute = false;
	}
	
	public void setRecordStatus(boolean val){
		isRecord = val;
	}
	
	public boolean getCompute(){
		return compute;
	}
	
	
	public void setCompute() {
		compute = true;
	}
	
	public void setConnect() {
		connect = true;
	}
}
