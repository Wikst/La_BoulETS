package Modele;

import java.util.ArrayList;

public class Equipe {
    private int numEquipe;
    private ArrayList<Joueur> tabJoueur;
    private int numOrdre;

    public Equipe(int numEquipe, ArrayList<Joueur> tabJoueur){
        this.numEquipe = numEquipe;
        this.tabJoueur = tabJoueur;
        this.numOrdre = 0;
    }

    public int getNumEquipe() {
        return numEquipe;
    }

    public Joueur getJoueur(int numJoueur){
        return tabJoueur.get(numJoueur);
    }

    public int getNbJoueurs(){
        return tabJoueur.size();
    }

    public int getNumOrdre() {
        return numOrdre;
    }

    public void setNumOrdre(int numOrdre) {
        this.numOrdre = numOrdre;
    }

    public boolean allPlayersPlayed(){
        boolean allPlayed = false;
        int temp = 0;
        for (int i=0; i<tabJoueur.size();i++){
            if (tabJoueur.get(i).isaJouer()){
                temp++;
            }
            if (temp >= tabJoueur.size()){
                allPlayed = true;
            }
        }
        return allPlayed;
    }

    public void resetPlayersTurn(){
        for (int i=0; i<tabJoueur.size();i++){
            tabJoueur.get(i).setaJouer(false);
        }
    }

    public int getScore() {
        int score=0;
        for (int i=0; i<tabJoueur.size();i++){
            score += tabJoueur.get(i).getScore();
        }
        return score;
    }
}
