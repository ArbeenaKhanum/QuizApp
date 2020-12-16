package com.example.quizapp.dialogs;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.View;
import android.widget.Button;

import com.example.quizapp.R;
import com.example.quizapp.activities.QuizStartScreenActivity;

public class TimerDialog {

    private Context mContext;
    private Dialog timerDialog;


    public TimerDialog(Context context) {
        mContext = context;
    }

    public void timerDialog() {
        timerDialog = new Dialog(mContext);
        timerDialog.setContentView(R.layout.timer_dialog);

        final Button btnFinalScore = timerDialog.findViewById(R.id.ibTimerDialog);
        btnFinalScore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                timerDialog.dismiss();
                Intent quizIntent = new Intent(mContext, QuizStartScreenActivity.class);
                mContext.startActivity(quizIntent);
            }
        });

        timerDialog.show();
        timerDialog.setCancelable(false);
        timerDialog.setCanceledOnTouchOutside(false);
    }
}
