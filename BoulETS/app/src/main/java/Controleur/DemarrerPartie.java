package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import Modele.ModeleJeu;

public class DemarrerPartie extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private Button commencerPartie;
    private TextView joueurCommence;
    private ModeleJeu jeu = ModeleJeu.getInstance();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demarrer_partie);

        joueurCommence = (TextView) findViewById(R.id.joueur_commence);

        //choisirTourJoueur(listeJoueurs, compteurNbJoueurs);
        jeu.setNumPhase(1);
        jeu.SetOrdreTour();
        jeu.NextJoueur();
        joueurCommence.setText(jeu.getJoueurActif().getNom());


        //Transition vers les autres vues à l'aide des boutons
        commencerPartie = (Button) findViewById(R.id.boutton_commencer);
        commencerPartie.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFenetreJeu();
            }
        });
    }

    //Redirection vers la page de jeu
    private void openFenetreJeu() {
        Intent intentDemarrer = new Intent(this, FenetreJeu.class);
        startActivity(intentDemarrer);
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
                if (x1 < x2) {
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