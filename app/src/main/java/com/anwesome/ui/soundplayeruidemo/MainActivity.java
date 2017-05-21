package com.anwesome.ui.soundplayeruidemo;

import android.content.pm.ActivityInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.ViewGroup;

import com.anwesome.ui.soundplayerui.SoundPlayerUI;
import com.anwesome.ui.soundplayerui.SoundPlayerView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
        SoundPlayerUI soundPlayerUI = new SoundPlayerUI(this);
        soundPlayerUI.setDuration(50);
        soundPlayerUI.show();
    }
}
