package com.example.quizapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.activities.QuizMainActivity;

public class CorrectScoreDialog {

    private Context mContext;
    private Dialog correctScoreDialog;
    private TextView tvCorrectScore;
    private QuizMainActivity quizMainActivity;

    public CorrectScoreDialog(Context context) {
        mContext = context;
    }

    public void correctScoreDialog(int totalScore, QuizMainActivity quizMainActivity) {
        this.quizMainActivity = quizMainActivity;
        correctScoreDialog = new Dialog(mContext);
        correctScoreDialog.setContentView(R.layout.correct_dialog);

        final Button btnFinalScore = correctScoreDialog.findViewById(R.id.ibScoreDialog);
        answerScore(totalScore);
        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                correctScoreDialog.dismiss();
                quizMainActivity.setQuestions();
            }
        });

        correctScoreDialog.show();
        correctScoreDialog.setCancelable(false);
        correctScoreDialog.setCanceledOnTouchOutside(false);
    }

    private void answerScore(int totalScore) {
        tvCorrectScore = correctScoreDialog.findViewById(R.id.tvScoreDialog);
        tvCorrectScore.setText("Score: " + String.valueOf(totalScore));
    }

}
