package com.tristan.aalocbeacon;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.w3c.dom.Node;

import com.tristan.algorithm.*;

import android.R.integer;

public class DataPool {
	
	public static final int NODENUM = 5;
	
	private static double[] resultLoc = new double[3]; //��λ���
	private static int loopNum = 5;
	private static double[][] resultLoop = new double[loopNum][3] ; //��ѭ�����εĽ��
	private static int loopNumTemp = 0;   //Ŀǰ�������λ��
	
	public static int time = 0;
	
	//���Ƶ���ʵλ��
    public static double realOne, realTwo;
    
    //��user��ָ���PrintWriter
    private static PrintWriter pw;
    
    //���ڴ��¼���ļ����� �ļ������Ƶ���ر�־λ
	public static int FolderNum = 1;
	//¼���ļ��ļ����Ƶ���ر�־λ
	public static int FileNum = 0;
	
	private static String rawfileName;
	private static String wavfileName;
	public static final String REFERAUDIO_STRING = "/mnt/sdcard/data_refer.wav";
    
	//************����������*****************
	private static DataPool instance = new DataPool();
	
	private DataPool(){
		super();
	}
	
	public static DataPool getInstance(){
		return instance;
	}
	//****************************************
	

	public boolean isLoopEnd(){
		return loopNum == loopNumTemp;
	}
	
	
	public String getRawfileName() {
		return "/mnt/sdcard/Data"+FolderNum+"/data_"+FileNum+".raw";
	}
	
	public String getWavfileName() {
		return "/mnt/sdcard/Data"+FolderNum+"/data_"+FileNum+".wav";
	}
	
	public String getFolderName(){
		return "mnt/sdcard/Data" + String.valueOf(FolderNum);
	}
	
    public void setPrintWriter(PrintWriter pw){
    	this.pw = pw;
    }
    
    public PrintWriter getPrintWriter(){
    	return pw;
    }
    
    
    public void setRealPl(double valOne, double valTwo) {
    	realOne = valOne;
    	realTwo = valTwo;
	}
}
