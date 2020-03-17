package Modele;

import android.graphics.Color;

public class Equipe {
    Color CouleurEquipe;
    String[] tabJoueur;
    int Score;

    public Equipe(Color CouleurEquipe, String[] tabJoueur){
        this.CouleurEquipe =  CouleurEquipe;
        this.tabJoueur = tabJoueur;
        this.Score = 0;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public Color getCouleurEquipe() {
        return CouleurEquipe;
    }

    public String getJoueur(int numJoueur){
        return tabJoueur[numJoueur];
    }
}
