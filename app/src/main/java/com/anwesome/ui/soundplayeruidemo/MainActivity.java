package com.anwesome.ui.soundplayeruidemo;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.soundplayerui.SoundPlayerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        SoundPlayerView soundPlayerView = new SoundPlayerView(this,40);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        soundPlayerView.setX(0);
        soundPlayerView.setY(1400);
        addContentView(soundPlayerView,new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,200));
    }
}
