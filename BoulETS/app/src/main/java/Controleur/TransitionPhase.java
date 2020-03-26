package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import Modele.ModeleJeu;

public class TransitionPhase extends AppCompatActivity {
    //Déclaration des variables
    private Button pret;
    private TextView joueurCommence;
    private TextView explications;
    private TextView Phase;

    private ModeleJeu jeu = ModeleJeu.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_phase);
        joueurCommence = (TextView) findViewById(R.id.next_joueur);
        Phase = (TextView) findViewById(R.id.phase2);
        explications = (TextView) findViewById(R.id.phase2_explication);


        //Transition vers les autres vues à l'aide des boutons
        pret = (Button) findViewById(R.id.button_suivant);
        pret.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFenetreJeu();
            }
        });

        jeu.incrementePhase();
        jeu.SetOrdreTour();
        jeu.NextJoueur();
        jeu.resetEtatTableMot();

        joueurCommence.setText(jeu.getJoueurActif().getNom());
        Phase.setText("Phase "+jeu.getNumPhase());
        explications.setText(jeu.getExplicationsPhases());
    }

    //Redirection vers la page de jeu
    private void openFenetreJeu() {
        Intent intentDemarrer = new Intent(this, FenetreJeu.class);
        startActivity(intentDemarrer);
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}

}
