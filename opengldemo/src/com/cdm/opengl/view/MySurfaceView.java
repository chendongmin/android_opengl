package com.cdm.opengl.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import com.cdm.opengl.shape.SixPointerStar;
import com.cdm.opengl.util.MatrixState;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.view.MotionEvent;

public class MySurfaceView extends GLSurfaceView {
	
	private final float TOUCH_SCALE_FACTOR = 180.0f/320;
	private SceneRenderer mRenderer;
	private float mPreviousX;
	private float mPreviousY;

	public MySurfaceView(Context context) {
		super(context);
	}
	
	
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		float y = e.getY();
		float x = e.getX();
		switch(e.getAction()){
		case MotionEvent.ACTION_MOVE:{
			float dy = y - mPreviousY;
			float dx = x - mPreviousX;
			for(SixPointerStar star:mRenderer.ha){
				star.yAngle += dx*TOUCH_SCALE_FACTOR;
				star.XAngle += dy*TOUCH_SCALE_FACTOR;
			}
		}break;
		}
		mPreviousX = x;
		mPreviousY = y;
		return true;
	}



	private class SceneRenderer implements Renderer{
		SixPointerStar[] ha = new SixPointerStar[6];
		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			GLES20.glClearColor(0.5f, 0.5f, 0.5f, 1.0f);
			for(int i=0;i<ha.length;i++){
				ha[i] = new SixPointerStar(MySurfaceView.this, 0.2f, 0.5f, -0.3f*i);
			}
			GLES20.glEnable(GLES20.GL_DEPTH_TEST);
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			GLES20.glViewport(0, 0, width, height);
			float ration = (float)width/height;
			MatrixState.setProjectOrtho(-ration, ration, -1, 1, 1, 10);
			MatrixState.setCamera(0, 0, 3f, 0, 0, 0, 0, 1, 0);
		}

		@Override
		public void onDrawFrame(GL10 gl) {
			GLES20.glClear(GLES20.GL_DEPTH_BUFFER_BIT|GLES20.GL_COLOR_BUFFER_BIT);
			for(SixPointerStar star:ha){
				star.drawSelf();
			}
		}
		
	}

}
