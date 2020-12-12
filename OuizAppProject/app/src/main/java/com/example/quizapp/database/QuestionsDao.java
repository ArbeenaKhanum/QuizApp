package com.example.quizapp.database;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import java.util.List;
@Dao
public interface QuestionsDao {
    @Query("SELECT * FROM questionsTable")
    List<Questions> getAllQuestions();
    @Insert
    void insert(Questions questions);
}
