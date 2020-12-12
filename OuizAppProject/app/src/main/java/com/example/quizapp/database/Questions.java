package com.example.quizapp.database;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;
@Entity (tableName = "questionsTable")
public class Questions {
    @PrimaryKey (autoGenerate = true)
    @ColumnInfo (name = "id")
    private int id;
    @ColumnInfo (name = "questions")
    private String questions;
    @ColumnInfo (name = "optionA")
    private String optionA;
    @ColumnInfo (name = "optionB")
    private String optionB;
    @ColumnInfo (name = "optionC")
    private String optionC;
    @ColumnInfo (name = "optionD")
    private String optionD;
    @ColumnInfo (name = "answer")
    private int answer;
    public Questions(String questions, String optionA, String optionB, String optionC, String optionD, int answer) {
        this.questions = questions;
        this.optionA = optionA;
        this.optionB = optionB;
        this.optionC = optionC;
        this.optionD = optionD;
        this.answer = answer;
    }
    public void setId(int id) {
        this.id = id;
    }
    public int getId() {
        return id;
    }
    public String getQuestions() {
        return questions;
    }
    public String getOptionA() {
        return optionA;
    }
    public String getOptionB() {
        return optionB;
    }
    public String getOptionC() {
        return optionC;
    }
    public String getOptionD() {
        return optionD;
    }
    public int getAnswer() {
        return answer;
    }
}

