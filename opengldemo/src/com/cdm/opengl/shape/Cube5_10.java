package com.cdm.opengl.shape;
import static com.cdm.opengl.util.Constant.UNIT_SIZE;
import android.opengl.GLSurfaceView;

import com.cdm.opengl.util.MatrixState;

//������
public class Cube5_10
{
	//���ڻ��Ƹ��������ɫ����
	ColorRect cr;
	
	public Cube5_10(GLSurfaceView mv)
	{
		//�������ڻ��Ƹ��������ɫ����
		cr=new ColorRect(mv);
	}
	
	public void drawSelf()
	{
		//�ܻ���˼�룺ͨ����һ����ɫ������ת��λ��������ÿ�����λ��
		//�����������ÿ����
		
		//�����ֳ�
		MatrixState.pushMatrix();
		
		//����ǰС��
		MatrixState.pushMatrix();
		MatrixState.translate(0, 0, UNIT_SIZE);
		cr.drawSelf();		
		MatrixState.popMatrix();
		
		//���ƺ�С��
		MatrixState.pushMatrix();		
		MatrixState.translate(0, 0, -UNIT_SIZE);
		MatrixState.rotate(180, 0, 1, 0);
		cr.drawSelf();		
		MatrixState.popMatrix();
		
		//�����ϴ���
		MatrixState.pushMatrix();	
		MatrixState.translate(0,UNIT_SIZE,0);
		MatrixState.rotate(-90, 1, 0, 0);
		cr.drawSelf();
		MatrixState.popMatrix();
		
		//�����´���
		MatrixState.pushMatrix();	
		MatrixState.translate(0,-UNIT_SIZE,0);
		MatrixState.rotate(90, 1, 0, 0);
		cr.drawSelf();
		MatrixState.popMatrix();
		
		//���������
		MatrixState.pushMatrix();	
		MatrixState.translate(UNIT_SIZE,0,0);
		MatrixState.rotate(-90, 1, 0, 0);
		MatrixState.rotate(90, 0, 1, 0);
		cr.drawSelf();
		MatrixState.popMatrix();
		
		//�����Ҵ���
		MatrixState.pushMatrix();				
		MatrixState.translate(-UNIT_SIZE,0,0);
		MatrixState.rotate(90, 1, 0, 0);
		MatrixState.rotate(-90, 0, 1, 0);
		cr.drawSelf();
		MatrixState.popMatrix();
		
		//�ָ��ֳ�
		MatrixState.popMatrix();
	}
	

}
