package com.cdm.opengl.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.cdm.opengl.shape.Cube;
import com.cdm.opengl.util.Constant;
import com.cdm.opengl.util.MatrixState;

public class MySurfaceView3 extends GLSurfaceView {
	
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;
	private SceneRenderer mRenderer;
	private float mPreviousX;
	private float mPreviousY;

	public MySurfaceView3(Context context) {
		super(context);
		this.setEGLContextClientVersion(2); //设置使用OPENGL ES2.0
        mRenderer = new SceneRenderer();	//创建场景渲染器
        setRenderer(mRenderer);				//设置渲染器		        
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
	}

	private class SceneRenderer implements Renderer{
		Cube cube;
		float angle = 30;
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			System.out.println("onSurfaceCreated");
			GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
			cube = new Cube(MySurfaceView3.this);
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);
			//打开背面剪裁   
			GLES20.glEnable(GLES20.GL_CULL_FACE);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			System.out.println("onSurfaceChanged");
			GLES20.glViewport(0, 0, width, height);
			Constant.ratio = (float)width/height;
			float ratio = Constant.ratio;
			//MatrixState.setProjectOrtho(-ration, ration, -1, 1, 1, 10);
			MatrixState.setProjectFrustum(-ratio*0.8f, ratio*1.2f, -1, 1, 8, 100);
			MatrixState.setCamera(-16f, 8f, 45f, 0, 0, 0, 0, 1, 0);
			
			MatrixState.setInitStack();
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			//System.out.println("onDrawFrame");
			GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
			
			MatrixState.pushMatrix();
			cube.drawSelf();
			MatrixState.popMatrix();
			//
			MatrixState.pushMatrix();
			MatrixState.translate(4, 0, 0);
			MatrixState.rotate(angle, 0, 0, 1);
			cube.drawSelf();
			MatrixState.popMatrix();
			angle+=0.4f;
		}
		
	}

}
