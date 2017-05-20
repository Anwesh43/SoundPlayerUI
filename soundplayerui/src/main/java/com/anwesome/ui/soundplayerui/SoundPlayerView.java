package com.anwesome.ui.soundplayerui;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 21/05/17.
 */
public class SoundPlayerView extends View{
    private Context context;
    private Paint paint = new Paint(Paint.ANTI_ALIAS_FLAG);
    private long durationInSeconds;
    private int w,h,time = 0;
    private AnimationRunner animationRunner = new AnimationRunner();
    private boolean isRunning = true;
    private SeekBar seekBar;
    private Thread animationThread;
    public SoundPlayerView(Context context,long duration) {
        super(context);
        this.durationInSeconds = duration;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            seekBar = new SeekBar();
            animationThread = new Thread(animationRunner);
            animationThread.start();
        }
        canvas.drawColor(Color.parseColor("#2A2A2A"));
        seekBar.draw(canvas);
        time++;
    }
    public void update() {
        seekBar.update();
        postInvalidate();
    }
    public void pause() {
        if (isRunning) {
            isRunning = false;
            while (true) {
                try {
                    animationThread.join();
                    break;
                } catch (Exception ex) {

                }
            }
        }
    }
    public void resume() {
        if(!isRunning) {
            isRunning = true;
            animationThread = new Thread(animationRunner);
            animationThread.start();
        }
    }
    public boolean onTouchEvent(MotionEvent event) {
        seekBar.handleTouch(event);
        return true;
    }
    private class SeekBar {
        private long currentTime = 0;
        private float x = 0,y;
        private boolean isDown = false;
        public SeekBar() {
            y = h/3;
        }
        public void draw(Canvas canvas) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(h/20);
            paint.setColor(Color.argb(60,255,0,0));
            canvas.drawLine(0,y,w,y,paint);
            paint.setColor(Color.RED);
            canvas.drawLine(0,y,x,y,paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(150,255,0,0));
            canvas.drawCircle(x,y,h/8,paint);
        }
        public void handleTouch(MotionEvent event) {
            float tx = event.getX(),ty = event.getY();
            switch(event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    if(!isDown && tx>=x-h/5 && tx<=x+h/5 && ty>=y-h/5 && ty<=y+h/5) {
                        isDown = true;
                    }
                    break;
                case MotionEvent.ACTION_MOVE:
                    if(isDown) {
                        x = event.getX();
                        currentTime = (long)(((x*1.0f)/w)*durationInSeconds);

                        postInvalidate();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    if(isDown) {
                        isDown = false;
                        if(!isRunning) {
                            resume();
                        }
                    }
                    break;
            }

        }
        public void update() {
            if(currentTime<durationInSeconds) {
                currentTime++;
                x = w * ((currentTime * 1.0f) / durationInSeconds);
            }
            else {
                isRunning = false;
            }
        }

    }
    private class AnimationRunner implements Runnable {
        public void run() {
            while(isRunning) {
                update();
                try {
                    Thread.sleep(1000);
                }
                catch(Exception ex) {

                }
            }
        }
    }
}
