package Controleur;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.media.audiofx.DynamicsProcessing;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.boulets.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.android.material.textfield.TextInputEditText;

import Modele.Equipe;
import Modele.ModeleJeu;

public class CreationEquipe extends AppCompatActivity {
    //Déclaration de variables
    private FloatingActionButton ajoutRouge;
    private FloatingActionButton ajoutBleu;
    private FloatingActionButton ajoutJaune;
    private FloatingActionButton ajoutVert;
    private ImageView jauneBanniere;
    private ImageView vertBanniere;
    private TextView jauneEquipe;
    private TextView vertEquipe;
    private TextInputEditText rougeJ1;
    private TextInputEditText rougeJ2;
    private TextInputEditText rougeJ3;
    private TextInputEditText rougeJ4;
    private TextInputEditText bleuJ1;
    private TextInputEditText bleuJ2;
    private TextInputEditText bleuJ3;
    private TextInputEditText bleuJ4;
    private TextInputEditText jauneJ1;
    private TextInputEditText jauneJ2;
    private TextInputEditText jauneJ3;
    private TextInputEditText jauneJ4;
    private TextInputEditText vertJ1;
    private TextInputEditText vertJ2;
    private TextInputEditText vertJ3;
    private TextInputEditText vertJ4;
    private TextView msgErreur;

    public String[][] listeJoueurs = new String[4][4];
    float x1, x2, y1, y2;

    private ModeleJeu jeu = ModeleJeu.getInstance();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_creation_equipe);
        msgErreur = (TextView) findViewById(R.id.msg_erreur);

        //Gestion bouton & Joueurs Rouge
        ajoutRouge = (FloatingActionButton) findViewById(R.id.rouge_ajout);
        rougeJ1 = (TextInputEditText) findViewById(R.id.rouge_J1);
        rougeJ2 = (TextInputEditText) findViewById(R.id.rouge_J2);
        rougeJ3 = (TextInputEditText) findViewById(R.id.rouge_J3);
        rougeJ4 = (TextInputEditText) findViewById(R.id.rouge_J4);
        ajoutRouge.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRouge();
            }
        });

        //Gestion bouton & Joueurs Bleu
        ajoutBleu = (FloatingActionButton) findViewById(R.id.bleu_ajout);
        bleuJ1 = (TextInputEditText) findViewById(R.id.bleu_J1);
        bleuJ2 = (TextInputEditText) findViewById(R.id.bleu_J2);
        bleuJ3 = (TextInputEditText) findViewById(R.id.bleu_J3);
        bleuJ4 = (TextInputEditText) findViewById(R.id.bleu_J4);
        ajoutBleu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBleu();
            }
        });

        //Gestion bouton & Joueurs Jaune
        ajoutJaune = (FloatingActionButton) findViewById(R.id.jaune_ajout);
        jauneBanniere = (ImageView) findViewById(R.id.jaune_banner);
        jauneEquipe = (TextView) findViewById(R.id.jaune_Team);
        jauneJ1 = (TextInputEditText) findViewById(R.id.jaune_J1);
        jauneJ2 = (TextInputEditText) findViewById(R.id.jaune_J2);
        jauneJ3 = (TextInputEditText) findViewById(R.id.jaune_J3);
        jauneJ4 = (TextInputEditText) findViewById(R.id.jaune_J4);
        ajoutJaune.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addJaune(); }});

        //Gestion bouton & Joueurs Vert
        ajoutVert = (FloatingActionButton) findViewById(R.id.vert_ajout);
        vertBanniere = (ImageView) findViewById(R.id.vert_banner);
        vertEquipe = (TextView) findViewById(R.id.vert_Team);
        vertJ1 = (TextInputEditText) findViewById(R.id.vert_J1);
        vertJ2 = (TextInputEditText) findViewById(R.id.vert_J2);
        vertJ3 = (TextInputEditText) findViewById(R.id.vert_J3);
        vertJ4 = (TextInputEditText) findViewById(R.id.vert_J4);
        ajoutVert.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) { addVert(); }});

    }

    //Logique pour ajouter des membres dans l'équipe rouge
    public void addRouge () {
        if (rougeJ4.getVisibility() == View.INVISIBLE && rougeJ3.getVisibility() == View.VISIBLE) {
            rougeJ4.setVisibility(View.VISIBLE);
        }
        if (rougeJ3.getVisibility() == View.INVISIBLE) {
            rougeJ3.setVisibility(View.VISIBLE);
        }
    }

    //Logique pour ajouter des membres dans l'équipe bleu
    public void addBleu(){
        if (bleuJ4.getVisibility() == View.INVISIBLE && bleuJ3.getVisibility() == View.VISIBLE) {
            bleuJ4.setVisibility(View.VISIBLE);
        }
        if (bleuJ3.getVisibility() == View.INVISIBLE) {
            bleuJ3.setVisibility(View.VISIBLE);
        }
    }

    //Logique pour ajouter des membres dans l'équipe jaune
    public void addJaune () {
        if (jauneJ4.getVisibility() == View.INVISIBLE && jauneJ3.getVisibility() == View.VISIBLE) {
            jauneJ4.setVisibility(View.VISIBLE);
        } else if (jauneJ3.getVisibility() == View.INVISIBLE && jauneJ2.getVisibility() == View.VISIBLE) {
            jauneJ3.setVisibility(View.VISIBLE);
        } else {
            jauneBanniere.setVisibility(View.VISIBLE);
            jauneEquipe.setVisibility(View.VISIBLE);
            jauneJ1.setVisibility(View.VISIBLE);
            jauneJ2.setVisibility(View.VISIBLE);
        }
    }

    //Logique pour ajouter des membres dans l'équipe vert
    public void addVert(){
    if(vertJ4.getVisibility()==View.INVISIBLE && vertJ3.getVisibility()==View.VISIBLE) {
        vertJ4.setVisibility(View.VISIBLE);
    } else if(vertJ3.getVisibility()==View.INVISIBLE && vertJ2.getVisibility() == View.VISIBLE){
        vertJ3.setVisibility(View.VISIBLE);
    } else {
        vertBanniere.setVisibility(View.VISIBLE);
        vertEquipe.setVisibility(View.VISIBLE);
        vertJ1.setVisibility(View.VISIBLE);
        vertJ2.setVisibility(View.VISIBLE);
    }
}

    //Pour le swipe
    public boolean onTouchEvent (MotionEvent touchevent){
        switch (touchevent.getAction()) {
            case MotionEvent.ACTION_DOWN:
                x1 = touchevent.getX();
                y1 = touchevent.getY();
                break;
            case MotionEvent.ACTION_UP:
                x2 = touchevent.getX();
                y2 = touchevent.getY();
                if (x1 > x2 && x1 - x2 > 300) {
                    //Verifie si kes equipes ont minimum 2 joueurs sinon affiche un message d erreur
                    if (jeu.verificationMinJoueur()){
                        Intent next = new Intent(CreationEquipe.this, ChoixMode.class);
                        startActivity(next);
                    }
                    else{
                        this.msgErreur.setVisibility(View.VISIBLE);
                    }

                }
                if (x1 < x2 && x2 - x1 > 300) {
                    finish();
                }
                break;
        }
        return false;
    }

    //Empêche d'utiliser le bouton "back" de l'appareil pour revenir à la page précédente
    @Override
    public void onBackPressed () {
    }

    private void sendEquipes(){
        getRedTeam();
        getBlueTeam();
        getGreenTeam();
        getYellowTeam();
        jeu.createListEquipe(listeJoueurs);

    }

    private String[] getRedTeam(){
        listeJoueurs[0][0] = rougeJ1.getText().toString();
        listeJoueurs[0][1] = rougeJ2.getText().toString();
        listeJoueurs[0][2] = rougeJ3.getText().toString();
        listeJoueurs[0][3] = rougeJ4.getText().toString();
        return listeJoueurs[0];
    }

    private String[] getBlueTeam(){
        listeJoueurs[1][0] = bleuJ1.getText().toString();
        listeJoueurs[1][1] = bleuJ2.getText().toString();
        listeJoueurs[1][2] = bleuJ3.getText().toString();
        listeJoueurs[1][3] = bleuJ4.getText().toString();
        return listeJoueurs[1];
    }

    private String[] getYellowTeam(){
        listeJoueurs[2][0] = jauneJ1.getText().toString();
        listeJoueurs[2][1] = jauneJ2.getText().toString();
        listeJoueurs[2][2] = jauneJ3.getText().toString();
        listeJoueurs[2][3] = jauneJ4.getText().toString();
        return listeJoueurs[2];
    }

    private String[] getGreenTeam(){
        listeJoueurs[3][0] = vertJ1.getText().toString();
        listeJoueurs[3][1] = vertJ2.getText().toString();
        listeJoueurs[3][2] = vertJ3.getText().toString();
        listeJoueurs[3][3] = vertJ4.getText().toString();
        return listeJoueurs[3];
    }
}
