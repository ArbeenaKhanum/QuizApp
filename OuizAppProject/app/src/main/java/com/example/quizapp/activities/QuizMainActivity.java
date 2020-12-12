package com.example.quizapp.activities;

import android.os.Bundle;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.lifecycle.ViewModelProviders;

import com.example.quizapp.R;
import com.example.quizapp.database.Questions;
import com.example.quizapp.viewmodel.QuestionsViewModel;

import java.util.List;

public class QuizMainActivity extends AppCompatActivity {

    private QuestionsViewModel questionsViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_quiz);

        questionsViewModel = ViewModelProviders.of(this).get(QuestionsViewModel.class);
        questionsViewModel.getAllQuestions().observe(this, new Observer<List<Questions>>() {
            @Override
            public void onChanged(List<Questions> questions) {
                Toast.makeText(QuizMainActivity.this, "View Data", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
