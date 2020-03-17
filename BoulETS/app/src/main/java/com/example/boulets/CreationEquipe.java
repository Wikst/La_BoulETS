package com.example.boulets;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

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
    private TextInputEditText rougeJ1;
    private TextInputEditText rougeJ2;
    private TextInputEditText rougeJ3;
    private TextInputEditText rougeJ4;
    private TextInputEditText bleuJ1;
    private TextInputEditText bleuJ2;
    private TextInputEditText bleuJ3;
    private TextInputEditText bleuJ4;
    private TextInputEditText jauneJ1;
    private TextInputEditText jauneJ2;
    private TextInputEditText jauneJ3;
    private TextInputEditText jauneJ4;
    private TextInputEditText vertJ1;
    private TextInputEditText vertJ2;
    private TextInputEditText vertJ3;
    private TextInputEditText vertJ4;
    private TextView msgErreur;
    private boolean okRouge = false;
    private boolean okBleu = false;
    private boolean okJaune = false;
    private boolean okVert = false;
    private boolean joueurUniqueRouge = false;
    private boolean joueurUniqueBleu = false;
    private boolean joueurUniqueJaune = false;
    private boolean joueurUniqueVert = false;
    public int compteurNbJoueurs;
    public int nbJoueursRouge = 0;
    public int nbJoueursBleu = 0;
    public int nbJoueursJaune = 0;
    public int nbJoueursVert = 0;
    public String[][] listeJoueurs = new String[4][4];
    float x1, x2, y1, y2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_equipe);
        msgErreur = (TextView) findViewById(R.id.msg_erreur);

        //Gestion bouton & Joueurs Rouge
        ajoutRouge = (FloatingActionButton) findViewById(R.id.rouge_ajout);
        rougeJ1 = (TextInputEditText) findViewById(R.id.rouge_J1);
        rougeJ2 = (TextInputEditText) findViewById(R.id.rouge_J2);
        rougeJ3 = (TextInputEditText) findViewById(R.id.rouge_J3);
        rougeJ4 = (TextInputEditText) findViewById(R.id.rouge_J4);
        ajoutRouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRouge();
            }
        });

        //Gestion bouton & Joueurs Bleu
        ajoutBleu = (FloatingActionButton) findViewById(R.id.bleu_ajout);
        bleuJ1 = (TextInputEditText) findViewById(R.id.bleu_J1);
        bleuJ2 = (TextInputEditText) findViewById(R.id.bleu_J2);
        bleuJ3 = (TextInputEditText) findViewById(R.id.bleu_J3);
        bleuJ4 = (TextInputEditText) findViewById(R.id.bleu_J4);
        ajoutBleu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBleu();
            }
        });

        //Gestion bouton & Joueurs Jaune
        ajoutJaune = (FloatingActionButton) findViewById(R.id.jaune_ajout);
        jauneBanniere = (ImageView) findViewById(R.id.jaune_banner);
        jauneEquipe = (TextView) findViewById(R.id.jaune_Team);
        jauneJ1 = (TextInputEditText) findViewById(R.id.jaune_J1);
        jauneJ2 = (TextInputEditText) findViewById(R.id.jaune_J2);
        jauneJ3 = (TextInputEditText) findViewById(R.id.jaune_J3);
        jauneJ4 = (TextInputEditText) findViewById(R.id.jaune_J4);
        ajoutJaune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addJaune(); }});

        //Gestion bouton & Joueurs Vert
        ajoutVert = (FloatingActionButton) findViewById(R.id.vert_ajout);
        vertBanniere = (ImageView) findViewById(R.id.vert_banner);
        vertEquipe = (TextView) findViewById(R.id.vert_Team);
        vertJ1 = (TextInputEditText) findViewById(R.id.vert_J1);
        vertJ2 = (TextInputEditText) findViewById(R.id.vert_J2);
        vertJ3 = (TextInputEditText) findViewById(R.id.vert_J3);
        vertJ4 = (TextInputEditText) findViewById(R.id.vert_J4);
        ajoutVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addVert(); }});

    }

        //Logique pour ajouter des membres dans l'équipe rouge
        public void addRouge () {
            if (rougeJ4.getVisibility() == View.INVISIBLE && rougeJ3.getVisibility() == View.VISIBLE) {
                rougeJ4.setVisibility(View.VISIBLE);
            }
            if (rougeJ3.getVisibility() == View.INVISIBLE) {
                rougeJ3.setVisibility(View.VISIBLE);
            }
        }

        //Logique pour ajouter des membres dans l'équipe bleu
        public void addBleu(){
            if (bleuJ4.getVisibility() == View.INVISIBLE && bleuJ3.getVisibility() == View.VISIBLE) {
                bleuJ4.setVisibility(View.VISIBLE);
            }
            if (bleuJ3.getVisibility() == View.INVISIBLE) {
                bleuJ3.setVisibility(View.VISIBLE);
            }
        }

        //Logique pour ajouter des membres dans l'équipe jaune
        public void addJaune () {
            if (jauneJ4.getVisibility() == View.INVISIBLE && jauneJ3.getVisibility() == View.VISIBLE) {
                jauneJ4.setVisibility(View.VISIBLE);
            } else if (jauneJ3.getVisibility() == View.INVISIBLE && jauneJ2.getVisibility() == View.VISIBLE) {
                jauneJ3.setVisibility(View.VISIBLE);
            } else {
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
        } else if(vertJ3.getVisibility()==View.INVISIBLE && vertJ2.getVisibility() == View.VISIBLE){
            vertJ3.setVisibility(View.VISIBLE);
        } else {
            vertBanniere.setVisibility(View.VISIBLE);
            vertEquipe.setVisibility(View.VISIBLE);
            vertJ1.setVisibility(View.VISIBLE);
            vertJ2.setVisibility(View.VISIBLE);
        }
    }

        //Pour le swipe
        public boolean onTouchEvent (MotionEvent touchevent){
            switch (touchevent.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    x1 = touchevent.getX();
                    y1 = touchevent.getY();
                    break;
                case MotionEvent.ACTION_UP:
                    x2 = touchevent.getX();
                    y2 = touchevent.getY();
                    if (x1 > x2 && x1 - x2 > 300) {
                        verificationJoueur();
                        compteurNbJoueurs = compteNbJoueurs();
                        verificationEquipe(String.valueOf(compteurNbJoueurs));
                    }
                    if (x1 < x2 && x2 - x1 > 300) {
                        finish();
                    }
                    break;
            }
            return false;
        }

        //Vérification qu'il y aille au moins 2 personnes dans une équipe
        public void verificationJoueur() {
            //Vérification équipe rouge
                //Vérification min 2 joueurs
            if ( !(rougeJ1.getText().toString().matches("") || rougeJ2.getText().toString().matches("")) ||
                    !(rougeJ1.getText().toString().matches("") || rougeJ3.getText().toString().matches("")) ||
                    !(rougeJ1.getText().toString().matches("") || rougeJ4.getText().toString().matches("")) ||
                    !(rougeJ2.getText().toString().matches("") || rougeJ3.getText().toString().matches("")) ||
                    !(rougeJ2.getText().toString().matches("") || rougeJ4.getText().toString().matches("")) ||
                    !(rougeJ3.getText().toString().matches("") || rougeJ4.getText().toString().matches(""))) {
                okRouge = true;
                Log.d("tag2", "2 joueurs min, OK");

                //Personne unique
            } else if ( ((rougeJ1.getText().toString().matches("") && !rougeJ2.getText().toString().matches("")) || (!rougeJ1.getText().toString().matches("") && rougeJ2.getText().toString().matches(""))) ||
                    ((rougeJ1.getText().toString().matches("") && !rougeJ3.getText().toString().matches("")) || (!rougeJ1.getText().toString().matches("") && rougeJ3.getText().toString().matches(""))) ||
                    ((rougeJ1.getText().toString().matches("") && !rougeJ4.getText().toString().matches("")) || (!rougeJ1.getText().toString().matches("") && rougeJ4.getText().toString().matches(""))) ||
                    ((rougeJ2.getText().toString().matches("") && !rougeJ3.getText().toString().matches("")) || (!rougeJ2.getText().toString().matches("") && rougeJ3.getText().toString().matches(""))) ||
                    ((rougeJ2.getText().toString().matches("") && !rougeJ4.getText().toString().matches("")) || (!rougeJ2.getText().toString().matches("") && rougeJ4.getText().toString().matches(""))) ||
                    ((rougeJ3.getText().toString().matches("") && !rougeJ4.getText().toString().matches("")) || (!rougeJ3.getText().toString().matches("") && rougeJ4.getText().toString().matches("")))) {
                joueurUniqueRouge = true;
                Log.d("tag1", "Joeur Unique détecté!!");
            }
                //Aucun Joueur
            else{ okRouge = false;
            joueurUniqueRouge = false;
                Log.d("tag3", "Aucun joueur");}


            //Vérification équipe bleu
                //Vérification min 2 joueurs
            if ( !(bleuJ1.getText().toString().matches("") || bleuJ2.getText().toString().matches("")) ||
                    !(bleuJ1.getText().toString().matches("") || bleuJ3.getText().toString().matches("")) ||
                    !(bleuJ1.getText().toString().matches("") || bleuJ4.getText().toString().matches("")) ||
                    !(bleuJ2.getText().toString().matches("") || bleuJ3.getText().toString().matches("")) ||
                    !(bleuJ2.getText().toString().matches("") || bleuJ4.getText().toString().matches("")) ||
                    !(bleuJ3.getText().toString().matches("") || bleuJ4.getText().toString().matches(""))) {
                okBleu = true;
                Log.d("tag2", "2 joueurs min, OK");

                //Personne unique
            } else if ( ((bleuJ1.getText().toString().matches("") && !bleuJ2.getText().toString().matches("")) || (!bleuJ1.getText().toString().matches("") && bleuJ2.getText().toString().matches(""))) ||
                    ((bleuJ1.getText().toString().matches("") && !bleuJ3.getText().toString().matches("")) || (!bleuJ1.getText().toString().matches("") && bleuJ3.getText().toString().matches(""))) ||
                    ((bleuJ1.getText().toString().matches("") && !bleuJ4.getText().toString().matches("")) || (!bleuJ1.getText().toString().matches("") && bleuJ4.getText().toString().matches(""))) ||
                    ((bleuJ2.getText().toString().matches("") && !bleuJ3.getText().toString().matches("")) || (!bleuJ2.getText().toString().matches("") && bleuJ3.getText().toString().matches(""))) ||
                    ((bleuJ2.getText().toString().matches("") && !bleuJ4.getText().toString().matches("")) || (!bleuJ2.getText().toString().matches("") && bleuJ4.getText().toString().matches(""))) ||
                    ((bleuJ3.getText().toString().matches("") && !bleuJ4.getText().toString().matches("")) || (!bleuJ3.getText().toString().matches("") && bleuJ4.getText().toString().matches("")))) {
                joueurUniqueBleu = true;
                Log.d("tag1", "Joeur Unique détecté!!");
            }
            //Aucun joueur
            else{ okBleu = false;
                joueurUniqueBleu = false;
                Log.d("tag3", "Aucun joueur");
            }


            //Vérification équipe jaune
                //Vérification min 2 joueurs
            if ( !(jauneJ1.getText().toString().matches("") || jauneJ2.getText().toString().matches("")) ||
                    !(jauneJ1.getText().toString().matches("") || jauneJ3.getText().toString().matches("")) ||
                    !(jauneJ1.getText().toString().matches("") || jauneJ4.getText().toString().matches("")) ||
                    !(jauneJ2.getText().toString().matches("") || jauneJ3.getText().toString().matches("")) ||
                    !(jauneJ2.getText().toString().matches("") || jauneJ4.getText().toString().matches("")) ||
                    !(jauneJ3.getText().toString().matches("") || jauneJ4.getText().toString().matches(""))) {
                okJaune = true;
                Log.d("tag2", "2 joueurs min, OK");

                //Personne unique
            } else if ( ((jauneJ1.getText().toString().matches("") && !jauneJ2.getText().toString().matches("")) || (!jauneJ1.getText().toString().matches("") && jauneJ2.getText().toString().matches(""))) ||
                    ((jauneJ1.getText().toString().matches("") && !jauneJ3.getText().toString().matches("")) || (!jauneJ1.getText().toString().matches("") && jauneJ3.getText().toString().matches(""))) ||
                    ((jauneJ1.getText().toString().matches("") && !jauneJ4.getText().toString().matches("")) || (!jauneJ1.getText().toString().matches("") && jauneJ4.getText().toString().matches(""))) ||
                    ((jauneJ2.getText().toString().matches("") && !jauneJ3.getText().toString().matches("")) || (!jauneJ2.getText().toString().matches("") && jauneJ3.getText().toString().matches(""))) ||
                    ((jauneJ2.getText().toString().matches("") && !jauneJ4.getText().toString().matches("")) || (!jauneJ2.getText().toString().matches("") && jauneJ4.getText().toString().matches(""))) ||
                    ((jauneJ3.getText().toString().matches("") && !jauneJ4.getText().toString().matches("")) || (!jauneJ3.getText().toString().matches("") && jauneJ4.getText().toString().matches("")))) {
                joueurUniqueJaune = true;
                Log.d("tag1", "Joeur Unique détecté!!");
            }
            //Aucun Joueur
            else{ okJaune = false;
                joueurUniqueJaune = false;
                Log.d("tag3", "Aucun joueur");}



            //Vérification équipe Vert
                //Vérification min 2 joueurs
            if ( !(vertJ1.getText().toString().matches("") || vertJ2.getText().toString().matches("")) ||
                    !(vertJ1.getText().toString().matches("") || vertJ3.getText().toString().matches("")) ||
                    !(vertJ1.getText().toString().matches("") || vertJ4.getText().toString().matches("")) ||
                    !(vertJ2.getText().toString().matches("") || vertJ3.getText().toString().matches("")) ||
                    !(vertJ2.getText().toString().matches("") || vertJ4.getText().toString().matches("")) ||
                    !(vertJ3.getText().toString().matches("") || vertJ4.getText().toString().matches(""))) {
                okVert = true;
                Log.d("tag2", "2 joueurs min, OK");

                //Personne unique
            } else if ( ((vertJ1.getText().toString().matches("") && !vertJ2.getText().toString().matches("")) || (!vertJ1.getText().toString().matches("") && vertJ2.getText().toString().matches(""))) ||
                    ((vertJ1.getText().toString().matches("") && !vertJ3.getText().toString().matches("")) || (!vertJ1.getText().toString().matches("") && vertJ3.getText().toString().matches(""))) ||
                    ((vertJ1.getText().toString().matches("") && !vertJ4.getText().toString().matches("")) || (!vertJ1.getText().toString().matches("") && vertJ4.getText().toString().matches(""))) ||
                    ((vertJ2.getText().toString().matches("") && !vertJ3.getText().toString().matches("")) || (!vertJ2.getText().toString().matches("") && vertJ3.getText().toString().matches(""))) ||
                    ((vertJ2.getText().toString().matches("") && !vertJ4.getText().toString().matches("")) || (!vertJ2.getText().toString().matches("") && vertJ4.getText().toString().matches(""))) ||
                    ((vertJ3.getText().toString().matches("") && !vertJ4.getText().toString().matches("")) || (!vertJ3.getText().toString().matches("") && vertJ4.getText().toString().matches("")))) {
                joueurUniqueVert = true;
                Log.d("tag1", "Joeur Unique détecté!!");
            }
            //Aucun Joueur
            else{ okVert = false;
                joueurUniqueVert = false;
                Log.d("tag3", "Aucun joueur");}

        }


        //Vérification qu'il y aille au moins 2 personnes dans une équipe
        public void verificationEquipe(String compteurNbJoueurs) {
            if( ((okRouge && okBleu)||(okRouge && okJaune)||(okRouge && okVert)||(okBleu&&okJaune)||(okBleu&&okVert)||(okJaune&&okVert)) && !(joueurUniqueRouge||joueurUniqueBleu||joueurUniqueJaune||joueurUniqueVert)){
                Intent next = new Intent(CreationEquipe.this, ChoixMode.class);
                next.putExtra("NB_JOUEURS", compteurNbJoueurs);
                next.putExtra("LISTE_JOUEURS", listeJoueurs);
                Bundle bundleListeJoueurs = new Bundle();
                bundleListeJoueurs.putSerializable("LISTE_JOUEURS", listeJoueurs);
                next.putExtras(bundleListeJoueurs);
                startActivity(next);
            } else {
                this.msgErreur.setVisibility(View.VISIBLE);
            }
        }

        //Compte le nombre de joueurs présent dans la partie
        public int compteNbJoueurs(){
        int compteurNbJoueurs = 0;
        //int nbJoueursRouge = 0;
        //int nbJoueursBleu = 0;
        //int nbJoueursJaune = 0;
        //int nbJoueursVert = 0;

        if(!rougeJ1.getText().toString().matches("")){
            listeJoueurs[0][nbJoueursRouge] = rougeJ1.getText().toString();
            Log.d("Rouge Joueur 1", "Rouge J1 s'appelle: " + listeJoueurs[0][nbJoueursRouge]);
            compteurNbJoueurs++;
            nbJoueursRouge++;
        }
        if(!rougeJ2.getText().toString().matches("")) {
            listeJoueurs[0][nbJoueursRouge] = rougeJ2.getText().toString();
            Log.d("Rouge Joueur 2", "Rouge J2 s'appelle: " + listeJoueurs[0][nbJoueursRouge]);
            compteurNbJoueurs++;
            nbJoueursRouge++;
        }
        if(!rougeJ3.getText().toString().matches("")) {
            listeJoueurs[0][nbJoueursRouge] = rougeJ3.getText().toString();
            Log.d("Rouge Joueur 3", "Rouge J3 s'appelle: " + listeJoueurs[0][nbJoueursRouge]);
            compteurNbJoueurs++;
            nbJoueursRouge++;
        }
        if(!rougeJ4.getText().toString().matches("")) {
            listeJoueurs[0][nbJoueursRouge] = rougeJ4.getText().toString();
            Log.d("Rouge Joueur 4", "Rouge J4 s'appelle: " + listeJoueurs[0][nbJoueursRouge]);
            compteurNbJoueurs++;
            nbJoueursRouge++;
        }
        if(!bleuJ1.getText().toString().matches("")) {
            this.listeJoueurs[1][nbJoueursBleu] = bleuJ1.getText().toString();
            Log.d("Bleu Joueur 1", "Bleu J1 s'appelle: " + listeJoueurs[1][nbJoueursBleu]);
            compteurNbJoueurs++;
            nbJoueursBleu++;
        }
        if(!bleuJ2.getText().toString().matches("")) {
            this.listeJoueurs[1][nbJoueursBleu] = bleuJ2.getText().toString();
            Log.d("Bleu Joueur 2", "Bleu J2 s'appelle: " + listeJoueurs[1][nbJoueursBleu]);
            compteurNbJoueurs++;
            nbJoueursBleu++;
        }
        if(!bleuJ3.getText().toString().matches("")) {
            listeJoueurs[1][nbJoueursBleu] = bleuJ3.getText().toString();
            Log.d("Bleu Joueur 3", "Bleu J3 s'appelle: " + listeJoueurs[1][nbJoueursBleu]);
            compteurNbJoueurs++;
            nbJoueursBleu++;
        }
        if(!bleuJ4.getText().toString().matches("")) {
            listeJoueurs[1][nbJoueursBleu] = bleuJ4.getText().toString();
            Log.d("Bleu Joueur 4", "Bleu J4 s'appelle: " + listeJoueurs[1][nbJoueursBleu]);
            compteurNbJoueurs++;
            nbJoueursBleu++;
        }
        if(!jauneJ1.getText().toString().matches("")) {
            listeJoueurs[2][nbJoueursJaune] = jauneJ1.getText().toString();
            Log.d("Jaune Joueur 1", "Jaune J1 s'appelle: " + listeJoueurs[2][nbJoueursJaune]);
            compteurNbJoueurs++;
            nbJoueursJaune++;
        }
        if(!jauneJ2.getText().toString().matches("")) {
            listeJoueurs[2][nbJoueursJaune] = jauneJ2.getText().toString();
            Log.d("Jaune Joueur 2", "Jaune J2 s'appelle: " + listeJoueurs[2][nbJoueursJaune]);
            compteurNbJoueurs++;
            nbJoueursJaune++;
        }
        if(!jauneJ3.getText().toString().matches("")) {
            listeJoueurs[2][nbJoueursJaune] = jauneJ3.getText().toString();
            Log.d("Jaune Joueur 3", "Jaune J3 s'appelle: " + listeJoueurs[2][nbJoueursJaune]);
            compteurNbJoueurs++;
            nbJoueursJaune++;
        }
        if(!jauneJ4.getText().toString().matches("")) {
            listeJoueurs[2][nbJoueursJaune] = jauneJ4.getText().toString();
            Log.d("Jaune Joueur 4", "Jaune J4 s'appelle: " + listeJoueurs[2][nbJoueursJaune]);
            compteurNbJoueurs++;
            nbJoueursJaune++;
        }
        if(!vertJ1.getText().toString().matches("")) {
            listeJoueurs[3][nbJoueursVert] = vertJ1.getText().toString();
            Log.d("Vert Joueur 1", "Vert J1 s'appelle: " + listeJoueurs[3][nbJoueursVert]);
            compteurNbJoueurs++;
            nbJoueursVert++;
        }
        if(!vertJ2.getText().toString().matches("")) {
            listeJoueurs[3][nbJoueursVert] = vertJ2.getText().toString();
            Log.d("Vert Joueur 2", "Vert J2 s'appelle: " + listeJoueurs[3][nbJoueursVert]);
            compteurNbJoueurs++;
            nbJoueursVert++;
        }
        if(!vertJ3.getText().toString().matches("")) {
            listeJoueurs[3][nbJoueursVert] = vertJ3.getText().toString();
            Log.d("Vert Joueur 3", "Vert J3 s'appelle: " + listeJoueurs[3][nbJoueursVert]);
            compteurNbJoueurs++;
            nbJoueursVert++;
        }
        if(!vertJ4.getText().toString().matches("")) {
            listeJoueurs[3][nbJoueursVert] = vertJ4.getText().toString();
            Log.d("Vert Joueur 4", "Vert J4 s'appelle: " + listeJoueurs[3][nbJoueursVert]);
            compteurNbJoueurs++;
            nbJoueursVert++;
        }

        Log.d("Nombre de joueurs", "Il y a présentement: " + compteurNbJoueurs + " joueurs dans la partie!");
            Log.d("TAG_NB_R", "Dont " + nbJoueursRouge + " dans l'équipe Rouge");
            Log.d("TAG_NB_B", "Dont " + nbJoueursBleu + " dans l'équipe Bleu");
            Log.d("TAG_NB_J", "Dont " + nbJoueursJaune + " dans l'équipe Jaune");
            Log.d("TAG_NB_V", "Dont " + nbJoueursVert + " dans l'équipe Vert");
            return compteurNbJoueurs;
        }


    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
        @Override
        public void onBackPressed () {
        }
    }