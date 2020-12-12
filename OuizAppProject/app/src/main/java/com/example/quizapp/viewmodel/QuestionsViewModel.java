package com.example.quizapp.viewmodel;

import android.app.Application;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.quizapp.database.Questions;
import com.example.quizapp.repository.QuestionsRepository;

import java.util.List;

public class QuestionsViewModel extends AndroidViewModel {
    private QuestionsRepository questionsRepository;
    private LiveData<List<Questions>> allQuestions;

    public QuestionsViewModel(Application application) {
        super(application);
        questionsRepository = new QuestionsRepository(application);
        allQuestions = questionsRepository.getAllQuestions();
    }

    public LiveData<List<Questions>> getAllQuestions() {
        return allQuestions;
    }
}
