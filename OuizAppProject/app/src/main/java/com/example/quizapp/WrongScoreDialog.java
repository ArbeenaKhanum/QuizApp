package com.example.quizapp;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.quizapp.activities.QuizMainActivity;

public class WrongScoreDialog {

    private Context mContext;
    private Dialog wrongScoreDialog;
    TextView tvWrongScore;


    public WrongScoreDialog(Context context) {
        mContext = context;
    }

    public void wrongScoreDialog(String correctAnswer) {
        wrongScoreDialog = new Dialog(mContext);
        wrongScoreDialog.setContentView(R.layout.wrong_dialog);
        tvWrongScore = wrongScoreDialog.findViewById(R.id.tvCorrectAnswerDialog);
        tvWrongScore.setText("Correct Answer" + correctAnswer);

        final Button btnFinalScore = wrongScoreDialog.findViewById(R.id.ibWrongDialog);
        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                wrongScoreDialog.dismiss();
            }
        });

        wrongScoreDialog.show();
        wrongScoreDialog.setCancelable(false);
        wrongScoreDialog.setCanceledOnTouchOutside(false);
    }
}
