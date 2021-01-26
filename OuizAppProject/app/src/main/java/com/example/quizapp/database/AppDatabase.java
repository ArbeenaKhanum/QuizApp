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

            questionsDao.insert(new Questions("Plants receive their nutrients mainly from?",
                    "Chlorophyll", "Atmosphere", "Light",
                    "Soil", 4));

            questionsDao.insert(new Questions("Full form of URL is?",
                    "Uniform Resource Locator", "Uniform Resource Link", "Uniform Registered Link",
                    "Unified Resource Link", 1));

            questionsDao.insert(new Questions("How many Months have 28 days?",
                    "One", "Twelve", "Five",
                    "Seven", 2));

            questionsDao.insert(new Questions("On which thread services work in android?",
                    "Worker Thread", "Background Thread", "Main Thread",
                    "None of the above", 3));

            questionsDao.insert(new Questions("In Activity life-cycle, what is the first callback method invoked by the system?",
                    "onStart()", "onStop()", "onRestore()",
                    "onCreate()", 4));

            questionsDao.insert(new Questions("Which is the only even prime number?",
                    "2", "5", "6",
                    "12", 1));

            questionsDao.insert(new Questions("Oxygen released in the process of photosynthesis comes fom?",
                    "Carbon dioxide", "Sugar", "Pyruvic acid",
                    "Water", 4));

            questionsDao.insert(new Questions("Who is widely considered to be the 'Father of  Mathematics'?",
                    "Srinivasa Ramanujan", "Abacus", "Archimedes",
                    "Shakuntala Devi", 3));

            questionsDao.insert(new Questions("Potato is a modified form (outgrowth of)?",
                    "Stem", "Root", "Fruit",
                    "Leaf", 1));

            questionsDao.insert(new Questions("Which the national animal of India?",
                    "Lion", "Tiger", "Deer",
                    "Camel", 2));

            questionsDao.insert(new Questions("12/2(5-2)=?",
                    "6", "12", "2",
                    "18", 4));

            questionsDao.insert(new Questions("Which is the national sport of India?",
                    "Cricket", "Hockey", "Kabaddi",
                    "Football", 2));

            questionsDao.insert(new Questions("Who was the first indian women in space?",
                    "Kalpana Chawla", "Sunita Williams", "Koneru Humpy",
                    "None of the above", 1));

            questionsDao.insert(new Questions("What makes a specific set of the application data available to other application?",
                    "Broadcast Receiver", "Content Provider", "Intent",
                    "None of these", 2));

            questionsDao.insert(new Questions("Factors of 9 are ...",
                    "1, 3 and 9", "1, 2 and 3", "1, 6 and 9",
                    "1, 2, 3 and 9", 1));

            questionsDao.insert(new Questions("Which vitamin is necessary for blood clotting?",
                    "Vitamin A", "Vitamin C", "Vitamin K",
                    "Vitamin D", 3));

            questionsDao.insert(new Questions("View Pager is used for?",
                    "Swapping Activities", "Swapping Fragments", "Paging Down List Items",
                    "Scrolling views", 2));

            questionsDao.insert(new Questions("What is 50 times 5 is equal to?",
                    "2500", "505", "500",
                    "250", 4));

            questionsDao.insert(new Questions("Who was the first Prime Minister of India?",
                    "Indira Gandhi", "Rajiv Gandhi", "Jawaharlal Nehru",
                    "Dr. Radha Krishna", 3));

            questionsDao.insert(new Questions("Who discovered India?",
                    "Vasco da Gama", "Christopher Columbus", "James Crook",
                    "Willem Janszoon", 1));
            return null;
        }
    }
}
