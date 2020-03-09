package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Score extends AppCompatActivity {

    //Déclaration des variables
    private Button retourAccueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_final);

        //Transition vers les autres vues à l'aide des boutons
        retourAccueil = (Button) findViewById(R.id.bouton_retour_accueil);
        retourAccueil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openRetourAccueil();
            }
        });
    }

    //Redirection vers le menu d'accueil
    private void openRetourAccueil() {
        Intent intentAccueil = new Intent(this, MainActivity.class);
        startActivity(intentAccueil);
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}
}
