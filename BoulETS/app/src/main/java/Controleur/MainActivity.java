package Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.boulets.R;

import Modele.ModeleJeu;

public class MainActivity extends AppCompatActivity {
    private Button buttonCommencer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acceuil);
        ModeleJeu.getInstance(); //Instanciation du singleton ModeleJeu

        buttonCommencer = (Button) findViewById(R.id.buttonCommencer);
        buttonCommencer.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openCreationEquipe();
            }
        });
    }

    public void openCreationEquipe() {
        Intent intent = new Intent(this, CreationEquipe.class);
        startActivity(intent);
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}
}
