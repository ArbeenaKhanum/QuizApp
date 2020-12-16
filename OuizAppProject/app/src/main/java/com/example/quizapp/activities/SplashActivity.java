package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.quizapp.R;

public class SplashActivity extends AppCompatActivity {
    ImageView ivQuizLogo;
    TextView tvQuizName;
    Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        Runnable runnable = new Runnable() {
            @Override
            public void run() {
                Intent splashIntent = new Intent(SplashActivity.this, QuizStartScreenActivity.class);
                startActivity(splashIntent);
                finish();
            }
        };

        Handler mHandler = new Handler();
        mHandler.postDelayed(runnable, 3000);


        initViews();
    }

    private void initViews() {
        ivQuizLogo = findViewById(R.id.ivQuizLogo);
        tvQuizName = findViewById(R.id.QuizName);

        animationsOnSplash();
    }

    private void animationsOnSplash() {
        Animation animation = AnimationUtils.loadAnimation(this, R.anim.transitions_splash);
        ivQuizLogo.setAnimation(animation);
        tvQuizName.startAnimation(animation);
    }

    @Override
    protected void onStop() {
        super.onStop();
        finish();
    }
}