# Quiz App
###### Quiz application provides multiple choice questions with option to select the correct answer.

## Technologies
* Java
* Room database

## Features

* The Quiz application can generate random questions and generate choices to be answered.
* The user can select the answer among the generated choices.
* When correct answer is selected, the user earns 10 points.
* When wrong answer is selected, the score remains same and correct answer is displayed.
* After the quiz is complete, The score board with the total score, the number of correct and wrong answers is displayed.

## Screenshots with description

After opening the application, you will find the welcome screen that stays in for 3 seconds (Quizziian galore is the name of the quiz app). Followed by that quiz start screen is displayed. It contains a button which is used to start the quiz.


|**Welcome screen**|**Quiz start screen**|
|:---|:--|
|<img src=Images/quiz_splash.jpeg height="500px"/>|<img src=Images/quiz_start.jpeg height="500px"/>|

<br/><br/>

When the user clicks on the "Start Quiz" button, Quiz main screen is displayed which contains the major feature of the app. Quiz questions are randomly generated from room database along with 4 options and user can select the answer of their choice. The quiz main screen also contains score value, count of questions and quiz timer. 

|**Quiz main screen**|**Quiz question**|**Quiz question**|
|:---|:--|:--|
|<img src=Images/quiz_screen.jpeg height="500px"/>|<img src=Images/quiz_screen_one.jpeg height="500px"/>|<img src=Images/quiz_screen_Two.jpeg height="500px"/>|

<br/><br/>

When the user selects the correct answer, the correct dialog appears along with a sound and the user gains 10 ponits added to the score. When the user select wrong answer, the wrong dialog appears with a sound and the score remains same, the user neither gains nor loose points. And the correct answer is also displayed with the wrong dialog box.

|**Correct dialog**|**Wrong dialog**|
|:---|:--|
|<img src=Images/quiz_correct.jpeg height="500px"/>|<img src=Images/quiz_wrong.jpeg height="500px"/>|

<br/><br/>

After the Quiz is complete, Quiz Score board appears. The quiz score board displays the score details that includes High Score, Total Questions, Correct Questions and Wrong Questions. It also contains two buttons "Start Quiz Again" - To restart the quiz again and "Main Menu" button - it directs to the quiz start screen.

When the user press on the back button, Exit dialog appears with "yes" or "no" option. The user can choose their desired option. On click of yes, the app is closed and on click of no, the app stays in the same place.

|**Quiz score screen**|**Exit dialog**|
|:---|:--|
|<img src=Images/quiz_score.jpeg height="500px"/>|<img src=Images/quiz_exit.jpeg height="500px"/>|
