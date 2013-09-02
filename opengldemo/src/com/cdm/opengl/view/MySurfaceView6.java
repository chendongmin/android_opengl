package com.cdm.opengl.view;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.cdm.opengl.shape.Belt;
import com.cdm.opengl.shape.Circle;
import com.cdm.opengl.shape.PointsOrLines;
import com.cdm.opengl.util.Constant;
import com.cdm.opengl.util.MatrixState;

public class MySurfaceView6 extends GLSurfaceView 
{
    private SceneRenderer mRenderer;//������Ⱦ��
    
	public MySurfaceView6(Context context) {
        super(context);
        this.setEGLContextClientVersion(2); //����ʹ��OPENGL ES2.0
        mRenderer = new SceneRenderer();	//����������Ⱦ��
        setRenderer(mRenderer);				//������Ⱦ��		        
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//������ȾģʽΪ������Ⱦ   
        Constant.CURR_DRAW_MODE = Constant.GL_POINTS;//��ʼ��Ϊ���Ƶ�ģʽ
    }

	private class SceneRenderer implements GLSurfaceView.Renderer 
    {   
    	Belt belt;
    	Circle circle;
    	
        public void onDrawFrame(GL10 gl) 
        {
        	//�����Ȼ�������ɫ����
            GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
           
            MatrixState.pushMatrix();
            
            MatrixState.pushMatrix();
            MatrixState.translate(-1.2f, 0, 0);
            belt.drawSelf();
            MatrixState.popMatrix();
            
            MatrixState.pushMatrix();
            MatrixState.translate(1.2f, 0, 0);
            circle.drawSelf();
            MatrixState.popMatrix();
            
            MatrixState.popMatrix();
        }  

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //�����Ӵ���С��λ�� 
        	GLES20.glViewport(0, 0, width, height); 
        	//����GLSurfaceView�Ŀ�߱�
            Constant.ratio = (float) width / height;
			// ���ô˷����������͸��ͶӰ����
            MatrixState.setProjectFrustum(-Constant.ratio, Constant.ratio, -1, 1, 8, 100);
			// ���ô˷������������9����λ�þ���
			MatrixState.setCamera(0, 8f, 30, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            
            //��ʼ���任����
            MatrixState.setInitStack();
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //������Ļ����ɫRGBA
            GLES20.glClearColor(0,0,0, 1.0f);  
            //��������߶���
            belt = new Belt(MySurfaceView6.this);
            circle = new Circle(MySurfaceView6.this);
            //����ȼ��
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //�򿪱������   
            GLES20.glEnable(GLES20.GL_CULL_FACE);  
        }
    }
}
