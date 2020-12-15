package com.example.quizapp.audioclass;

import android.content.Context;
import android.media.MediaPlayer;

import com.example.quizapp.R;

public class AudioForAnswers {
    private Context mContext;
    private MediaPlayer mediaPlayer;

    public AudioForAnswers(Context mContext) {
        this.mContext = mContext;
    }

    public void setAudio(final int flag) {
        switch (flag) {
            case 1:
                int correctAnswerAudio = R.raw.quiz_correct_answer;
                playAudio(correctAnswerAudio);
                break;
            case 2:
                int wrongAnswerAudio = R.raw.quiz_wrong_answer;
                playAudio(wrongAnswerAudio);
                break;
        }
    }

    private void playAudio(int audioSoundFiles) {
        mediaPlayer = MediaPlayer.create(mContext, audioSoundFiles);
        mediaPlayer.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                mediaPlayer.start();
            }
        });

        mediaPlayer.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaPlayer.release();
            }
        });
    }
}
