package com.cdm.opengl.activity.Sample6;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.LinearLayout;
import android.widget.SeekBar;

import com.cdm.opengl.R;
import com.cdm.opengl.view.Sample6.MySurfaceView6_3;

public class Sample6_3_Activity extends Activity {
	private MySurfaceView6_3 mGLSurfaceView;
    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		// 设置为全屏
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
				WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);//设置为横屏
		// 初始化GLSurfaceView
		mGLSurfaceView = new MySurfaceView6_3(this);
		
		// 切换到主界面
		setContentView(R.layout.main6_3);
		LinearLayout ll = (LinearLayout) findViewById(R.id.main_liner6_3);
		ll.addView(mGLSurfaceView);
		SeekBar sb=(SeekBar)this.findViewById(R.id.SeekBar6_3);
		sb.setOnSeekBarChangeListener(
	            new SeekBar.OnSeekBarChangeListener()
	            {
					@Override
					public void onProgressChanged(SeekBar seekBar, int progress,
							boolean fromUser) {
						mGLSurfaceView.setLightOffset((seekBar.getMax()/2.0f-progress)/(seekBar.getMax()/2.0f)*-4);
					}
					@Override
					public void onStartTrackingTouch(SeekBar seekBar) {	}
					@Override
					public void onStopTrackingTouch(SeekBar seekBar) { }            	
	            }
	        );
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