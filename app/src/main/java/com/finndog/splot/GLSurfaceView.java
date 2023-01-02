package com.finndog.splot;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import androidx.annotation.NonNull;

public class GLSurfaceView extends SurfaceView implements SurfaceHolder.Callback2{
    public GLSurfaceView(Context context) {
        super(context);
    }

    @Override
    public void surfaceRedrawNeeded(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {

    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {

    }
}
