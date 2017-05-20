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
    private boolean isRunning = false;
    private SeekBar seekBar;
    public SoundPlayerView(Context context,long duration) {
        super(context);
        this.durationInSeconds = duration;
    }
    public void onDraw(Canvas canvas) {
        if(time == 0) {
            w = canvas.getWidth();
            h = canvas.getHeight();
            seekBar = new SeekBar();
        }
        seekBar.draw(canvas);
        time++;
    }
    public void update() {
        seekBar.update();
        postInvalidate();
    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }
    private class SeekBar {
        private long currentTime = 0;
        private float x = 0,y;
        public SeekBar() {
            y = h/10;
        }
        public void draw(Canvas canvas) {
            paint.setStyle(Paint.Style.STROKE);
            paint.setStrokeWidth(h/20);
            paint.setColor(Color.argb(150,255,0,0));
            canvas.drawLine(0,y,w,y,paint);
            paint.setColor(Color.RED);
            canvas.drawLine(0,y,x,y,paint);
            paint.setStyle(Paint.Style.FILL);
            paint.setColor(Color.argb(150,255,0,0));
            canvas.drawCircle(x,y,h/20,paint);
        }
        public void update() {
            currentTime++;
            x = w*((currentTime*1.0f)/durationInSeconds);
        }

    }
}
