package com.example.quizapp.database;


import android.content.Context;
import android.os.AsyncTask;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

@Database(entities = {Questions.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    public abstract QuestionsDao questionsDao();

    private static AppDatabase INSTANCE;

    public static synchronized AppDatabase getInstance(final Context context) {
        if (INSTANCE == null) {
            INSTANCE = Room.databaseBuilder(context.getApplicationContext(),
                    AppDatabase.class, "questionsDB").fallbackToDestructiveMigration()
                    .addCallback(callback)
                    .build();
        }
        return INSTANCE;
    }

    private static RoomDatabase.Callback callback = new RoomDatabase.Callback() {
        @Override
        public void onCreate(@NonNull SupportSQLiteDatabase db) {
            super.onCreate(db);
            new PopulateDbAsyncTask(INSTANCE).execute();
        }
    };

    private static class PopulateDbAsyncTask extends AsyncTask<Void, Void, Void> {
        private QuestionsDao questionsDao;

        private PopulateDbAsyncTask(AppDatabase database) {
            questionsDao = database.questionsDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            questionsDao.insert(new Questions("What are the languages used to code in Android studio?",
                    "Html and Css", "Php and Ruby", "Java and Kotlin",
                    "Python and JavaScript", 3));

            questionsDao.insert(new Questions("How many Months have 28 days?",
                    "one", "Twelve", "Five",
                    "Seven", 2));

            questionsDao.insert(new Questions("12/2(5-2)=?",
                    "6", "12", "2",
                    "18", 4));

            questionsDao.insert(new Questions("Who was the first indian women in space?",
                    "Kalpana Chawla", "Sunita Williams", "Koneru Humpy",
                    "None of the above", 1));

            questionsDao.insert(new Questions("What makes a specific set of the application data available to other application?",
                    "Broadcast Receiver", "Content Provider", "Intent",
                    "None of these", 2));
            return null;
        }
    }
}
