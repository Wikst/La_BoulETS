package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

public class PartiePerso extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private Button valider;
    private EditText mot1;
    private EditText mot2;
    private EditText mot3;
    private EditText mot4;
    private EditText mot5;
    //Ajouter un compteur (la fenètre se répètent par le nombre de joueurs sélectionner au début)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_perso);

        mot1 = (EditText) findViewById(R.id.mot1);
        mot2 = (EditText) findViewById(R.id.mot2);
        mot3 = (EditText) findViewById(R.id.mot3);
        mot4 = (EditText) findViewById(R.id.mot4);
        mot5 = (EditText) findViewById(R.id.mot5);

        int compteurNbJoueurs = getIntent().getIntExtra("nbJoueurs");

        //Transition vers les autres vues à l'aide des boutons
        valider = (Button) findViewById(R.id.bouton_valider);
        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFenetreJeu();
            }
        });

        //Récupère le nombre de mots choisi dans la fenêtre ChoixMode
        String nbMots = getIntent().getStringExtra("nbMotsChoixMode");
        adapteNbMots(nbMots);
        }

    //Redirection vers la page de jeu
    private void openFenetreJeu() {
        Intent intentDemarrer = new Intent(this, DemarrerPartie.class);
        startActivity(intentDemarrer);
    }

    private void adapteNbMots(String nbMots){
        switch(nbMots){
            case "1":
                mot1.setVisibility(View.VISIBLE);
                break;
            case "2":
                mot1.setVisibility(View.VISIBLE);
                mot2.setVisibility(View.VISIBLE);
                break;
            case "3":
                mot1.setVisibility(View.VISIBLE);
                mot2.setVisibility(View.VISIBLE);
                mot3.setVisibility(View.VISIBLE);
                break;
            case"4":
                mot1.setVisibility(View.VISIBLE);
                mot2.setVisibility(View.VISIBLE);
                mot3.setVisibility(View.VISIBLE);
                mot4.setVisibility(View.VISIBLE);
                break;
            case"5":
                mot1.setVisibility(View.VISIBLE);
                mot2.setVisibility(View.VISIBLE);
                mot3.setVisibility(View.VISIBLE);
                mot4.setVisibility(View.VISIBLE);
                mot5.setVisibility(View.VISIBLE);
                break;
        }
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
                    //INTÉRESSANT D'AJOUTER UNE CONFIRMATION (pour éviter swipe accidentel)+ Retour à la dernière personne, pas à la fenêtre d'avant
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