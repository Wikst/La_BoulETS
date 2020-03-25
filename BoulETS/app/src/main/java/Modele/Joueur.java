package Modele;

public class Joueur {
    private String nom;
    private boolean aJouer;
    private int Score;

    public Joueur(String nom){
        this.nom = nom;
        this.aJouer = false;
        this.Score = 0;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public boolean isaJouer() {
        return aJouer;
    }

    public void setaJouer(boolean aJouer) {
        this.aJouer = aJouer;
    }

    public int getScore() { return Score; }

    public void ajouterScore(){
        this.Score++;
    }
}
