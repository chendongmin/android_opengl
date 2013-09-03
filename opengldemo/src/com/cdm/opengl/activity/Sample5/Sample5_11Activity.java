package com.cdm.opengl.activity.Sample5;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.LinearLayout;
import android.widget.RadioButton;

import com.cdm.opengl.R;
import com.cdm.opengl.view.MySurfaceView10;

public class Sample5_11Activity extends Activity {

	MySurfaceView10 mGLSurfaceView;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
	              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		mGLSurfaceView = new MySurfaceView10(this);
		mGLSurfaceView.requestFocus();
		mGLSurfaceView.setFocusableInTouchMode(true);
		setContentView(R.layout.sample5_11);
		LinearLayout ll=(LinearLayout)findViewById(R.id.main_liner5_11);
		ll.addView(mGLSurfaceView);
		RadioButton rb = (RadioButton) findViewById(R.id.RadioButton01);
		
		// RadioButtonÌí¼Ó¼àÌýÆ÷
		rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mGLSurfaceView.setCwOrCcw(isChecked);
			}
		});
		rb = (RadioButton) findViewById(R.id.RadioButton03);
        // RadioButtonÌí¼Ó¼àÌýÆ÷
		rb.setOnCheckedChangeListener(new OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				mGLSurfaceView.setCullFace(isChecked);
			}
		});
	}

	@Override
	protected void onResume() {
		super.onResume();
		mGLSurfaceView.onResume();
	}

	@Override
	protected void onPause() {
		super.onPause();
		mGLSurfaceView.onPause();
	}
	
	

}
