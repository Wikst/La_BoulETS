package Controleur;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.boulets.R;

import java.util.ArrayList;

import Modele.Equipe;
import Modele.ModeleJeu;

public class Score extends AppCompatActivity {

    //Déclaration des variables
    private Button retourAccueil;
    private TextView position3;
    private TextView position4;
    private TextView equipe1;
    private TextView equipe2;
    private TextView equipe3;
    private TextView equipe4;
    private TextView score1;
    private TextView score2;
    private TextView score3;
    private TextView score4;
    private ModeleJeu jeu = ModeleJeu.getInstance();
    private ArrayList<Equipe> equipeList;

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
        position3 = (TextView) findViewById(R.id.position3);
        position4 = (TextView) findViewById(R.id.position4);

        equipeList = jeu.calculerPodiumFinal();
        afficherEquipe(equipeList);

        //Transition vers les autres vues à l'aide des boutons
        retourAccueil = (Button) findViewById(R.id.bouton_retour_accueil);
        retourAccueil.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                openRetourAccueil();
            }
        });
    }

    /**
     * Méthode qui ajuste l'affichage des différents éléments de la vue
     * @param equipeList    La liste des équipes par ordre de gagnant
     */
    private void afficherEquipe(ArrayList<Equipe> equipeList) {
        switch(equipeList.size()){
            case 4 :
                equipe4.setVisibility(View.VISIBLE);
                score4.setVisibility(View.VISIBLE);
                position4.setVisibility(View.VISIBLE);
                equipe4.setText(convertDigitToColor(equipeList,3));
                score4.setText(Integer.toString(equipeList.get(3).getScore()));

            case 3 :
                equipe3.setVisibility(View.VISIBLE);
                score3.setVisibility(View.VISIBLE);
                position3.setVisibility(View.VISIBLE);
                equipe3.setText(convertDigitToColor(equipeList,2));
                score3.setText(Integer.toString(equipeList.get(2).getScore()));

            case 2 :
                equipe1.setText(convertDigitToColor(equipeList,0));
                score1.setText(Integer.toString(equipeList.get(0).getScore()));
                equipe2.setText(convertDigitToColor(equipeList,1));
                score2.setText(Integer.toString(equipeList.get(1).getScore()));
                break;
        }
    }

    //Redirection vers le menu d'accueil
    private void openRetourAccueil() {
        Intent intentAccueil = new Intent(this, MainActivity.class);
        startActivity(intentAccueil);
    }

    private String convertDigitToColor(ArrayList<Equipe> listEquipe, int compteur){
        switch(listEquipe.get(compteur).getNumEquipe()){
            case 0: return "Rouge";
            case 1 : return "Bleu";
            case 2 : return "Jaune";
            case 3 : return "Vert";
        }
        return null;
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed() {}
}
