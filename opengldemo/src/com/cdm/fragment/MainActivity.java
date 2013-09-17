package com.cdm.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.view.Window;

import com.cdm.fragment.FragmentIndicator.OnIndicateListener;
import com.cdm.opengl.R;

public class MainActivity extends FragmentActivity {
	
	public static Fragment[] mFragments;
	
	
	@Override  
    protected void onCreate(Bundle savedInstanceState) {  
        super.onCreate(savedInstanceState);  
        requestWindowFeature(Window.FEATURE_NO_TITLE);  
        setContentView(R.layout.fragment_activity_main);  
  
        setFragmentIndicator(0);  
          
    }  
  
    /** 
     * ≥ı ºªØfragment 
     */  
    private void setFragmentIndicator(int whichIsDefault) {  
        mFragments = new Fragment[3];  
       /* mFragments[0] = getSupportFragmentManager().findFragmentById(R.id.fragment_home);  
        mFragments[1] = getSupportFragmentManager().findFragmentById(R.id.fragment_search);  
        mFragments[2] = getSupportFragmentManager().findFragmentById(R.id.fragment_settings);  
        getSupportFragmentManager().beginTransaction().hide(mFragments[0])  
                .hide(mFragments[1]).hide(mFragments[2]).show(mFragments[whichIsDefault]).commit();*/  
  
        FragmentIndicator mIndicator = (FragmentIndicator) findViewById(R.id.indicator);  
        FragmentIndicator.setIndicator(whichIsDefault);  
        mIndicator.setOnIndicateListener(new OnIndicateListener() {  
            @Override  
            public void onIndicate(View v, int which) {  
                /*getSupportFragmentManager().beginTransaction()  
                        .hide(mFragments[0]).hide(mFragments[1])  
                        .hide(mFragments[2]).show(mFragments[which]).commit();*/  
            }  
        });  
    }  
  
    @Override  
    protected void onResume() {  
        super.onResume();  
    }  
      
    @Override  
    protected void onPause() {  
        super.onPause();  
    } 

}
