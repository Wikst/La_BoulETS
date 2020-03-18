package Modele;

import java.util.List;

//Patern de Singleton inspire de https://openclassrooms.com/forum/sujet/android-meilleur-pattern-singleton
public final class ModeleJeu {

    private static volatile ModeleJeu instance = null;

    private List<Equipe> EquipeList;
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
        for (int i=0; i<EquipeList.size(); i++ ){
            nbJoueurPartie += EquipeList.get(i).getNbJoueurs();
        }
        return nbJoueurPartie;
    }

    public boolean verificationMinJoueur(){
        for (int i=0; i<EquipeList.size();i++){
            if (EquipeList.get(i).getNbJoueurs() < 2) return false;
        }
        return true;
    }

    public void createListEquipe(String[][] listeJoueurs){
        
    }
}
