package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TransitionPhase extends AppCompatActivity {
    //Déclaration des variables
    private Button pret;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transition_phase);

        //Transition vers les autres vues à l'aide des boutons
        pret = (Button) findViewById(R.id.button_suivant);
        pret.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openFenetreJeu();
            }
        });
    }

    //Redirection vers la page de jeu
    private void openFenetreJeu() {
        //Intent intentDemarrer = new Intent(this, FenetreJeu.class);
        Intent intentDemarrer = new Intent(this, FinJeu.class);
        startActivity(intentDemarrer);
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}

}
