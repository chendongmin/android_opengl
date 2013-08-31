package com.cdm.opengl.util;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

import android.content.res.Resources;
import android.opengl.GLES20;
import android.util.Log;

public class ShaderUtil {
	
	public static String TAG = "ES20_ERROR";
	
	public static int loadShader(int shaderType,String source){
		int shader = GLES20.glCreateShader(shaderType);
		if(shader != 0){
			GLES20.glShaderSource(shader, source);
			GLES20.glCompileShader(shader);
			int[] compiled = new int[1];
			GLES20.glGetShaderiv(shader,GLES20.GL_COMPILE_STATUS,compiled,0);
			if(compiled[0] == 0){
				Log.e(TAG, "Could not compile shader "+shaderType+":");
				Log.e(TAG, GLES20.glGetShaderInfoLog(shader));
				GLES20.glDeleteShader(shader);
				shader = 0;
			}
		}
		return shader;
	}
	
	public static int createProgram(String vertexSource,String fragmentSource){
		//���ض�����ɫ��
		int vertexShader = loadShader(GLES20.GL_VERTEX_SHADER, vertexSource);
		if(0 == vertexShader){
			return 0;
		}
		//����ƬԪ��ɫ��
		int pixelShader = loadShader(GLES20.GL_FRAGMENT_SHADER, fragmentSource);
		if(0 == pixelShader){
			return 0;
		}
		int program = GLES20.glCreateProgram();
		if(program != 0){
			GLES20.glAttachShader(program, vertexShader);
			checkGlError("glAttachShader");
			GLES20.glAttachShader(program, pixelShader);
			checkGlError("glAttachShader");
			GLES20.glLinkProgram(program);
			int[] linkStatus = new int[1];
			GLES20.glGetProgramiv(program, GLES20.GL_LINK_STATUS, linkStatus, 0);
			if(linkStatus[0] != GLES20.GL_TRUE){
				Log.e(TAG, "Could not link program: ");
				Log.e(TAG, GLES20.glGetProgramInfoLog(program));
				GLES20.glDeleteProgram(program);
				program = 0;
			}
		}
		return program;
	}
	
	public static void checkGlError(String op){
		int error ;
		while((error=GLES20.glGetError()) != GLES20.GL_NO_ERROR){
			Log.e(TAG, op+": glError "+error);
			throw new RuntimeException();
		}
	}
	
	public static String loadFromAssetsFile(String fname,Resources r){
		String result = null;
		InputStream in = null;
		ByteArrayOutputStream out = null;
		try {
			in = r.getAssets().open(fname);
			int ch = 0;
			out = new ByteArrayOutputStream();
			while((ch=in.read())!=-1){
				out.write(ch);
			}
			byte[] buff = out.toByteArray();
			result = new String(buff,"utf-8");
			result = result.replaceAll("\\r\\n", "\n"); 
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			if(null != out){
				try {
					out.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
			if(null != in){
				try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
		return result;
	}

}
