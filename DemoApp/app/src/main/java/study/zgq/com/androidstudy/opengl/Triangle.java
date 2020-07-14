package study.zgq.com.androidstudy.opengl;

import android.opengl.GLES30;
import android.opengl.Matrix;

import java.nio.ByteBuffer;
import java.nio.ByteOrder;
import java.nio.FloatBuffer;

import javax.microedition.khronos.opengles.GL;

public class Triangle {
    public static float[] mProjMatrix = new float[16];
    public static float[] mVMatrix = new float[16];
    public static float[] mMVPMatrix = new float[16];
    int program;
    int muMVPPositionHandle;
    int muMVPColorHandle;
    int muMVPUniform4fvHandle;
    int mVertexShader;
    int mFragmentShader;
    static float[] mMMatrix = new float[16];
    FloatBuffer vertexBuffer;
    FloatBuffer colorBuffer;
    int vCount;
    float xAngle = 45;

    MyTDView myTDView;
    public Triangle(MyTDView view){
        this.myTDView = view;
        initVertexData();
        initShader();
    }

    public void initVertexData() {
        vCount = 3;
        final float UNIT_SIZE = 0.2f;
        float[] vertex = new float[]{-4*UNIT_SIZE,0,0,0,-4*UNIT_SIZE,0,4*UNIT_SIZE,0,0};
        ByteBuffer vbb = ByteBuffer.allocateDirect(vertex.length * 4);
        vbb.order(ByteOrder.nativeOrder());
        vertexBuffer = vbb.asFloatBuffer();
        vertexBuffer.put(vertex);
        vertexBuffer.position(0);
        float[] color = new float[]{1,1,1,1,0,1,1,0,0,1,0,0};
        ByteBuffer cbb = ByteBuffer.allocateDirect(color.length * 4);
        cbb.order(ByteOrder.nativeOrder());
        colorBuffer = cbb.asFloatBuffer();
        colorBuffer.put(color);
        colorBuffer.position(0);
    }

    public void initShader() {
        mVertexShader = ShaderUtils.loadShader(myTDView.getContext(), "vertex.sh", GLES30.GL_VERTEX_SHADER);
        mFragmentShader = ShaderUtils.loadShader(myTDView.getContext(), "fragment.sh", GLES30.GL_FRAGMENT_SHADER);
        program = ShaderUtils.createProgram(mVertexShader, mFragmentShader);
        muMVPPositionHandle = GLES30.glGetAttribLocation(program, "aPosition");
        muMVPColorHandle = GLES30.glGetAttribLocation(program, "aColor");
        muMVPUniform4fvHandle = GLES30.glGetUniformLocation(program, "uMVPMatrix");
    }

    public void drawSelf() {
        GLES30.glUseProgram(program);
        Matrix.setRotateM(mMMatrix,0,0,0,1,0);
        Matrix.translateM(mMMatrix,0,0,0,1);
        Matrix.rotateM(mMMatrix,0,xAngle,1,0,0);
        Matrix.multiplyMM(mMVPMatrix, 0, mMMatrix, 0, mMVPMatrix, 0);
        GLES30.glUniformMatrix4fv(muMVPUniform4fvHandle, 1, false, getFinalMatrix(mMMatrix), 0);

        GLES30.glVertexAttribPointer(muMVPPositionHandle, 3, GLES30.GL_FLOAT, false, 3*4, vertexBuffer);
        GLES30.glVertexAttribPointer(muMVPColorHandle, 4, GLES30.GL_FLOAT,false, 4*4, colorBuffer);
        GLES30.glEnableVertexAttribArray(muMVPPositionHandle);
        GLES30.glEnableVertexAttribArray(muMVPColorHandle);

        GLES30.glDrawArrays(GLES30.GL_TRIANGLES, 0, vCount);
    }

    public static float[] getFinalMatrix(float[] spec){
        mMVPMatrix = new float[16];
        Matrix.multiplyMM(mMVPMatrix, 0 ,mVMatrix, 0, spec, 0);
        Matrix.multiplyMM(mMVPMatrix, 0 ,mProjMatrix, 0, mMVPMatrix, 0);
        return mMVPMatrix;
    }
}
