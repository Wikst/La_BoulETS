package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.TimeUnit;

public class FenetreJeu extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;

    private long timeCountInMilliSeconds = 1 * 3000;
    private enum TimerStatus {
        STARTED,
        STOPPED
    }
    private TimerStatus timerStatus = TimerStatus.STOPPED;
    private ProgressBar progressBarCircle;
    private EditText editTextMinute;
    private TextView temps;
    private ImageView imageViewReset;
    private ImageView imageViewStartStop;
    private CountDownTimer countDownTimer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jeu);

        // method call to initialize the views
        initViews();
        // method call to initialize the listeners
                             //initListeners();
        startCountDownTimer();

        }

    //Pour le swipe
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (x1 < x2 && x2-x1 > 300) {
                    Log.d("SwipeBas", "Swipe vers la droite effectué");
                    //finish();
                }
                if (y1 < y2 && y2-y1 > 250) {
                    Log.d("SwipeBas", "Swipe vers le bas effectué");
                    //finish();
                }

                break;
        }
        return false;
    }

    //Pour la minuterie; cette méthode est inspirée du site https://stackoverflow.com/questions/20010997/circular-progress-bar-for-a-countdown-timer
    /**
     * method to initialize the views
     */
    private void initViews() {
        progressBarCircle = (ProgressBar) findViewById(R.id.chrono);
        //editTextMinute = (EditText) findViewById(R.id.editTextMinute);
        temps = findViewById(R.id.temps);
        //imageViewReset = (ImageView) findViewById(R.id.imageViewReset);
        //imageViewStartStop = (ImageView) findViewById(R.id.imageViewStartStop);
    }

    /**
     * method to initialize the click listeners

    private void initListeners() {
        imageViewReset.setOnClickListener(this);
        imageViewStartStop.setOnClickListener(this);
    } */

    /**
     * implemented method to listen clicks
     *
     * @param view

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.imageViewReset:
                reset();
                break;
            case R.id.imageViewStartStop:
                startStop();
                break;
        }
    }*/

    /**
     * method to reset count down timer
     */
    private void reset() {
        stopCountDownTimer();
        startCountDownTimer();
    }

    /**
     * method to start and stop count down timer
     */
    private void startStop() {
        if (timerStatus == TimerStatus.STOPPED) {

            // call to initialize the timer values
            setTimerValues();
            // call to initialize the progress bar values
            setProgressBarValues();
            // showing the reset icon
                       //imageViewReset.setVisibility(View.VISIBLE);
            // changing play icon to stop icon
                    //imageViewStartStop.setImageResource(R.drawable.icon_stop);
            // making edit text not editable
            editTextMinute.setEnabled(false);
            // changing the timer status to started
            timerStatus = TimerStatus.STARTED;
            // call to start the count down timer
            startCountDownTimer();

        } else {

            // hiding the reset icon
            imageViewReset.setVisibility(View.GONE);
            // changing stop icon to start icon
                        //imageViewStartStop.setImageResource(R.drawable.icon_start);
            // making edit text editable
            editTextMinute.setEnabled(true);
            // changing the timer status to stopped
            timerStatus = TimerStatus.STOPPED;
            stopCountDownTimer();
            
            openPointage();

        }
    }

    /**
     * method to initialize the values for count down timer
     */
    private void setTimerValues() {
        int time = 1;
        if (!temps.getText().toString().isEmpty()) {
            // fetching value from edit text and type cast to integer
            time = Integer.parseInt(temps.getText().toString().trim());
        } else {
            // toast message to fill edit text
            Toast.makeText(getApplicationContext(), getString(R.string.message_minutes), Toast.LENGTH_LONG).show();
        }
        // assigning values after converting to milliseconds
        //timeCountInMilliSeconds = time * 60 * 1000;
        timeCountInMilliSeconds = time * 1000;
    }

    /**
     * method to start count down timer
     */
    private void startCountDownTimer() {

        countDownTimer = new CountDownTimer(timeCountInMilliSeconds, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

                temps.setText(hmsTimeFormatter(millisUntilFinished));

                progressBarCircle.setProgress((int) (millisUntilFinished / 1000));

            }

            @Override
            public void onFinish() {

               temps.setText(hmsTimeFormatter(timeCountInMilliSeconds));
                // call to initialize the progress bar values
                setProgressBarValues();
                // hiding the reset icon
                          //imageViewReset.setVisibility(View.GONE);
                // changing stop icon to start icon
                         //imageViewStartStop.setImageResource(R.drawable.icon_start);
                // making edit text editable
                editTextMinute.setEnabled(true);
                // changing the timer status to stopped
                timerStatus = TimerStatus.STOPPED;

                openPointage();
            }

        }.start();
        countDownTimer.start();
    }

    //Redirection vers le score de la partie une fois le timer fini
    private void openPointage() {
        Intent intentPointage = new Intent(this, Pointage.class);
        startActivity(intentPointage);
    }

    /**
     * method to stop count down timer
     */
    private void stopCountDownTimer() {
        countDownTimer.cancel();
        openPointage();
    }

    /**
     * method to set circular progress bar values
     */
    private void setProgressBarValues() {

        progressBarCircle.setMax((int) timeCountInMilliSeconds / 1000);
        progressBarCircle.setProgress((int) timeCountInMilliSeconds / 1000);
    }


    /**
     * method to convert millisecond to time format
     *
     * @param milliSeconds
     * @return HH:mm:ss time formatted string
     */
    private String hmsTimeFormatter(long milliSeconds) {

        //String hms = String.format("%02d:%02d:%02d",
        String hms = String.format("%02d",
                //TimeUnit.MILLISECONDS.toHours(milliSeconds),
                TimeUnit.MILLISECONDS.toSeconds(milliSeconds) - TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(milliSeconds)));
                //TimeUnit.MILLISECONDS.toMillis(milliSeconds) - TimeUnit.SECONDS.toMillis(TimeUnit.MILLISECONDS.toSeconds(milliSeconds)));

        //Backup
        //TimeUnit.MILLISECONDS.toHours(milliSeconds),
        //TimeUnit.MILLISECONDS.toMinutes(milliSeconds) - TimeUnit.HOURS.toMinutes(TimeUnit.MILLISECONDS.toHours(milliSeconds)),

        return hms;




    }





}