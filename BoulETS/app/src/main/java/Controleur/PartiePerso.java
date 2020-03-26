package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import java.util.ArrayList;

import Modele.ModeleJeu;

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

    private ModeleJeu jeu = ModeleJeu.getInstance();
    int nbMotsParJoueurs;
    private int nbJoueurs;
    private int compteur;

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
        compteur = 0;

        //Recuperation nb de mot par joueur
        nbMotsParJoueurs = jeu.getNbMotParJoueur();
        nbJoueurs = nbJoueurs = jeu.getNbJoueurs();

        //Adaptation fenêtre
        adapteNbMots();

        //Recuperation du nom du premier Joueur
        joueurSuivant();
        joueurName.setText(jeu.getJoueurActif().getNom());

        //Transition vers les autres vues à l'aide des boutons
        valider = (Button) findViewById(R.id.bouton_valider);
        valider.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                motsProchainJoueur();
            }
        });

    }


    //Enregistre les mots dans la banque de mots(associée aux joueurs), efface les données dans l'écran et affiche le prochain nom
    private void motsProchainJoueur(){
        ArrayList<String> listeMot = getListeMot();
        if (jeu.listeMotIsValid(listeMot)){
            jeu.addMotPartiePerso(listeMot);
            if (compteur < nbJoueurs){
                jeu.getJoueurActif().setaJouer(true);
                joueurSuivant();
                resetFennetre();
            }
            else {
                openFenetreJeu();
            }
        }
        else {
            erreur.setVisibility(View.VISIBLE);
        }

    }

    //Redirection vers la page de jeu
    private void openFenetreJeu(){
            Intent intentDemarrer = new Intent(this, DemarrerPartie.class);
            startActivity(intentDemarrer);
    }

    private ArrayList<String> getListeMot(){
        ArrayList<String> listeMot = new ArrayList<String>();
        listeMot.add(mot1.getText().toString());
        listeMot.add(mot2.getText().toString());
        switch(nbMotsParJoueurs){
            case 5:
                listeMot.add(mot5.getText().toString());
            case 4:
                listeMot.add(mot4.getText().toString());
            case 3:
                listeMot.add(mot3.getText().toString());
                break;
        }
        return listeMot;
    }

    private void joueurSuivant(){
        jeu.nextPlayerInsert();
        compteur++;
    }

    private void resetFennetre(){
        mot1.setText("");
        mot2.setText("");
        mot3.setText("");
        mot4.setText("");
        mot5.setText("");
        joueurName.setText(jeu.getJoueurActif().getNom());
    }

    private void adapteNbMots(){
        //Switch fonctionne en sequence, si aucun break il continue jusqu'a celui-ci en partant du bon case
        switch(nbMotsParJoueurs){
            case 5:
                mot5.setVisibility(View.VISIBLE);
            case 4:
                mot4.setVisibility(View.VISIBLE);
            case 3:
                mot3.setVisibility(View.VISIBLE);
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