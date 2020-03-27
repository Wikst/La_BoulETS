package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

public class Score extends AppCompatActivity {

    //Déclaration des variables
    private Button retourAccueil;
    private TextView equipe1;
    private TextView equipe2;
    private TextView equipe3;
    private TextView equipe4;
    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_final);

        equipe1 = (TextView) findViewById(R.id.Equipe_1er);
        equipe2 = (TextView) findViewById(R.id.Equipe_2e);
        equipe3 = (TextView) findViewById(R.id.Equipe_3e);
        equipe4 = (TextView) findViewById(R.id.Equipe_4e);
        score1 = (TextView) findViewById(R.id.score_1er);
        score2 = (TextView) findViewById(R.id.score_2e);
        score3 = (TextView) findViewById(R.id.score_3e);
        score4 = (TextView) findViewById(R.id.score_4e);


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
