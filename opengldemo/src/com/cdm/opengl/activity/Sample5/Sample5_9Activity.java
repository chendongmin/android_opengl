package com.cdm.opengl.activity.Sample5;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

import com.cdm.opengl.view.MySurfaceView8;

public class Sample5_9Activity extends Activity {

	MySurfaceView8 mview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mview = new MySurfaceView8(this);
		mview.requestFocus();
		mview.setFocusableInTouchMode(true);
		setContentView(mview);
	}

	@Override
	protected void onResume() {
		super.onResume();
		mview.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mview.onPause();
	}
	
	

}
