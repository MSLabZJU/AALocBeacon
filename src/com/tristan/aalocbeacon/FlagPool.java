package com.tristan.aalocbeacon;

/**
 * 
 * @author TristanHuang
 * 2018-2-25  ����8:14:09
 */
public class FlagPool {
	/*
	 * ��ʼ¼���ķ�����־λ��ÿ�ν������Ӻ�ÿ��beacon�ᷢ�͹���һ��"520"�ź�
	 * Ȼ�������@param feedBackStart�ͻ�++��5�����Ļ��ͻ���TimerTask���ж��Ƿ���5
	 */
	public static int feedBackStart = 0; //��ʼ¼���ķ�����־λ
	public static boolean isRecord = false; //��¼¼��״̬�ı�־λ
	
	private static boolean connect = false;
	
	private static boolean compute = false;

	
	//********************����������***********************
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
