uniform mat4 uMVPMatrix; 		//�ܱ任����
uniform mat4 uMMatrix; 			//�任����
//uniform vec3 uLightLocation;		//��Դλ��
uniform vec3 uCamera;			//�����λ��
uniform vec3 uLightDirection;
attribute vec3 aPosition;  		//����λ��
attribute vec3 aNormal;    		//������
varying vec3 vPosition;			//���ڴ��ݸ�ƬԪ��ɫ���Ķ���λ��
varying vec4 vAmbient;			//���ڴ��ݸ�ƬԪ��ɫ���Ļ���������ǿ��
varying vec4 vDiffuse;			//���ڴ��ݸ�ƬԪ��ɫ����ɢ�������ǿ��
varying vec4 vSpecular;			//���ڴ��ݸ�ƬԪ��ɫ���ľ��������ǿ��
void directionalLight(					//�������ռ���ķ���
  in vec3 normal,				//������
  inout vec4 ambient,			//����������ǿ��
  inout vec4 diffuse,				//ɢ�������ǿ��
  inout vec4 specular,			//���������ǿ��
  in vec3 lightDirection,			//����ⷽ��
  in vec4 lightAmbient,			//������ǿ��
  in vec4 lightDiffuse,			//ɢ���ǿ��
  in vec4 lightSpecular			//�����ǿ��
){
  ambient=lightAmbient;			//ֱ�ӵó������������ǿ��  
  vec3 normalTarget=aPosition+normal;	//����任��ķ�����
  vec3 newNormal=(uMMatrix*vec4(normalTarget,1)).xyz-(uMMatrix*vec4(aPosition,1)).xyz;
  newNormal=normalize(newNormal); 	//�Է��������
  //����ӱ���㵽�����������
  vec3 eye= normalize(uCamera-(uMMatrix*vec4(aPosition,1)).xyz);  
  //����ӱ���㵽��Դλ�õ�����vp
  //vec3 vp= normalize(lightLocation-(uMMatrix*vec4(aPosition,1)).xyz);
  vec3 vp = normalize(lightDirection);  
  //vp=normalize(vp);//��ʽ��vp
  vec3 halfVector=normalize(vp+eye);	//����������ߵİ�����    
  float shininess=50.0;				//�ֲڶȣ�ԽСԽ�⻬
  float nDotViewPosition=max(0.0,dot(newNormal,vp)); 	//��������vp�ĵ����0�����ֵ
  diffuse=lightDiffuse*nDotViewPosition;				//����ɢ��������ǿ��
  float nDotViewHalfVector=dot(newNormal,halfVector);	//������������ĵ�� 
  float powerFactor=max(0.0,pow(nDotViewHalfVector,shininess)); 	//���淴���ǿ������
  specular=lightSpecular*powerFactor;    			//���㾵��������ǿ��
}
void main(){ 	
   gl_Position = uMVPMatrix * vec4(aPosition,1); //�����ܱ任�������˴λ��ƴ˶���λ��     
   vec4 ambientTemp,diffuseTemp,specularTemp;	  //������������ͨ������ǿ�ȵı��� 
   directionalLight(normalize(aNormal),ambientTemp,diffuseTemp,specularTemp,uLightDirection,
   vec4(0.15,0.15,0.15,1.0),vec4(0.8,0.8,0.8,1.0),vec4(0.7,0.7,0.7,1.0));   
   vAmbient=ambientTemp; 		//������������ǿ�ȴ���ƬԪ��ɫ��
   vDiffuse=diffuseTemp; 		//��ɢ�������ǿ�ȴ���ƬԪ��ɫ��
   vSpecular=specularTemp; 		//�����������ǿ�ȴ���ƬԪ��ɫ��  
   vPosition = aPosition;  //�������λ�ô���ƬԪ��ɫ��
} 