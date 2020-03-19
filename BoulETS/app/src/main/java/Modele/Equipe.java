package Modele;

import java.util.ArrayList;

public class Equipe {
    private int numEquipe;
    private ArrayList<String> tabJoueur;
    private int Score;

    public Equipe(int numEquipe, ArrayList<String> tabJoueur){
        this.numEquipe = numEquipe;
        this.tabJoueur = tabJoueur;
        this.Score = 0;
    }

    public int getScore() {
        return Score;
    }

    public void setScore(int score) {
        Score = score;
    }

    public int getNumEquipe() {
        return numEquipe;
    }

    public String getJoueur(int numJoueur){
        return tabJoueur.get(numJoueur);
    }

    public int getNbJoueurs(){
        return tabJoueur.size();
    }

    private void FillArayList(String[] listeJoueur){
        for(int i=0; i<listeJoueur.length; i++){
            //Verification que la chaine n est pas vide ou nulle
            if (listeJoueur[i].length() != 0 && listeJoueur[i] != null){
                this.tabJoueur.add(listeJoueur[i]);
            }
        }
    }
}
