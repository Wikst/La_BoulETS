package Modele;

import java.util.ArrayList;

//Patern de Singleton inspire de https://openclassrooms.com/forum/sujet/android-meilleur-pattern-singleton
public final class ModeleJeu {

    private static volatile ModeleJeu instance = null;

    private ArrayList<Equipe> equipeList;
    private int nbMotParJoueur;

    private ModeleJeu(){
        super();
    }

    public final static ModeleJeu getInstance() {
        if (ModeleJeu.instance == null) {
            // Le mot-clé synchronized sur ce bloc empêche toute instanciation
            // multiple même par différents "threads".
            // Il est TRES important.
            synchronized(ModeleJeu.class) {
                if (ModeleJeu.instance == null) {
                    ModeleJeu.instance = new ModeleJeu();
                }
            }
        }
        return ModeleJeu.instance;
    }

    public void setNbMotParJoueur(int nbMotParJoueur) {
        this.nbMotParJoueur = nbMotParJoueur;
    }

    public int getNbJoueurs(){
        int nbJoueurPartie =0;
        for (int i = 0; i< equipeList.size(); i++ ){
            nbJoueurPartie += equipeList.get(i).getNbJoueurs();
        }
        return nbJoueurPartie;
    }

    public boolean verificationMinJoueur(){
        int min = 2;
        boolean verif = false;
        for (int i = 0; i< equipeList.size(); i++){
            min = Math.min(min,equipeList.get(i).getNbJoueurs());
            System.out.println("min : "+min);
            System.out.println("size : "+equipeList.size());
            System.out.println("nbJoueurs : "+equipeList.get(i).getNbJoueurs());
        }
        if (min >= 2)
            verif = true;
        return verif;
    }

    public void createListEquipe(String[][] listeJoueurs){
        equipeList = new ArrayList<Equipe>();
        for (int i=0; i<listeJoueurs.length; i++){
            equipeList.add(new Equipe(i,listeJoueurs[i]));
        }
    }
}
