package com.example.boulets;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

public class CreationEquipe extends AppCompatActivity {
    private Button buttonAjoutRouge;
    private Button buttonAjoutBleu;
    private Button buttonAjoutJaune;
    private Button buttonAjoutVert;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_equipe);
    }
}
