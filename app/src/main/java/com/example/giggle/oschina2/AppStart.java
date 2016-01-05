package com.example.giggle.oschina2;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;

import com.example.giggle.oschina2.ui.MainActivity;

public class AppStart extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        final View startView = View.inflate(this, R.layout.app_start, null);
        setContentView(startView);

        AlphaAnimation alphaAnimation = new AlphaAnimation(0.5f, 1.0f);
        alphaAnimation.setDuration(800);
        startView.startAnimation(alphaAnimation);
        alphaAnimation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {

            }

            @Override
            public void onAnimationEnd(Animation animation) {
                redirectTO();
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
    }

    // TODO: 2015-10-25
    @Override
    protected void onResume() {
        super.onResume();

//        TDevice.getVersionCode();
    }

    private void redirectTO() {
        // TODO: 2015-11-29 start log upload services
//        Intent logUpload = new Intent(this, LogUploadService.class);
//        startService(logUpload);

        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

        finish();
    }
}
