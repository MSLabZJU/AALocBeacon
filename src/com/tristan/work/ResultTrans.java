package com.tristan.work;

import java.io.PrintWriter;

import android.provider.ContactsContract.Contacts.Data;
import android.util.Log;

import com.tristan.aalocbeacon.DataPool;
import com.tristan.aalocbeacon.FlagPool;

public class ResultTrans implements Runnable {

	private FlagPool flagPool;
	private DataPool dataPool;
	private PrintWriter pw;
	
	public ResultTrans(DataPool dataPool, FlagPool flagPool){
		this.dataPool = dataPool;
		this.flagPool = flagPool;
	}
	
	public void run() {
		while (true) {
			if (flagPool.getCompute()) {
				flagPool.resetCompute();  //等待ComputeImpl处理完
				pw = dataPool.getPrintWriter();
				pw.println("521");  //发数据
				pw.println(String.valueOf(dataPool.realOne));
				pw.println(String.valueOf(dataPool.realTwo));
				break;
			}
			Log.i("beacon", "waiting for ComputeImpl");
		}
	}

}
