package com.example.boulets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.widget.Button;

public class CreationEquipe extends AppCompatActivity {
    private Button buttonAjoutRouge;
    private Button buttonAjoutBleu;
    private Button buttonAjoutJaune;
    private Button buttonAjoutVert;
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_equipe);
    }

    //Pour le swipe
    public boolean onTouchEvent(MotionEvent touchevent) {
        switch (touchevent.getAction()){
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if(x1 < x2){
                    Intent choixMode = new Intent(CreationEquipe.this, ChoixMode.class);
                    startActivity(choixMode);
                }
                break;
        }
        return false;
    }
}
