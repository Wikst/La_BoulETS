package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import java.util.ArrayList;

import Modele.Equipe;
import Modele.ModeleJeu;

public class Pointage extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private Button cestParti;
    private TextView joueurSuivant;
    private TextView nbPhase;
    private TextView nbMotsRestants;
    private ImageView banniereRouge;
    private ImageView banniereBleu;
    private ImageView banniereJaune;
    private ImageView banniereVert;
    private TextView scoreRouge;
    private TextView scoreBleu;
    private TextView scoreJaune;
    private TextView scoreVert;
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

        banniereRouge = (ImageView) findViewById(R.id.rouge_banner);
        banniereBleu = (ImageView) findViewById(R.id.bleu_banner);
        banniereJaune = (ImageView) findViewById(R.id.jaune_banner);
        banniereVert = (ImageView) findViewById(R.id.vert_banner);
        scoreRouge = (TextView) findViewById(R.id.rouge_score);
        scoreBleu = (TextView) findViewById(R.id.bleu_score);
        scoreJaune = (TextView) findViewById(R.id.jaune_score);
        scoreVert = (TextView) findViewById(R.id.vert_score);

        afficheBanniere(jeu.getEquipeList());
        System.out.println("Size de EquipeList: " +  jeu.getEquipeList().size());

        nbMotsRestants = (TextView) findViewById(R.id.nbMotsRestants);
        nbMotsRestants.setText(Integer.toString(jeu.calculMotsRestants()));

        joueurSuivant = (TextView) findViewById(R.id.nom_next);
        jeu.NextJoueur();
        joueurSuivant.setText(jeu.getJoueurActif().getNom());
    }

    private void afficheBanniere(ArrayList<Equipe> equipeList) {
        switch(equipeList.size()){
                case 4:
                    banniereVert.setVisibility(View.VISIBLE);
                    scoreVert.setVisibility(View.VISIBLE);
                case 3:
                    banniereJaune.setVisibility(View.VISIBLE);
                    scoreJaune.setVisibility(View.VISIBLE);
                case 2:
                    break;
            }
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