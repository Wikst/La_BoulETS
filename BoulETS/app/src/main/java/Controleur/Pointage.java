package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import Modele.ModeleJeu;

public class Pointage extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private Button cestParti;
    private TextView joueurSuivant;
    private TextView nbPhase;
    private TextView nbMotsRestants;
    private int compteur;
    private ModeleJeu jeu = ModeleJeu.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pointage);

        //Transition vers les autres vues à l'aide des boutons
        cestParti = (Button) findViewById(R.id.bouton_cestParti);
        cestParti.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openTransitionPhase();
            }
        });

        nbPhase = (TextView) findViewById(R.id.titre);
        //nbPhase.setText("Phase " + jeu.getPhaseActif() );

        nbMotsRestants = (TextView) findViewById(R.id.nbMotsRestants);
        //jeu.

        joueurSuivant = (TextView) findViewById(R.id.nom_next);
        jeu.NextJoueur();
        joueurSuivant.setText(jeu.getJoueurActif().getNom());
    }

    //Redirection vers la page de jeu
    private void openTransitionPhase() {
        //if (compteur == 0) {
            Intent intentTransitionPhase = new Intent(this, TransitionPhase.class);
            startActivity(intentTransitionPhase);
        //}
        //else {
          //  Intent intentJeu = new Intent(this, FenetreJeu.class);
            //startActivity(intentJeu);
            //Ou utiliser la méthode finish(). Cela demanderait de reset l'instance précédente par contre. À voir
            //}
        }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}
    }