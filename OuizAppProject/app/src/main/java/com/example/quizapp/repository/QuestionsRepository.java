package com.example.quizapp.repository;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.quizapp.database.AppDatabase;
import com.example.quizapp.database.Questions;
import com.example.quizapp.database.QuestionsDao;

import java.util.List;

public class QuestionsRepository {
    private QuestionsDao mQuestionDao;
    private LiveData<List<Questions>> mAllQuestions;

    public QuestionsRepository(Application application) {
        AppDatabase database = AppDatabase.getInstance(application);
        mQuestionDao = database.questionsDao();
        mAllQuestions = mQuestionDao.getAllQuestions();
    }

     public LiveData<List<Questions>> getAllQuestions() {
        return mAllQuestions;
    }
}
