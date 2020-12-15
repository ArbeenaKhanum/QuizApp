package com.example.quizapp.activities;

import android.content.Intent;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.Handler;
import android.view.View;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.audioclass.AudioForAnswers;
import com.example.quizapp.dialogs.CorrectScoreDialog;
import com.example.quizapp.dialogs.FinalScoreDialog;
import com.example.quizapp.R;
import com.example.quizapp.dialogs.WrongScoreDialog;
import com.example.quizapp.database.Questions;
import com.example.quizapp.viewmodel.QuestionsViewModel;

import java.util.Collections;
import java.util.List;
import java.util.Locale;

public class QuizMainActivity extends AppCompatActivity {

    QuestionsViewModel questionsViewModel;
    TextView mTvQuestions, mTvQuestionsScore, mTvQuestionsCount, mTvQuestionsTimer, mTvQuestionsCorrect,
            mTvQuestionsWrong, mTvSubmit;
    RadioButton optionOne, optionTwo, optionThree, optionFour;
    RadioGroup rbGroup;
    boolean answered = false;
    List<Questions> questionsList;
    Questions currentQues;
    int wrongAnswer = 0;
    int correctAnswer = 0;
    private int score = 0;
    private int questionCounter = 0, questionsTotalCount;
    private ColorStateList tvColorButton;
    private Handler handler = new Handler();
    private FinalScoreDialog finalScoreDialog;
    private WrongScoreDialog wrongScoreDialog;
    private CorrectScoreDialog correctScoreDialog;
    private int totalSizeOfQuizQuestions;
    private int FLAG = 0;
    private AudioForAnswers audioForAnswers;
    private static final long COUNTDOWNTIME = 30000;
    private CountDownTimer countDownTimer;
    private long timeRemaining;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);
        initViews();
        tvColorButton = optionOne.getTextColors();
        finalScoreDialog = new FinalScoreDialog(this);
        wrongScoreDialog = new WrongScoreDialog(this);
        correctScoreDialog = new CorrectScoreDialog(this);
        audioForAnswers = new AudioForAnswers(this);

        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        questionsViewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                fetchDatabaseDetails(questions);
            }
        });

    }

    private void fetchDatabaseDetails(List<Questions> questions) {
        questionsList = questions;

        /**Start Quiz method*/
        startQuiz();

    }

    private void startQuiz() {
        setQuestions();

        rbGroup.setOnCheckedChangeListener(new RadioGroup.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(RadioGroup group, int checkedId) {
                switch (checkedId) {
                    case R.id.rbOptionOne:
                        optionOne.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_answers_options));
                        optionTwo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionThree.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionFour.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        break;

                    case R.id.rbOptionTwo:
                        optionTwo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_answers_options));
                        optionOne.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionThree.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionFour.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        break;

                    case R.id.rbOptionThree:
                        optionThree.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_answers_options));
                        optionTwo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionOne.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionFour.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        break;

                    case R.id.rbOptionFour:
                        optionFour.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.selected_answers_options));
                        optionTwo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionThree.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        optionOne.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
                        break;
                }

            }
        });

        mTvSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!answered) {
                    if (optionOne.isChecked() || optionTwo.isChecked() || optionThree.isChecked() || optionFour.isChecked()) {
                        quizOperations();
                    } else {
                        Toast.makeText(QuizMainActivity.this, "Please Select Answer", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void quizOperations() {
        answered = true;
        countDownTimer.cancel();

        RadioButton radioBtnSelected = findViewById(rbGroup.getCheckedRadioButtonId());
        int answerSelected = rbGroup.indexOfChild(radioBtnSelected) + 1;

        checkForSolution(answerSelected, radioBtnSelected);
    }

    private void checkForSolution(int answerSelected, RadioButton radioBtnSelected) {
        switch (currentQues.getAnswer()) {
            case 1:
                if (currentQues.getAnswer() == answerSelected) {
                    optionOne.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answer_correct_selected));
                    optionOne.setTextColor(Color.WHITE);
                    correctAnswer++;
                    mTvQuestionsCorrect.setText("Correct: " + String.valueOf(correctAnswer));
                    score += 10;
                    mTvQuestionsScore.setText("Score: " + String.valueOf(score));

                    correctScoreDialog.correctScoreDialog(score);
                    FLAG = 1;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);


                } else {
                    changeToIncorrectColor(radioBtnSelected);
                    wrongAnswer++;
                    mTvQuestionsWrong.setText("Wrong: " + String.valueOf(wrongAnswer));
                    final String correctAnswer = (String) optionOne.getText();
                    wrongScoreDialog.wrongScoreDialog(correctAnswer);
                    FLAG = 2;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);
                }
                break;

            case 2:
                if (currentQues.getAnswer() == answerSelected) {
                    optionTwo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answer_correct_selected));
                    optionTwo.setTextColor(Color.WHITE);
                    correctAnswer++;
                    mTvQuestionsCorrect.setText("Correct: " + String.valueOf(correctAnswer));
                    score += 10;
                    mTvQuestionsScore.setText("Score: " + String.valueOf(score));

                    correctScoreDialog.correctScoreDialog(score);
                    FLAG = 1;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);

                } else {
                    changeToIncorrectColor(radioBtnSelected);
                    wrongAnswer++;
                    mTvQuestionsWrong.setText("Wrong: " + String.valueOf(wrongAnswer));
                    final String correctAnswer = (String) optionTwo.getText();
                    wrongScoreDialog.wrongScoreDialog(correctAnswer);
                    FLAG = 2;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);
                }
                break;
            case 3:
                if (currentQues.getAnswer() == answerSelected) {
                    optionThree.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answer_correct_selected));
                    optionThree.setTextColor(Color.WHITE);
                    correctAnswer++;
                    mTvQuestionsCorrect.setText("Correct: " + String.valueOf(correctAnswer));
                    score += 10;
                    mTvQuestionsScore.setText("Score: " + String.valueOf(score));

                    correctScoreDialog.correctScoreDialog(score);
                    FLAG = 1;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);

                } else {
                    changeToIncorrectColor(radioBtnSelected);
                    wrongAnswer++;
                    mTvQuestionsWrong.setText("Wrong: " + String.valueOf(wrongAnswer));
                    final String correctAnswer = (String) optionThree.getText();
                    wrongScoreDialog.wrongScoreDialog(correctAnswer);
                    FLAG = 2;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);
                }
                break;
            case 4:
                if (currentQues.getAnswer() == answerSelected) {
                    optionFour.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answer_correct_selected));
                    optionFour.setTextColor(Color.WHITE);
                    correctAnswer++;
                    mTvQuestionsCorrect.setText("Correct: " + String.valueOf(correctAnswer));
                    score += 10;
                    mTvQuestionsScore.setText("Score: " + String.valueOf(score));

                    correctScoreDialog.correctScoreDialog(score);
                    FLAG = 1;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);

                } else {
                    changeToIncorrectColor(radioBtnSelected);
                    wrongAnswer++;
                    mTvQuestionsWrong.setText("Wrong: " + String.valueOf(wrongAnswer));
                    final String correctAnswer = (String) optionFour.getText();
                    wrongScoreDialog.wrongScoreDialog(correctAnswer);
                    FLAG = 2;
                    audioForAnswers.setAudio(FLAG);

                    handler.postDelayed(new Runnable() {
                        @Override
                        public void run() {
                            setQuestions();
                        }
                    }, 500);
                }
                break;
        }

        if (questionCounter == questionsTotalCount) {
            mTvSubmit.setText("Confirm and Submit");
        }
    }

    private void changeToIncorrectColor(RadioButton radioBtnSelected) {
        radioBtnSelected.setTextColor(Color.WHITE);
        radioBtnSelected.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.answer_wrong_selected));
    }

    public void setQuestions() {
        rbGroup.clearCheck();

        optionOne.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
        optionTwo.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
        optionThree.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));
        optionFour.setBackground(ContextCompat.getDrawable(getApplicationContext(), R.drawable.default_answers_options));

        optionOne.setTextColor(getResources().getColor(R.color.default_text_color));
        optionTwo.setTextColor(getResources().getColor(R.color.default_text_color));
        optionThree.setTextColor(getResources().getColor(R.color.default_text_color));
        optionFour.setTextColor(getResources().getColor(R.color.default_text_color));

        questionsTotalCount = questionsList.size();
        Collections.shuffle(questionsList);
        if (questionCounter < questionsTotalCount - 1) {
            currentQues = questionsList.get(questionCounter);

            mTvQuestions.setText(currentQues.getQuestions());
            optionOne.setText(currentQues.getOptionA());
            optionTwo.setText(currentQues.getOptionB());
            optionThree.setText(currentQues.getOptionC());
            optionFour.setText(currentQues.getOptionD());
            questionCounter++;

            answered = false;
            mTvSubmit.setText("Submit Answer");
            mTvQuestionsCount.setText("Questions:" + questionCounter + "/" + (questionsTotalCount - 1));
            timeRemaining = COUNTDOWNTIME;
            startCountDownTimer();

        } else {
            Toast.makeText(this, "Quiz Over", Toast.LENGTH_SHORT).show();
            totalSizeOfQuizQuestions = questionsList.size();

            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    resultDataOfQuiz();
                }
            }, 2000);
        }
    }

    /**
     * Countdown timer method
     */

    private void startCountDownTimer() {
        countDownTimer = new CountDownTimer(timeRemaining, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeRemaining = millisUntilFinished;
                updateCountDownTimer();
            }

            @Override
            public void onFinish() {
                timeRemaining = 0;
                updateCountDownTimer();
            }
        }.start();
    }

    private void updateCountDownTimer() {
        int minutes = (int) ((timeRemaining / 1000) / 60);
        int seconds = (int) ((timeRemaining / 1000) % 60);

        String timeFormat = String.format(Locale.getDefault(), "%02d:%02d", minutes, seconds);
        mTvQuestionsTimer.setText(timeFormat);

        if (timeRemaining < 1000) {
            mTvQuestionsTimer.setTextColor(Color.RED);
            FLAG = 3;
            audioForAnswers.setAudio(FLAG);

        } else {
            mTvQuestionsTimer.setTextColor(getResources().getColor(R.color.default_text_color));
        }

        if (timeRemaining == 0) {
            Toast.makeText(this, "Times Up!!", Toast.LENGTH_SHORT).show();
            handler.postDelayed(new Runnable() {
                @Override
                public void run() {
                    Intent intent = new Intent(getApplicationContext(), QuizMainActivity.class);
                    startActivity(intent);
                }
            }, 2000);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        if (countDownTimer != null) {
            countDownTimer.cancel();
        }
    }

    private void resultDataOfQuiz() {
        Intent resultIntent = new Intent(QuizMainActivity.this, QuizResultActivity.class);
        resultIntent.putExtra("QuizScore", score);
        resultIntent.putExtra("TotalQuizQues", (questionsTotalCount - 1));
        resultIntent.putExtra("CorrectQues", correctAnswer);
        resultIntent.putExtra("WrongQues", wrongAnswer);
        startActivity(resultIntent);

    }

    private void initViews() {
        mTvQuestions = findViewById(R.id.tvQuestions);
        mTvQuestionsCount = findViewById(R.id.tvTotalQuestions);
        mTvQuestionsScore = findViewById(R.id.tvScores);
        mTvQuestionsTimer = findViewById(R.id.tvTimer);
        mTvQuestionsCorrect = findViewById(R.id.tvCorrectAns);
        mTvQuestionsWrong = findViewById(R.id.tvWrongAns);
        mTvSubmit = findViewById(R.id.btnSubmit);
        rbGroup = findViewById(R.id.radioGroup);
        optionOne = findViewById(R.id.rbOptionOne);
        optionTwo = findViewById(R.id.rbOptionTwo);
        optionThree = findViewById(R.id.rbOptionThree);
        optionFour = findViewById(R.id.rbOptionFour);
    }
}
