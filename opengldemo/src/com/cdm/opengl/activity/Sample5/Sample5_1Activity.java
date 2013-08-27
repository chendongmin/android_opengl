package com.cdm.opengl.activity.Sample5;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;

import com.cdm.opengl.view.MySurfaceView;

public class Sample5_1Activity extends Activity {

	MySurfaceView mview;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mview = new MySurfaceView(this);
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
