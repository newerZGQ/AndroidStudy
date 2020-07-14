package study.zgq.com.androidstudy.opengl;

import android.content.Context;
import android.opengl.GLES30;
import android.opengl.GLSurfaceView;
import android.opengl.Matrix;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;

public class MyTDView extends GLSurfaceView {
    Triangle triangle;
    public MyTDView(Context context) {
        super(context);
        triangle = new Triangle(this);
        this.setEGLContextClientVersion(3);
        setRenderer(new MyTDViewRender());
        this.setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);
    }

    public class MyTDViewRender implements Renderer {

        @Override
        public void onSurfaceCreated(GL10 gl, EGLConfig config) {
            //������Ļ����ɫRGBA
            GLES30.glClearColor(1,0,0,1.0f);
            //����ȼ��
            GLES30.glEnable(GLES30.GL_DEPTH_TEST);
        }

        @Override
        public void onSurfaceChanged(GL10 gl, int width, int height) {
            GLES30.glViewport(0, 0, width, height);
            float ratio = (float) width / height;
            Matrix.frustumM(Triangle.mProjMatrix, 0, -ratio, ratio, -1, 1, 1, 10);
            Matrix.setLookAtM(Triangle.mVMatrix, 0, 0,0,3,0f,0f,0f,0f,1.0f,0.0f);
        }

        @Override
        public void onDrawFrame(GL10 gl) {
            GLES30.glClear( GLES30.GL_DEPTH_BUFFER_BIT | GLES30.GL_COLOR_BUFFER_BIT);
            triangle.drawSelf();
        }
    }
}
