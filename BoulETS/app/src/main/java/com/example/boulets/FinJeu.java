package com.example.boulets;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class FinJeu extends AppCompatActivity {

    //Déclaration des variables
    private Button scoreFinal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_fin_jeu);

        //Transition vers les autres vues à l'aide des boutons
        scoreFinal = (Button) findViewById(R.id.bouton_score_final);
        scoreFinal.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openPointageFinal();
            }
        });
    }

    //Redirection vers la page de pointage final
    private void openPointageFinal() {
        Intent intentFinal = new Intent(this, Score.class);
        startActivity(intentFinal);
    }
}
