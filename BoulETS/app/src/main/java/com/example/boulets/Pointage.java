package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class Pointage extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private Button commencerPartie;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage);
    }
}
