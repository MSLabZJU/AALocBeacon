package com.tristan.aalocbeacon;

import java.io.File;
import java.util.HashMap;
import java.util.Timer;

import com.tristan.work.ConnectImpl;

import android.app.Activity;
import android.media.AudioManager;
import android.media.AudioRecord;
import android.media.SoundPool;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.TextView;


/**
 * 
 * @author TristanHuang
 * 2018-2-27  下午9:00:45
 */
public class MainActivity extends Activity {

	/** static data.*/
	private DataPool dataPool;
	private FlagPool flagPool;
	private ConfigPool configPool;
	/** make sound */
	private AudioRecord audioRecord;
	private static SoundPool soundPool;
	private HashMap<Integer, Integer> spMap;
	/** thread for beacon. */
	private Thread workingManThread;
	/** textview */
	private TextView tv_state;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		findGuys();
		
		initViews();
		
		workingManThread = new Thread(new ConnectImpl(MainActivity.this, 
				ConfigPool.IP, ConfigPool.PORT, 
				dataPool, flagPool, audioRecord, tv_state));
		workingManThread.start();
	}

	private void findGuys() {
		tv_state = (TextView) findViewById(R.id.tv_state);
		/** static data. */
		dataPool = DataPool.getInstance();
		flagPool = FlagPool.getInstance();
		configPool = ConfigPool.getInstance();
		/** create the folder for wavfile */
		while (new File(dataPool.getFolderName()).exists()) {
			DataPool.FolderNum++;
		}
		/** objects for making sound. */
		audioRecord = configPool.getAudioRecord();
		soundPool = new SoundPool(7, AudioManager.STREAM_MUSIC, 0);// 同时播放的最大音频数为7
		spMap = new HashMap<Integer, Integer>();
		spMap.put(1, soundPool.load(this, R.raw.data_refer, 1));
	}
	
	private void initViews() {
		// keep the screen on
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON, 
				WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);  
	}

	/**
	 * @param sound   spMap中信号的序号
	 * @param number  循环的次数
	 */
	public void playSounds(int sound, int number) {

		// AudioManger对象通过getSystemService(Service.AUDIO_SERVICE)获取
		AudioManager am = (AudioManager) this.getSystemService(this.AUDIO_SERVICE);
		// 获得手机播放最大音乐音量
		float audioMaxVolumn = am.getStreamMaxVolume(AudioManager.STREAM_MUSIC);
		// float audioCurrentVolumn =
		// am.getStreamVolume(AudioManager.STREAM_MUSIC);
		float volumnRatio = audioMaxVolumn;
		soundPool.play(spMap.get(sound), volumnRatio, volumnRatio, 1, number, 1);
	}
	
	public void stopSound(){
		if (audioRecord != null) {
			Log.i("IO", "stop Recording...");
			flagPool.setRecordStatus(false);
			audioRecord.stop();
		}
	}
}
