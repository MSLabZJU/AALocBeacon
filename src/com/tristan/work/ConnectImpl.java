package com.tristan.work;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import android.R;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.media.AudioRecord;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.tristan.aalocbeacon.ConfigPool;
import com.tristan.aalocbeacon.DataPool;
import com.tristan.aalocbeacon.FlagPool;
import com.tristan.aalocbeacon.MainActivity;

public class ConnectImpl implements Runnable {
	/** connect */
	private String ip;
	private int port;
	/** io */
	private Socket linkSocket;
	private BufferedReader br;
	private PrintWriter pw;
	/** pools */
	private DataPool dataPool;
	private FlagPool flagPool;

	private MainActivity activity;
	private AudioRecord audioRecord;
	
	private TextView tv_state;

	
	public ConnectImpl() {
		super();
	}

	public ConnectImpl(MainActivity activity, String ip, int port,
			DataPool dataPool, FlagPool flagPool, AudioRecord audioRecord, TextView tv_state){
		this.activity = activity;
		this.audioRecord = audioRecord;
		/** connect */
		this.ip = ip;
		this.port = port;
		/** pools */
		this.dataPool = dataPool;
		this.flagPool = flagPool;
		
		this.tv_state = tv_state;
	}
	
	public void run() {
		while (true) {
			try {
				linkSocket = new Socket(ip, port);
				flagPool.setConnect();
				tv_state.post(new Runnable() {
					public void run() {
						int temp = ConfigPool.PORT - 2000;
						tv_state.setText(String.valueOf(temp));
						tv_state.setTextSize(300);
						tv_state.setTextColor(Color.RED);
					}
				});
				pw = new PrintWriter(linkSocket.getOutputStream(), true);
				dataPool.setPrintWriter(pw);
				
				while (true) {
					br = new BufferedReader(new InputStreamReader(linkSocket.getInputStream()));
					String keyString = br.readLine();
					while (keyString != null) {
						if (keyString.equals("00")) {
							new File(dataPool.getFolderName()).mkdirs();
							flagPool.setRecordStatus(true);
							startRecord();
							// feedback to user meaning starting to work
							pw.println("520"); 
						}
						if (keyString.equals("01")) {
							flagPool.setRecordStatus(false);
							activity.stopSound();
						}
						if (keyString.equals("02")) {
							Log.i("beacon", "I have received '02'. ");
							activity.playSounds(1, 1);
							//播放声音后反馈标志位000给控制端
							pw.println("000");
							Log.i("beacon", "I have sent 000");
							
						}
						if (keyString.equals("reset")) {
							Intent intent = activity.getIntent();
							activity.finish();
							activity.startActivity(intent);
							Log.i("beacon", "reset...");
						}
						keyString = br.readLine();
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	
	private void startRecord() {
		audioRecord.startRecording();
		flagPool.setRecordStatus(true);
		new Thread(new AudioRecordImpl(dataPool, flagPool, audioRecord)).start();
	}
}
