package com.anwesome.ui.soundplayerui;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.hardware.display.DisplayManager;
import android.view.Display;
import android.view.ViewGroup;

/**
 * Created by anweshmishra on 21/05/17.
 */
public class SoundPlayerUI {
    private long duration = 100;
    private Activity activity;
    private boolean isShown = false;
    private int w,h;
    public SoundPlayerUI(Activity activity) {
        this.activity = activity;
        initDimension(activity);
    }
    public void initDimension(Activity activity) {
        DisplayManager displayManager = (DisplayManager) activity.getSystemService(Context.DISPLAY_SERVICE);
        Display display = displayManager.getDisplay(0);
        if(display != null) {
            Point size = new Point();
            display.getRealSize(size);
            w = size.x;
            h = size.y;
        }
    }
    public void setDuration(long duration) {
        if(!isShown) {
            this.duration = duration;
        }
    }
    public void show() {
        if(!isShown) {
            SoundPlayerView soundPlayerView = new SoundPlayerView(activity,duration);
            activity.addContentView(soundPlayerView,new ViewGroup.LayoutParams(w,h/9));
            soundPlayerView.setX(0);
            soundPlayerView.setY(7*h/9-h/30);
            isShown = true;
        }
    }
}
