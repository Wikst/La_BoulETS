package Modele;

public class Joueur {
    private String nom;
    private boolean aJouer;

    public Joueur(String nom){
        this.nom = nom;
        this.aJouer = false;
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
}
