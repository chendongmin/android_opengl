package com.cdm.opengl.activity.Sample7;

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
import com.cdm.opengl.view.Sample7.MySurfaceView7_2;

public class MyActivity7_2 extends Activity {
	private MySurfaceView7_2 mGLSurfaceView;
    @Override
    protected void onCreate(Bundle savedInstanceState)  
    {
    	super.onCreate(savedInstanceState);         
        //����Ϊȫ��
        requestWindowFeature(Window.FEATURE_NO_TITLE); 
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN ,  
		              WindowManager.LayoutParams.FLAG_FULLSCREEN);
		//����Ϊ����ģʽ
		setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
		//�л���������
		setContentView(R.layout.main7_2);		
		//��ʼ��GLSurfaceView
        mGLSurfaceView = new MySurfaceView7_2(this);
        mGLSurfaceView.requestFocus();//��ȡ����
        mGLSurfaceView.setFocusableInTouchMode(true);//����Ϊ�ɴ���  
        //���Զ����GLSurfaceView��ӵ����LinearLayout��
        LinearLayout ll=(LinearLayout)findViewById(R.id.main_liner7_2); 
        ll.addView(mGLSurfaceView); 
        
        //ΪRadioButton��Ӽ�������SxTѡ�����
        RadioButton rab=(RadioButton)findViewById(R.id.edge7_2_1);
        rab.setOnCheckedChangeListener(
            new OnCheckedChangeListener()
            {
     			@Override
     			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
     			{
     				//GL_CLAMP_TO_EDGEģʽ��
     				if(isChecked)
     				{
     					mGLSurfaceView.currTextureId=mGLSurfaceView.textureCTId;
     				}
     			}        	   
            }         		
        );       
        rab=(RadioButton)findViewById(R.id.repeat7_2_1);
        rab.setOnCheckedChangeListener(
            new OnCheckedChangeListener()
            {
     			@Override
     			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
     			{
     				//GL_REPEATģʽ��
     				if(isChecked)
     				{
     					mGLSurfaceView.currTextureId=mGLSurfaceView.textureREId;
     				}
     			}        	   
            }         		
        );        
    
        
        //ΪRadioButton��Ӽ�������SxTѡ�����
        RadioButton rb=(RadioButton)findViewById(R.id.x11);
        rb.setOnCheckedChangeListener(
            new OnCheckedChangeListener()
            {
     			@Override
     			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
     			{
     				if(isChecked)
     				{//����Ϊ��������SxT=1x1
     					mGLSurfaceView.trIndex=0;
     				}
     			}        	   
            }         		
        );       
        rb=(RadioButton)findViewById(R.id.x42);
        rb.setOnCheckedChangeListener(
            new OnCheckedChangeListener()
            {
     			@Override
     			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
     			{
     				if(isChecked)
     				{//����Ϊ��������SxT=4x2
     					mGLSurfaceView.trIndex=1;
     				}
     			}        	   
            }         		
        );     
        rb=(RadioButton)findViewById(R.id.x44);
        rb.setOnCheckedChangeListener(
            new OnCheckedChangeListener()
            {
     			@Override
     			public void onCheckedChanged(CompoundButton buttonView,boolean isChecked) 
     			{
     				if(isChecked)
     				{//����Ϊ��������SxT=4x4
     					mGLSurfaceView.trIndex=2;
     				}
     			}        	   
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



