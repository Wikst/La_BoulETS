package Modele;

import android.content.Context;


import java.util.ArrayList;
import java.util.List;



public final class ModeleJeu {

    private static volatile ModeleJeu instance = null;
    private final int NBJOUEURSMIN = 2;
    private final int NBEQUIPEMIN =2;
    private final String[] MOTSRAPIDE = {"King Kong", "Pirate", "Table", "Guitare", "Chaise",
                                         "Soda", "Pomme", "Epee", "Kirby", "Justin Trudeau",
                                         "Livre","Paysan", "Ordinateur", "Cycliste", "Donald Trump",
                                         "Jesus", "Cesar"
                                        };

    private ArrayList<Equipe> equipeList;
    private int nbMotParJoueur;
    private ArrayList<Mot> tableMot;


    private ModeleJeu(){
        super();
    }

    //Patern de Singleton inspire de https://openclassrooms.com/forum/sujet/android-meilleur-pattern-singleton
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

    /**
     * Rempli tableMot par les mots precharges dans le jeu
     * Pas de difference si le joueur change le nb de mot par joueur
     */
    public void setTablePartieRapide(){
        tableMot = new ArrayList<Mot>();
        for (int i=0; i<MOTSRAPIDE.length; i++){
            tableMot.add(new Mot(MOTSRAPIDE[i]));
            //System.out.println("Mot ajoute : "+tableMot.get(i).getMot());
        }
    }

    public int getNbJoueurs(){
        int nbJoueurPartie =0;
        for (int i = 0; i< equipeList.size(); i++ ){
            nbJoueurPartie += equipeList.get(i).getNbJoueurs();
        }
        return nbJoueurPartie;
    }

    /**
     * Verifie qu il y a bien minimun 2 joueurs dans chaque equipe et un minimum de 2 equipes
     * @return
     */
    public boolean verificationMinJoueur(){
        boolean verif = false;
        //System.out.println("size : "+equipeList.size());
        if (equipeList.size() >= NBEQUIPEMIN){
            int min = NBJOUEURSMIN;
            for (int i = 0; i< equipeList.size(); i++){
                min = Math.min(min,equipeList.get(i).getNbJoueurs());
                //System.out.println("nbJoueurs : "+equipeList.get(i).getNbJoueurs());
            }
            //System.out.println("min : "+min);
            if (min >= NBJOUEURSMIN)
                verif = true;
        }
        return verif;
    }

    /**
     * Cree la liste des equipes, si une equipe ne possede pas de joueur elle n<est pas creee
     * @param listeJoueurs
     */
    public void createListEquipe(String[][] listeJoueurs){
        equipeList = new ArrayList<Equipe>();
        for (int i=0; i<listeJoueurs.length; i++){
            ArrayList<String> joueursList = RemoveNull(listeJoueurs[i]);
            if (joueursList.size() > 0)
                equipeList.add(new Equipe(i,joueursList));
        }
    }

    /**
     * Enleve les chaines vides et retourne une liste de joueur sous le format ArrayList
     * @param listeJoueur
     * @return
     */
    private ArrayList<String> RemoveNull(String[] listeJoueur){
        ArrayList<String> joueursList = new ArrayList<String>();
        for(int i=0; i<listeJoueur.length; i++){
            //Verification que la chaine n est pas vide ou nulle
            if (listeJoueur[i].length() != 0 && listeJoueur[i] != null){
                joueursList.add(listeJoueur[i]);
            }
        }
        return joueursList;
    }
}
