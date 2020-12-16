package com.example.quizapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.R;

public class QuizResultActivity extends AppCompatActivity {
    TextView mTvHighScore, mTvTotalQues, mTvCorrectQues, mTvWrongQues;
    Button mBtnStartQuiz, mBtnMenu;
    int highScore = 0;
    private long backPressedTime;
    private static final String SHARED_PREFERENCES_SCORE = "shared_preferences_score";
    private static final String SHARED_PREFERENCES_HIGH_SCORE = "shared_preferences_high_score";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quiz_result);

        initViews();
    }

    private void initViews() {
        mTvHighScore = findViewById(R.id.tvResultHighScore);
        mTvTotalQues = findViewById(R.id.tvResultTotalQues);
        mTvCorrectQues = findViewById(R.id.tvResultCorrect);
        mTvWrongQues = findViewById(R.id.tvResultWrong);
        mBtnStartQuiz = findViewById(R.id.btnStartAgain);
        mBtnMenu = findViewById(R.id.btnMainMenu);

        mBtnStartQuiz.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent startQuizIntent = new Intent(QuizResultActivity.this, QuizMainActivity.class);
                startActivity(startQuizIntent);
            }
        });

        mBtnMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menuIntent = new Intent(QuizResultActivity.this, QuizStartScreenActivity.class);
                startActivity(menuIntent);
            }
        });

        loadHighScore();

        Intent updateIntent = getIntent();
        int score = updateIntent.getIntExtra("QuizScore", 0);
        int totalQues = updateIntent.getIntExtra("TotalQuizQues", 0);
        int correctQues = updateIntent.getIntExtra("CorrectQues", 0);
        int wrongQues = updateIntent.getIntExtra("WrongQues", 0);

        mTvTotalQues.setText("Total Questions: " + String.valueOf(totalQues));
        mTvCorrectQues.setText("Correct Questions: " + String.valueOf(correctQues));
        mTvWrongQues.setText("Wrong Questions: " + String.valueOf(wrongQues));

        if (score > highScore) {
            updateScore(score);
        }
    }


    private void loadHighScore() {
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_SCORE, MODE_PRIVATE);
        highScore = sharedPreferences.getInt(SHARED_PREFERENCES_HIGH_SCORE, 0);

        mTvHighScore.setText("HighScore: " + String.valueOf(highScore));

    }

    private void updateScore(int score) {
        highScore = score;
        mTvHighScore.setText("HighScore: " + String.valueOf(highScore));
        SharedPreferences sharedPreferences = getSharedPreferences(SHARED_PREFERENCES_SCORE, MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(SHARED_PREFERENCES_HIGH_SCORE, highScore);
        editor.apply();

    }
}