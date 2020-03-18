package Modele;

import android.graphics.Color;

public class Equipe {
    private int CouleurEquipe;
    private String[] tabJoueur;
    private int Score;

    public Equipe(int CouleurEquipe, String[] tabJoueur){
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

    public int getCouleurEquipe() {
        return CouleurEquipe;
    }

    public String getJoueur(int numJoueur){
        return tabJoueur[numJoueur];
    }

    public int getNbJoueurs(){
        return tabJoueur.length;
    }
}
