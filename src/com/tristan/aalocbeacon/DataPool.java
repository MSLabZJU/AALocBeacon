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
	
	private static double[] resultLoc = new double[3]; //定位结果
	private static int loopNum = 5;
	private static double[][] resultLoop = new double[loopNum][3] ; //存循环几次的结果
	private static int loopNumTemp = 0;   //目前正处理的位置
	
	public static int time = 0;
	
	//估计的真实位置
    public static double realOne, realTwo;
    
    //给user发指令的PrintWriter
    private static PrintWriter pw;
    
    //用于存放录音文件的与 文件夹名称的相关标志位
	public static int FolderNum = 1;
	//录音文件文件名称的相关标志位
	public static int FileNum = 0;
	
	private static String rawfileName;
	private static String wavfileName;
	public static final String REFERAUDIO_STRING = "/mnt/sdcard/data_refer.wav";
    
	//************构造器区域*****************
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
