package com.cdm.opengl.util;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import android.opengl.Matrix;

public class MatrixState {
	
	private static float[] mProjMatrix = new float[16];//投影
	private static float[] mVMatrix = new float[16];//摄像机朝向
	private static float[] currMatrix;
	private static float[] mMVPMatrix  = new float[16]; ;
	static float[][] mStack = new float[10][16];
	static int stackTop = -1;
	public static float[] lightLocation = new float[]{0,0,0};
	public static FloatBuffer lightPositionFB;
	static ByteBuffer llbbl = ByteBuffer.allocateDirect(3*4);
	static ByteBuffer llbb= ByteBuffer.allocateDirect(3*4);
	static float[] cameraLocation=new float[3];//摄像机位置
	public static FloatBuffer cameraFB;
	public static float[] lightDirection = new float[]{0,0,1};//定向光
	public static FloatBuffer lightDirectionFB;
	
	
	public static void setLightDirection(float x,float y,float z){
		llbbl.clear();
		lightDirection[0]=x;lightDirection[1]=y;lightDirection[2]=z;
		llbbl.order(ByteOrder.nativeOrder());
		lightDirectionFB = llbbl.asFloatBuffer();
		lightDirectionFB.put(lightDirection);
		lightDirectionFB.position(0);
	}
	
	
	public static void setInitStack(){
		currMatrix = new float[16];
		Matrix.setRotateM(currMatrix, 0, 0, 1, 0, 0);
	}
	
	public static void pushMatrix(){
		stackTop++;
		for(int i=0;i<16;i++){
			mStack[stackTop][i]=currMatrix[i];
		}
	}
	
	public static void popMatrix(){
		for(int i=0;i<16;i++){
			currMatrix[i] = mStack[stackTop][i];
		}
		stackTop--;
	}
	
	
	public static void translate(float x,float y,float z){
		Matrix.translateM(currMatrix, 0, x, y, z);
	}
	
	public static void rotate(float angle, float x, float y, float z) {// 设置绕xyz轴移动
		Matrix.rotateM(currMatrix, 0, angle, x, y, z);
	}
	
	public static void scale(float sx,float sy,float sz){
		Matrix.scaleM(currMatrix, 0, sx, sy, sz);
	}
	
	public static void setCamera(float cx,float cy,float cz,
			float tx,float ty,float tz,
			float upx,float upy,float upz){
		Matrix.setLookAtM(mVMatrix,0, cx, cy, cz, tx, ty, tz, upx, upy, upz);
		
		cameraLocation[0]=cx;
    	cameraLocation[1]=cy;
    	cameraLocation[2]=cz;
    	
    	llbb.clear();
        llbb.order(ByteOrder.nativeOrder());//设置字节顺序
        cameraFB=llbb.asFloatBuffer();
        cameraFB.put(cameraLocation);
        cameraFB.position(0);  
	}
	
	public static void setProjectOrtho(
			float left,float right,
			float bottom,float top,
			float near,float far
			){
		Matrix.orthoM(mProjMatrix, 0, left, right, bottom, top, near, far);
	}
	
	public static float[] getFinalMatrix(float[] spec){
		
		//将摄像机矩阵乘以变换矩阵
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, spec, 0);
		//
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		return mMVPMatrix;
	}
	
	public static float[] getFinalMatrix(){
		
		//将摄像机矩阵乘以变换矩阵
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, currMatrix, 0);
		//
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix, 0);
		return mMVPMatrix;
	}
	
	public static void setProjectFrustum(
			float left,float right,
			float bottom,float top,
			float near,float far
			){
		Matrix.frustumM(mProjMatrix, 0, left, right, bottom, top, near, far);
	}
	
	public static float[] getMMatrix()
    {       
        return currMatrix;
    }
	
	public static void setLightLocation(float x,float y,float z){
		//System.out.println("setLightLocation");
		llbbl.clear();
		lightLocation[0]=x;lightLocation[1]=y;lightLocation[2]=z;
		llbbl.order(ByteOrder.nativeOrder());
		lightPositionFB =llbbl.asFloatBuffer();
		lightPositionFB.put(lightLocation);
		lightPositionFB.position(0);
	}

}
