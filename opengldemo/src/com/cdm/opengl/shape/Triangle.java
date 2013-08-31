package com.cdm.opengl.shape;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import com.cdm.opengl.util.ShaderUtil;
import com.cdm.opengl.view.MyTDView;

import android.opengl.GLES20;
import android.opengl.Matrix;

public class Triangle {
	
	public static float[] mProjMatrix = new float[16]; //4X4ͶӰ����
	public static float[] mVMatrix = new float[16];//�����λ�ó���Ĳ�������
	public static float[] mMVPMatrix ;//�ܱ任����
	int mProgram;//�Զ�����Ⱦ������ɫ������id
	int muMVPMatrixHandle; //�ܱ任��������
	int maPositionHandle; //����λ����������
	int maColorHandle;//������ɫ��������
	String mVertexShader;//������ɫ������ű�
	String mFragmentShader;//ƬԪ��ɫ���ű�
	static float[] mMMatrix = new float[16];//���������3D�任����,������ת��ƽ�ơ�����
	
	FloatBuffer mVertexBuffer;//
	FloatBuffer mColorBuffer;
	int vCount = 0;//��������
	public float xAngle = 0;//��X����ת�ĽǶ�
	
	public Triangle(MyTDView mv){
		initVertexData();
		initShader(mv);
	}
	
	
	public void initShader(MyTDView mv){
		mVertexShader = ShaderUtil.loadFromAssetsFile("vertex.sh", mv.getResources());
		mFragmentShader = ShaderUtil.loadFromAssetsFile("frag.sh", mv.getResources());
		mProgram = ShaderUtil.createProgram(mVertexShader, mFragmentShader);
		maPositionHandle = GLES20.glGetAttribLocation(mProgram, "aPosition");
		maColorHandle = GLES20.glGetAttribLocation(mProgram, "aColor");
		muMVPMatrixHandle = GLES20.glGetUniformLocation(mProgram, "uMVPMatrix");
		
	}
	
	public void initVertexData(){
		vCount = 3;
		final float UNIT_SIZE = 0.2f;//���õ�λ����
		float vertices[] = new float[]{
				-4*UNIT_SIZE,0,0,
				0,-4*UNIT_SIZE,0,
				4*UNIT_SIZE,0,0
		};
		ByteBuffer vbb = ByteBuffer.allocateDirect(vertices.length*4);
		vbb.order(ByteOrder.nativeOrder());
		mVertexBuffer = vbb.asFloatBuffer();
		mVertexBuffer.put(vertices);
		mVertexBuffer.position(0);
		
		float colors[] = new float[]{
				1,1,1,0,
				0,0,1,0,
				0,1,0,0
		};
		
		ByteBuffer cbb = ByteBuffer.allocateDirect(colors.length*4);
		cbb.order(ByteOrder.nativeOrder());
		mColorBuffer = cbb.asFloatBuffer();
		mColorBuffer.put(colors);
		mColorBuffer.position(0);
		
	}
	
	public static float[] getFinalMatrix(float[] spec){
		mMVPMatrix = new float[16];
		Matrix.multiplyMM(mMVPMatrix, 0, mVMatrix, 0, spec, 0);
		Matrix.multiplyMM(mMVPMatrix, 0, mProjMatrix, 0, mMVPMatrix	,0);
		return mMVPMatrix;
	}
	
	public void drawSelf(){
		GLES20.glUseProgram(mProgram);
		Matrix.setRotateM(mMMatrix, 0, 0, 0, 1, 0);
		Matrix.translateM(mMMatrix, 0, 0, 0, 1);
		Matrix.rotateM(mMMatrix, 0, xAngle, 1, 0, 0);
		
		GLES20.glUniformMatrix4fv(muMVPMatrixHandle, 1, false, Triangle.getFinalMatrix(mMMatrix), 0);
		GLES20.glVertexAttribPointer(maPositionHandle, 3, GLES20.GL_FLOAT, false, 3*4, mVertexBuffer);
		GLES20.glVertexAttribPointer(maColorHandle, 4,GLES20.GL_FLOAT, false, 4*4, mColorBuffer);
		GLES20.glEnableVertexAttribArray(maPositionHandle);
		GLES20.glEnableVertexAttribArray(maColorHandle);
		GLES20.glDrawArrays(GLES20.GL_TRIANGLES, 0, vCount);
	}

}
