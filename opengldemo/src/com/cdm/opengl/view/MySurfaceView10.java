package com.cdm.opengl.view;
import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

import android.content.Context;
import android.opengl.GLES20;
import android.opengl.GLSurfaceView;

import com.cdm.opengl.shape.TrianglePair;
import com.cdm.opengl.util.Constant;
import com.cdm.opengl.util.MatrixState;

public class MySurfaceView10 extends GLSurfaceView 
{
    private SceneRenderer mRenderer;//场景渲染器
    boolean cullFaceFlag=false;//是否开启背面剪裁的标志位
    
	public MySurfaceView10(Context context) {
        super(context);
        this.setEGLContextClientVersion(2); //设置使用OPENGL ES2.0
        mRenderer = new SceneRenderer();	//创建场景渲染器
        setRenderer(mRenderer);				//设置渲染器		        
        setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);//设置渲染模式为主动渲染   
       // Constant.CURR_DRAW_MODE = Constant.GL_POINTS;//初始化为绘制点模式
    }
	
	
	//设置是否开启背面剪裁的标志位
	public void setCullFace(boolean flag)
	{
		cullFaceFlag=flag;
	}
	
	boolean cwCcwFlag=false;//是否打开自定义卷绕的标志位
	//设置是否打开自定义卷绕的标志位
	public void setCwOrCcw(boolean flag)
	{
		cwCcwFlag=flag;
	}

	private class SceneRenderer implements GLSurfaceView.Renderer 
    {   
    	TrianglePair pair;
    	
        public void onDrawFrame(GL10 gl) 
        {
        	//判断是否要打开背面剪裁
        	if(cullFaceFlag)
			{
				GLES20.glEnable(GLES20.GL_CULL_FACE);//打开背面剪裁   
			}
			else
			{
				GLES20.glDisable(GLES20.GL_CULL_FACE);//关闭背面剪裁   
			}
        	
        	//判断是否需要打开自定义卷绕
        	if(cwCcwFlag)
		    {
		    	GLES20.glFrontFace(GLES20.GL_CCW);//使用自定义卷绕
		    }
		    else
		    {
		    	GLES20.glFrontFace(GLES20.GL_CW);//不使用自定义卷绕
		    }
        	//清除深度缓冲与颜色缓冲
            GLES20.glClear( GLES20.GL_DEPTH_BUFFER_BIT | GLES20.GL_COLOR_BUFFER_BIT);
           
            
            MatrixState.pushMatrix();
            MatrixState.translate(0, -1.4f, 0);//沿y方向平移
            pair.drawSelf();
            MatrixState.popMatrix();
            
            
        }  

        public void onSurfaceChanged(GL10 gl, int width, int height) {
            //设置视窗大小及位置 
        	GLES20.glViewport(0, 0, width, height); 
        	//计算GLSurfaceView的宽高比
            Constant.ratio = (float) width / height;
			// 调用此方法计算产生透视投影矩阵
            MatrixState.setProjectFrustum(-10*Constant.ratio, 10*Constant.ratio, -10, 10, 10, 100);
			// 调用此方法产生摄像机9参数位置矩阵
			MatrixState.setCamera(0, 0f, 20, 0f, 0f, 0f, 0f, 1.0f, 0.0f);
            
            //初始化变换矩阵
            MatrixState.setInitStack();
        }

        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //设置屏幕背景色RGBA
            GLES20.glClearColor(0,0,0, 1.0f);  
            //创建点或线对象
            pair = new TrianglePair(MySurfaceView10.this);
            //打开深度检测
            GLES20.glEnable(GLES20.GL_DEPTH_TEST);
            //打开背面剪裁   
           // GLES20.glEnable(GLES20.GL_CULL_FACE);  
        }
    }
}
