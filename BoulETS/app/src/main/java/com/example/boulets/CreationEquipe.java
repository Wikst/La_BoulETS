package com.example.boulets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CreationEquipe extends AppCompatActivity {
    //Déclaration de variables
    private Button ajoutRouge;
    private Button ajoutBleu;
    private Button ajoutJaune;
    private Button ajoutVert;
    private TextView rougeJ3;
    private TextView rougeJ4;
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_equipe);

/**        ajoutRouge = (Button) findViewById(R.id.rouge_ajout);
        rougeJ3 = (TextView) findViewById(R.id.rouge_J3);
        rougeJ4 = (TextView) findViewById(R.id.rouge_J4);
        ajoutRouge.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                addRouge();
            }
        });
    }

    public void addRouge(){
        if(rougeJ4.getVisibility()==View.INVISIBLE && rougeJ3.getVisibility()==View.VISIBLE) {
            rougeJ4.setVisibility(View.VISIBLE);
        }
            if(rougeJ3.getVisibility()==View.INVISIBLE){
            rougeJ3.setVisibility(View.VISIBLE);
        }
**/    }

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
                if(x1 > x2 && x2-x1 > 300){
                    Intent next = new Intent(CreationEquipe.this, ChoixMode.class);
                    startActivity(next);
                }
                if(x1 < x2 && x2-x1 > 300){
                    finish();
            }
            break;
        }
        return false;
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}
}
