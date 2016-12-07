package com.example.emma.catapp;

import android.content.Context;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

/**
 * Created by Emma on 05/12/2016.
 */
public class mcBiorythmSurfaceView extends SurfaceView implements SurfaceHolder.Callback
{
    private SurfaceHolder shBioSurface;

    mcBiorythmsThread drawingThread = null;


    public mcBiorythmSurfaceView(Context context)
    {
        super(context);
        shBioSurface = getHolder();
        shBioSurface.addCallback(this);
        drawingThread = new mcBiorythmsThread(getHolder(), this);
        setFocusable(true);

    }

    public mcBiorythmsThread getThread()
    {
        return drawingThread;
    }

    @Override
    public void surfaceCreated(SurfaceHolder holder)
    {

        drawingThread.setRunning(true);
        drawingThread.start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height)
    {
        drawingThread.setSurfaceSize(width,height);

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder)
    {
        boolean retry = true;
        drawingThread.setRunning(false);
        while(retry)
        {
            try {
                drawingThread.join();
                retry = false;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
