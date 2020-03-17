package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

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
    private TextView joueurName;
    private TextView erreur;

    private String compteurNbJoueurs;
    private String compteurMots;
    private String[] listeJoueurs = new String[16];
    private String nbMots;
    private String[][] banqueMots;

    int compteur = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mode_perso);

        mot1 = (EditText) findViewById(R.id.mot1);
        mot2 = (EditText) findViewById(R.id.mot2);
        mot3 = (EditText) findViewById(R.id.mot3);
        mot4 = (EditText) findViewById(R.id.mot4);
        mot5 = (EditText) findViewById(R.id.mot5);
        joueurName = (TextView) findViewById(R.id.joueur_name);
        erreur = (TextView) findViewById(R.id.msg_erreur_MotsPasRempli);
        
        //Récupère la liste des joueurs, nombre de joueurs et le nombre de mots par joueur dans la fenêtre précédente (ChoixMode)
        Bundle extras = getIntent().getExtras();
        compteurNbJoueurs = extras.getString("NB_JOUEURS");
        listeJoueurs = extras.getStringArray("LISTE_JOUEURS");
        nbMots = getIntent().getStringExtra("NB_MOTS_PAR_JOUEUR");

        //Adaptation fenêtre et création banque de mots
        adapteNbMots(nbMots);
        joueurName.setText(listeJoueurs[compteur]);
        banqueMots = new String[Integer.valueOf(compteurNbJoueurs)][Integer.valueOf(nbMots)];

        //Transition vers les autres vues à l'aide des boutons
        valider = (Button) findViewById(R.id.bouton_valider);
        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                motsProchainJoueur(compteurNbJoueurs, nbMots);
            }
        }); }

    //Enregistre les mots dans la banque de mots(associée aux joueurs), efface les données dans l'écran et affiche le prochain nom
    private void motsProchainJoueur(String compteurNbJoueursString, String nbMotsString){
        int nbJoueurs = Integer.valueOf(compteurNbJoueursString);
        int nbMots = Integer.valueOf(nbMotsString);

        //Si dernier joueur, passe à la prochaine fenêtre (DemarrerPartie), sinon continue
        if (compteur==nbJoueurs-1){
            openFenetreJeu();
        }

        //Selon Nb mots choisi, stock en mémoire les mots pour chaque individu (suivi par la variable "compteur") ET efface les mots écris entrée par l'utilisateur.
        switch(nbMots) {
            case 1:
                banqueMots[compteur][0] = mot1.getText().toString();
                Log.d("TAG_REC1", "Mot1: " + mot1.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                mot1.setText(null);
                break;
            case 2:
                banqueMots[compteur][0] = mot1.getText().toString();
                Log.d("TAG_REC1", "Mot1: " + mot1.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][1] = mot2.getText().toString();
                Log.d("TAG_REC2", "Mot2: " + mot2.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                mot1.setText(null);
                mot2.setText(null);
                break;
            case 3:
                banqueMots[compteur][0] = mot1.getText().toString();
                Log.d("TAG_REC1", "Mot1: " + mot1.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][1] = mot2.getText().toString();
                Log.d("TAG_REC2", "Mot2: " + mot2.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][2] = mot3.getText().toString();
                Log.d("TAG_REC3", "Mot3: " + mot3.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                mot1.setText(null);
                mot2.setText(null);
                mot3.setText(null);
                break;
            case 4:
                banqueMots[compteur][0] = mot1.getText().toString();
                Log.d("TAG_REC1", "Mot1: " + mot1.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][1] = mot2.getText().toString();
                Log.d("TAG_REC2", "Mot2: " + mot2.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][2] = mot3.getText().toString();
                Log.d("TAG_REC3", "Mot3: " + mot3.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][3] = mot4.getText().toString();
                Log.d("TAG_REC4", "Mot4: " + mot4.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                mot1.setText(null);
                mot2.setText(null);
                mot3.setText(null);
                mot4.setText(null);
                break;
            case 5:
                banqueMots[compteur][0] = mot1.getText().toString();
                Log.d("TAG_REC1", "Mot1: " + mot1.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][1] = mot2.getText().toString();
                Log.d("TAG_REC2", "Mot2: " + mot2.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][2] = mot3.getText().toString();
                Log.d("TAG_REC3", "Mot3: " + mot3.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][3] = mot4.getText().toString();
                Log.d("TAG_REC4", "Mot4: " + mot4.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                banqueMots[compteur][4] = mot5.getText().toString();
                Log.d("TAG_REC5", "Mot5: " + mot5.getText().toString() + " de " + listeJoueurs[compteur] + " a été enregistré");
                mot1.setText(null);
                mot2.setText(null);
                mot3.setText(null);
                mot4.setText(null);
                mot5.setText(null);
                break;
        }

        //Passe au prochain joueur pour écrire ses mots
        compteur++;
        Log.d("TAG_Compteur", "Le compteur est présentement à: " + compteur);
        joueurName.setText(listeJoueurs[compteur]);
    }

    //Redirection vers la page de jeu
    private void openFenetreJeu() {
        if(verificationMotsRemplis()) {
            Intent intentDemarrer = new Intent(this, DemarrerPartie.class);
            intentDemarrer.putExtra("NB_MOTS_PAR_JOUEUR", compteurMots);
            intentDemarrer.putExtra("NB_JOUEURS", compteurNbJoueurs);
            intentDemarrer.putExtra("LISTE_JOUEURS",listeJoueurs);
            startActivity(intentDemarrer);
        }
    }

    //Vérifie qu'aucune case mot n'est laissé vide
    private boolean verificationMotsRemplis() {
        switch (nbMots) {
            case "1":
                if (mot1.getText().toString().matches("")) {
                    erreur.setVisibility(View.VISIBLE);
                    return false;
                }
                break;

            case "2":
                if (mot1.getText().toString().matches("") || mot2.getText().toString().matches("")) {
                    erreur.setVisibility(View.VISIBLE);
                    return false;
                }
                break;

            case "3":
                if (mot1.getText().toString().matches("") || mot2.getText().toString().matches("") ||
                        mot3.getText().toString().matches("")) {
                    erreur.setVisibility(View.VISIBLE);
                    return false;
                }
                break;

            case "4":
                if (mot1.getText().toString().matches("") || mot2.getText().toString().matches("") ||
                        mot3.getText().toString().matches("") || mot4.getText().toString().matches("")) {
                    erreur.setVisibility(View.VISIBLE);
                    return false;
                }
                break;

            case "5":
                if (mot1.getText().toString().matches("") || mot2.getText().toString().matches("") ||
                        mot3.getText().toString().matches("") || mot4.getText().toString().matches("") ||
                        mot5.getText().toString().matches("")) {
                    erreur.setVisibility(View.VISIBLE);
                    return false;
                }
                break;
        }
        return true;
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