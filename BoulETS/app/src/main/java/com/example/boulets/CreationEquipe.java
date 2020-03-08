package com.example.boulets;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CreationEquipe extends AppCompatActivity {
    //Déclaration de variables
    private FloatingActionButton ajoutRouge;
    private FloatingActionButton ajoutBleu;
    private FloatingActionButton ajoutJaune;
    private FloatingActionButton ajoutVert;
    private ImageView jauneBanniere;
    private ImageView vertBanniere;
    private TextView jauneEquipe;
    private TextView vertEquipe;
    private TextView rougeJ3;
    private TextView rougeJ4;
    private TextView bleuJ3;
    private TextView bleuJ4;
    private TextView jauneJ1;
    private TextView jauneJ2;
    private TextView jauneJ3;
    private TextView jauneJ4;
    private TextView vertJ1;
    private TextView vertJ2;
    private TextView vertJ3;
    private TextView vertJ4;
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_equipe);

        //Gestion bouton & Joueurs Rouge
        ajoutRouge = (FloatingActionButton) findViewById(R.id.rouge_ajout);
        rougeJ3 = (TextView) findViewById(R.id.rouge_J3);
        rougeJ4 = (TextView) findViewById(R.id.rouge_J4);
        ajoutRouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addRouge(); }});

        //Gestion bouton & Joueurs Bleu
        ajoutBleu = (FloatingActionButton) findViewById(R.id.bleu_ajout);
        bleuJ3 = (TextView) findViewById(R.id.bleu_J3);
        bleuJ4 = (TextView) findViewById(R.id.bleu_J4);
        ajoutBleu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addBleu(); }});

        //Gestion bouton & Joueurs Jaune
        ajoutJaune = (FloatingActionButton) findViewById(R.id.jaune_ajout);
        jauneBanniere = (ImageView) findViewById(R.id.jaune_banner);
        jauneEquipe = (TextView) findViewById(R.id.jaune_Team);
        jauneJ1 = (TextView) findViewById(R.id.jaune_J1);
        jauneJ2 = (TextView) findViewById(R.id.jaune_J2);
        jauneJ3 = (TextView) findViewById(R.id.jaune_J3);
        jauneJ4 = (TextView) findViewById(R.id.jaune_J4);
        ajoutJaune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addJaune(); }});

        //Gestion bouton & Joueurs Vert
        ajoutVert = (FloatingActionButton) findViewById(R.id.vert_ajout);
        vertEquipe = (TextView) findViewById(R.id.vert_Team);
        vertJ3 = (TextView) findViewById(R.id.vert_J3);
        vertJ4 = (TextView) findViewById(R.id.vert_J4);
        ajoutVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addVert(); }});


    }

    //Logique pour ajouter des membres dans l'équipe rouge
    public void addRouge(){
        if(rougeJ4.getVisibility()==View.INVISIBLE && rougeJ3.getVisibility()==View.VISIBLE) {
            rougeJ4.setVisibility(View.VISIBLE);
        }
            if(rougeJ3.getVisibility()==View.INVISIBLE){
            rougeJ3.setVisibility(View.VISIBLE);
        }
    }

    //Logique pour ajouter des membres dans l'équipe bleu
    public void addBleu(){
        if(bleuJ4.getVisibility()==View.INVISIBLE && bleuJ3.getVisibility()==View.VISIBLE) {
            bleuJ4.setVisibility(View.VISIBLE);
        }
        if(bleuJ3.getVisibility()==View.INVISIBLE){
            bleuJ3.setVisibility(View.VISIBLE);
        }
    }

    //Logique pour ajouter des membres dans l'équipe jaune
    public void addJaune(){
        if(jauneJ4.getVisibility()==View.INVISIBLE && jauneJ3.getVisibility()==View.VISIBLE) {
            jauneJ4.setVisibility(View.VISIBLE);
        }
        if(jauneJ3.getVisibility()==View.INVISIBLE){
            jauneJ3.setVisibility(View.VISIBLE);
        }
        if(jauneJ2.getVisibility()==View.INVISIBLE){
            jauneBanniere.setVisibility(View.VISIBLE);
            jauneEquipe.setVisibility(View.VISIBLE);
            jauneJ1.setVisibility(View.VISIBLE);
            jauneJ2.setVisibility(View.VISIBLE);
        }
    }

    //Logique pour ajouter des membres dans l'équipe vert
    public void addVert(){
        if(vertJ4.getVisibility()==View.INVISIBLE && vertJ3.getVisibility()==View.VISIBLE) {
            vertJ4.setVisibility(View.VISIBLE);
        }
        if(vertJ3.getVisibility()==View.INVISIBLE){
            vertJ3.setVisibility(View.VISIBLE);
        }
        if(vertJ2.getVisibility()==View.INVISIBLE){
            vertBanniere.setVisibility(View.VISIBLE);
            vertEquipe.setVisibility(View.VISIBLE);
            vertJ1.setVisibility(View.VISIBLE);
            vertJ2.setVisibility(View.VISIBLE);
        }
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
