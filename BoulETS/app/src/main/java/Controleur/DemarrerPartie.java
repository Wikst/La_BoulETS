package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import java.util.Random;

public class DemarrerPartie extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private Button commencerPartie;
    private TextView joueurCommence;

    private String[][] listeJoueurs = new String[4][4];
    private String compteurNbJoueurs;
    private String nbMots;
    private String[][] banqueMots;
    //private String compteurMots;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_demarrer_partie);

        joueurCommence = (TextView) findViewById(R.id.joueur_commence);

        //Récupère la liste des joueurs, nombre de joueurs et le nombre de mots par joueur dans
        //la fenêtre précédente (ChoixMode ou PartiePerso)
        Bundle extras = getIntent().getExtras();
        compteurNbJoueurs = extras.getString("NB_JOUEURS");
        nbMots = getIntent().getStringExtra("NB_MOTS_PAR_JOUEUR");
        listeJoueurs = (String[][]) extras.getSerializable("LISTE_JOUEURS");
        banqueMots = new String[Integer.valueOf(compteurNbJoueurs)][Integer.valueOf(nbMots)];

        choisirTourJoueur(listeJoueurs, compteurNbJoueurs);

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
        intentDemarrer.putExtra("NB_MOTS_PAR_JOUEUR", compteurMots);
        intentDemarrer.putExtra("NB_JOUEURS", compteurNbJoueurs);

        Bundle bundleListeJoueurs = new Bundle();
        bundleListeJoueurs.putSerializable("LISTE_JOUEURS", listeJoueurs);
        intentDemarrer.putExtras(bundleListeJoueurs);

        Bundle bundleListeMots = new Bundle();
        bundleListeMots.putSerializable("LISTE_MOTS", banqueMots);
        intentDemarrer.putExtras(bundleListeMots);
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

    //Méthode qui choisit un joueur parmi les joueurs et affiche son nom
    private void choisirTourJoueur(String[][] listeJoueurs, String compteurNbJoueursString){
        int nbJoueurs = Integer.valueOf(compteurNbJoueursString);
        int equipe = getRandomNumberInRange(0,3);
        int joueur = getRandomNumberInRange(0,3);

        while(listeJoueurs[equipe][joueur] == null){
            equipe = getRandomNumberInRange(0,3);
            joueur = getRandomNumberInRange(0,3);
        }
        joueurCommence.setText(listeJoueurs[equipe][joueur]);
    }

    //Méthode pour obtenir une chiffre aléatoire entre une valeur min et max
    //Pris du site: https://mkyong.com/java/java-generate-random-integers-in-a-range/
    private static int getRandomNumberInRange(int min, int max){
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }

        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}
}