package com.example.quizapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.R;
import com.example.quizapp.activities.QuizMainActivity;

public class FinalScoreDialog {

    private Context mContext;
    private Dialog finalScoreDialog;
    private TextView tvFinalScore;


    public FinalScoreDialog(Context context) {
        mContext = context;
    }

    public void finalScoreDialog(int correctAnswer, int wrongAnswer, int totalSizeOfQuizQuestions) {
        finalScoreDialog = new Dialog(mContext);
        finalScoreDialog.setContentView(R.layout.final_score_dialog);

        final Button btnFinalScore = finalScoreDialog.findViewById(R.id.ibFinalDialog);
        finalScore(correctAnswer, wrongAnswer, totalSizeOfQuizQuestions);
        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finalScoreDialog.dismiss();
                Intent quizIntent = new Intent(mContext, QuizMainActivity.class);
                mContext.startActivity(quizIntent);
            }
        });

        finalScoreDialog.show();
        finalScoreDialog.setCancelable(false);
        finalScoreDialog.setCanceledOnTouchOutside(false);
    }

    private void finalScore(int correctAnswer, int wrongAnswer, int totalSizeOfQuizQuestions) {
        int tempScore = 0;
        tvFinalScore = finalScoreDialog.findViewById(R.id.tvFinalScoreDialog);
        if (correctAnswer == totalSizeOfQuizQuestions) {
            tempScore = (correctAnswer * 20) - (wrongAnswer * 5);
            tvFinalScore.setText("Final Score :" + String.valueOf(tempScore));
        } else if (wrongAnswer == totalSizeOfQuizQuestions){
            tempScore = 0;
            tvFinalScore.setText("Final Score :" + String.valueOf(tempScore));
        } else if (correctAnswer > wrongAnswer) {
            tempScore = (correctAnswer * 20) - (wrongAnswer * 5);
            tvFinalScore.setText("Final Score :" + String.valueOf(tempScore));
        } else if (wrongAnswer > correctAnswer) {
            tempScore = (wrongAnswer * 5) - (correctAnswer - 20) ;
            tvFinalScore.setText("Final Score :" + String.valueOf(tempScore));
        } else if (wrongAnswer == correctAnswer) {
            tempScore = (correctAnswer * 20) - (wrongAnswer * 5 );
            tvFinalScore.setText("Final Score :" + String.valueOf(tempScore));
        }
    }
}
