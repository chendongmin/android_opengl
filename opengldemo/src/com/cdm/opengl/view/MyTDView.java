package com.cdm.opengl.view;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import com.cdm.opengl.shape.Triangle;

public class MyTDView extends GLSurfaceView {

	final float ANGLE_SPAN = 0.375f;
	RotateThread rthread;
	SceneRenderer mRenderer;

	public MyTDView(Context context) {
		super(context);
		setEGLContextClientVersion(2);
		mRenderer = new SceneRenderer();
		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
	}

	private class SceneRenderer implements Renderer {

		Triangle tle;

		@Override
		public void onDrawFrame(GL10 gl) {
			GLES20.glClear(GLES20.GL_DEPTH_BITS|GLES20.GL_COLOR_BUFFER_BIT);
			tle.drawSelf();
		}

		@Override
		public void onSurfaceChanged(GL10 gl, int width, int height) {
			GLES20.glViewport(0, 0, width, height);
			float ration = (float) width / height;
			Matrix.frustumM(Triangle.mProjMatrix, 0, -ration, ration, -1, 1, 1, 10);
			Matrix.setLookAtM(Triangle.mVMatrix, 0, 0, 0, 3, 0f, 0f, 0f, 0f, 1.0f, 0f);		
		}

		@Override
		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			GLES20.glClearColor(0, 0, 0, 1f);
			tle = new Triangle(MyTDView.this);
			 //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
			rthread = new RotateThread();
			rthread.start();
		}

	}

	private class RotateThread extends Thread {

		public boolean flag = true;

		@Override
		public void run() {
			while (flag) {
				mRenderer.tle.xAngle = mRenderer.tle.xAngle + ANGLE_SPAN;
				try {
					Thread.sleep(20);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}

	}

}
