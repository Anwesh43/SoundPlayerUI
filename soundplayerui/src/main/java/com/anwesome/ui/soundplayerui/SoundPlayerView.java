package com.anwesome.ui.soundplayerui;

import android.content.Context;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by anweshmishra on 21/05/17.
 */
public class SoundPlayerView extends View{
    private Context context;
    private long durationInSeconds;
    public SoundPlayerView(Context context,long duration) {
        super(context);
        this.durationInSeconds = duration;
    }
    public void onDraw(Canvas canvas) {

    }
    public boolean onTouchEvent(MotionEvent event) {
        return true;
    }

}
