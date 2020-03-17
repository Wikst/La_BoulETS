package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class ChoixMode extends AppCompatActivity {
    //Déclaration des variables
    float x1, x2, y1, y2;
    private TextView nbMots;
    private SeekBar seekBar;
    private Button partieRapide;
    private Button partiePerso;
    private String compteurMots = "2";
    private String[][] listeJoueurs = new String[4][4];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_choix_mode);

        //Liaison du seekbar avec le nombre de mots
        nbMots = (TextView) findViewById(R.id.nbMots);
        seekBar = (SeekBar) findViewById(R.id.seekBar);
        seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean b) {
                nbMots.setText("" + progress);
                compteurMots = nbMots.getText().toString();
                //Log.d("tagMots", "Nombre de mots choisi: " + compteurMots);
            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) { }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) { }
        });

        //Conserve l'information de l'activité précédente(CreationEquipe)
        Bundle extras = getIntent().getExtras();
        final String compteurNbJoueurs = extras.getString("NB_JOUEURS");
        listeJoueurs = (String[][]) extras.getSerializable("LISTE_JOUEURS");

      /* Test pour vérifier String[][] de joueurs est reçu dans la nouvelle activité
      Log.d("TAG_FOR", "Voici la liste [][] des joueurs retenus dans ChoixMode:");
        for (int i = 0; i<4;i++){       //Équipe
            for(int j = 0; j<4; j++){   //Joueur
                if(listeJoueurs[i][j] != null){
                    Log.d("TAG_FOR", listeJoueurs[i][j]);
                }
            }
        }
       */

        //Transition vers les autres vues à l'aide des boutons
        partieRapide = (Button) findViewById(R.id.partieRapide);
        partieRapide.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openReady(compteurNbJoueurs,listeJoueurs);
            }
        });
        partiePerso = (Button) findViewById(R.id.partiePerso);
        partiePerso.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openPerso(compteurNbJoueurs,listeJoueurs);
            }
        });
    }

    //Redirection vers la page Partie personnalisée
    private void openPerso(String compteurNbJoueurs, String[][] listeJoueurs) {
        Intent intentPerso = new Intent(this, PartiePerso.class);
        intentPerso.putExtra("NB_MOTS_PAR_JOUEUR", compteurMots);
        intentPerso.putExtra("NB_JOUEURS", compteurNbJoueurs);
        Bundle bundleListeJoueurs = new Bundle();
        bundleListeJoueurs.putSerializable("LISTE_JOUEURS", listeJoueurs);
        intentPerso.putExtras(bundleListeJoueurs);
        startActivity(intentPerso);
    }

    //Redirection vers la page Démarrer le jeu
    private void openReady(String compteurNbJoueurs, String[][] listeJoueurs) {
        Intent intentReady = new Intent(this, DemarrerPartie.class);
        intentReady.putExtra("NB_MOTS_PAR_JOUEUR", compteurMots);
        intentReady.putExtra("NB_JOUEURS", compteurNbJoueurs);
        Bundle bundleListeJoueurs = new Bundle();
        bundleListeJoueurs.putSerializable("LISTE_JOUEURS", listeJoueurs);
        intentReady.putExtras(bundleListeJoueurs);
        startActivity(intentReady);
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