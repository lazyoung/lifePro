package com.lazyoung.lifepro;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.TimePicker;
import java.util.Calendar;
import java.util.TimeZone;

public class DayActivity extends Activity
    {
        CharSequence taskA,taskB,taskC,taskD,taskE;
        Integer taskSelect = 0;
        Integer classSelect = 0;
        Integer scoreDinner = 0;
        Integer scoreClassD = 0;
        Integer scoreSleep = 0;
        Integer scoreClassA = 0;
        Integer scoreBreakfast = 0;
        Integer scoreClassB = 0;
        Integer scoreLunch = 0;
        Integer scoreClassC = 0;
        Integer scoreWork = 0;
        Integer scoreHappy = 0;
        Integer mHour, mMinute;
        Integer timeClassD = 0;
        Integer timeSleep = 0;
        Integer timeClassA = 0;
        Integer timeClassB = 0;
        Integer timeClassC = 0;

        public void updateTimePicker()
        {
            TimePicker timePicker = (TimePicker) findViewById(R.id.DayTimePicker);
            long mTime = System.currentTimeMillis();
            Calendar mCalendar = Calendar.getInstance();
            mCalendar.setTimeInMillis(mTime);
            TimeZone timeZone = TimeZone.getDefault();
            mCalendar.setTimeZone(timeZone);
            mHour = mCalendar.get(Calendar.HOUR);
            mMinute = mCalendar.get(Calendar.MINUTE);
            timePicker.setIs24HourView(true);
            timePicker.setHour(mHour);
            timePicker.setMinute(mMinute);
        }

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_day);

            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            int scoreInt = (int) (ratingBar.getRating() * 19.9998);
            String scoreStr = String.valueOf(scoreInt);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    int scoreInt = (int) (rating * 19.9998);
                    String scoreStr = String.valueOf(scoreInt);

                    TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
                    scoreView.setText(scoreStr);
                }
            });

            TimePicker timePicker = (TimePicker) findViewById(R.id.DayTimePicker);
            updateTimePicker();
            timePicker.setOnTimeChangedListener(new TimePicker.OnTimeChangedListener() {

                @Override
                public void onTimeChanged(TimePicker p1, int p2, int p3) {
                    mHour = p2;
                    mMinute = p3;
                }
            });
        }

        public void onMoreClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            float x = ratingBar.getRating();
            x = (float) (x + 0.051);
            ratingBar.setRating(x);
        }

        public void onLessClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            float y = ratingBar.getRating();
            y = (float) (y - 0.051);
            ratingBar.setRating(y);
        }

        public void onTaskAClick(View view)
        {
            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskView.setText(taskA);
            taskSelect = 1;
        }

        public void onTaskBClick(View view)
        {
            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskView.setText(taskB);
            taskSelect = 2;
        }

        public void onTaskCClick(View view)
        {
            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskView.setText(taskC);
            taskSelect = 3;
        }

        public void onTaskDClick(View view)
        {
            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskView.setText(taskD);
            taskSelect = 4;
        }

        public void onTaskEClick(View view)
        {
            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskView.setText(taskE);
            taskSelect = 5;
        }

        public void onSaveClick(View view)
        {
            CharSequence taskInput;
            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskInput = taskView.getText();

            switch(taskSelect)
            {
                case 1:
                    taskA = taskInput;
                    break;
                case 2:
                    taskB = taskInput;
                    break;
                case 3:
                    taskC = taskInput;
                    break;
                case 4:
                    taskD = taskInput;
                    break;
                case 5:
                    taskE = taskInput;
                    break;
                default:
                    break;
            }

            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            int scoreInt = (int) (ratingBar.getRating() * 19.9998);
            updateTimePicker();

            switch(classSelect)
            {
               case 1:
                    scoreDinner = scoreInt;
                    break;
               case 2:
                    scoreClassD = ((mHour*60 + mMinute - timeClassD)) % 100;
                    break;
               case 3:
                    scoreSleep = ((mHour*60 + mMinute - timeSleep)) % 100;
                    break;
               case 4:
                    scoreClassA = ((mHour*60 + mMinute - timeClassA)) % 100;
                    break;
               case 5:
                    scoreBreakfast = scoreInt;
                    break;
               case 6:
                    scoreClassB = ((mHour*60 + mMinute - timeClassB)) % 100;
                    break;
               case 7:
                    scoreLunch = scoreInt;
                    break;
               case 8:
                    scoreClassC = ((mHour*60 + mMinute - timeClassC)) % 100;
                    break;
               case 9:
                    scoreWork = scoreInt;
                    break;
               case 10:
                    scoreHappy = scoreInt;
                    break;
               default:
                    break;
            }

            //Reset select
            taskSelect = 0;
            classSelect = 0;
        }

        public void onResetClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            ratingBar.setRating(0);

            TextView taskView = (TextView) findViewById(R.id.DayEditTextTask);
            taskView.setText("");

            updateTimePicker();
        }

        public void onDinnerClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreDinner);

            if(scoreDinner > 0)
                ratingBar.setRating((float)scoreDinner / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            classSelect = 1;
        }

        public void onBreakfastClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreBreakfast);

            if(scoreBreakfast > 0)
                ratingBar.setRating((float)scoreBreakfast / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            classSelect = 5;
        }

        public void onLunchClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreLunch);

            if(scoreLunch > 0)
                ratingBar.setRating((float)scoreLunch / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            classSelect = 7;
        }

        public void onWorkClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreWork);

            if(scoreWork > 0)
                ratingBar.setRating((float)scoreWork / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            classSelect = 9;
        }

        public void onHappyClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreHappy);

            if(scoreHappy > 0)
                ratingBar.setRating((float)scoreHappy / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            classSelect = 10;
        }

        public void onSleepClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreSleep);

            if(scoreSleep > 0)
                ratingBar.setRating((float)scoreSleep / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            updateTimePicker();
            classSelect = 3;
        }

        public void onClassAClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreClassA);

            if(scoreClassA > 0)
                ratingBar.setRating((float)scoreClassA / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            updateTimePicker();
            classSelect = 4;
        }

        public void onClassBClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreClassB);

            if(scoreClassB > 0)
                ratingBar.setRating((float)scoreClassB / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            updateTimePicker();
            classSelect = 6;
        }

        public void onClassCClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreClassC);

            if(scoreClassC > 0)
                ratingBar.setRating((float)scoreClassC / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            updateTimePicker();
            classSelect = 8;
        }

        public void onClassDClick(View view)
        {
            RatingBar ratingBar = (RatingBar) findViewById(R.id.DayRatingBar);
            String scoreStr = String.valueOf(scoreClassD);

            if(scoreClassD > 0)
                ratingBar.setRating((float)scoreClassD / 20);

            TextView scoreView = (TextView) findViewById(R.id.DayTextViewScore);
            scoreView.setText(scoreStr);

            updateTimePicker();
            classSelect = 2;
        }

        public void onStartClick(View view)
        {
            switch(classSelect)
            {
                case 2:
                    timeClassD = mHour * 60 + mMinute;
                    break;
                case 3:
                    timeSleep = mHour * 60 + mMinute;
                    break;
                case 4:
                    timeClassA = mHour * 60 + mMinute;
                    break;
                case 6:
                    timeClassB = mHour * 60 + mMinute;
                    break;
                case 8:
                    timeClassC = mHour * 60 + mMinute;
                    break;
                default: break;
            }
        }

        @Override
        protected void onStart( )
        {
            // TODO: Implement this method
            super.onStart( );
        }

        @Override
        protected void onStop( )
        {
            // TODO: Implement this method
            super.onStop( );
        }

        @Override
        protected void onPause( )
        {
            // TODO: Implement this method
            super.onPause( );
        }

        @Override
        protected void onResume( )
        {
            // TODO: Implement this method
            super.onResume( );
        }

    }
