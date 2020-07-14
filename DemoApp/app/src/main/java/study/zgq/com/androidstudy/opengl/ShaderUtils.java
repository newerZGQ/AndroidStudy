package study.zgq.com.androidstudy.opengl;

import android.annotation.SuppressLint;
import android.content.Context;
import android.opengl.GLES30;
import android.util.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

public class ShaderUtils {
    public static int loadShader(Context context, String fileName, int type){
        StringBuilder shaderSrc = new StringBuilder();
        try {
            InputStream inputStream = context.getAssets().open(fileName);
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
            String line = null;
            while ((line = reader.readLine()) != null){
                shaderSrc.append(line);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        int shader = GLES30.glCreateShader(type);
        GLES30.glShaderSource(shader, shaderSrc.toString());
        GLES30.glCompileShader(shader);
        return shader;
    }

    public static int createProgram(int vertexShader, int fragmentShader){
        int program = GLES30.glCreateProgram();
        GLES30.glAttachShader(program, vertexShader);
        GLES30.glAttachShader(program, fragmentShader);
        GLES30.glLinkProgram(program);
        int[] linkStatus = new int[1];
        GLES30.glGetProgramiv(program, GLES30.GL_LINK_STATUS, linkStatus, 0);
        if (linkStatus[0] != GLES30.GL_TRUE){
            Log.e("ES20_ERROR", "Could not link program: ");
            Log.e("ES20_ERROR", GLES30.glGetProgramInfoLog(program));
            GLES30.glDeleteProgram(program);
            program = 0;
        }
        return program;
    }

    @SuppressLint("NewApi")
    public static void checkGlError(String op)
    {
        int error;
        while ((error = GLES30.glGetError()) != GLES30.GL_NO_ERROR)
        {
            Log.e("ES20_ERROR", op + ": glError " + error);
            throw new RuntimeException(op + ": glError " + error);
        }
    }
}
