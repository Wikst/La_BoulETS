package Modele;

import com.google.android.material.tabs.TabLayout;

import java.util.ArrayList;

//Patern de Singleton inspire de https://openclassrooms.com/forum/sujet/android-meilleur-pattern-singleton
public final class ModeleJeu {

    private static volatile ModeleJeu instance = null;

    private ArrayList<Equipe> equipeList;
    private int nbMotParJoueur;
    private final int NBJOUEURSMIN = 2;
    private final int NBEQUIPEMIN =2;
    private TableMot tableMot;


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

    public void setTablePartieRapide(){
        this.tableMot = TableMot.populateData();
        System.out.println("Nom table : "+tableMot.getNomTable());
        System.out.println("id : "+tableMot.getId());
    }

    public int getNbJoueurs(){
        int nbJoueurPartie =0;
        for (int i = 0; i< equipeList.size(); i++ ){
            nbJoueurPartie += equipeList.get(i).getNbJoueurs();
        }
        return nbJoueurPartie;
    }

    public boolean verificationMinJoueur(){
        boolean verif = false;
        System.out.println("size : "+equipeList.size());
        if (equipeList.size() >= NBEQUIPEMIN){
            int min = NBJOUEURSMIN;
            for (int i = 0; i< equipeList.size(); i++){
                min = Math.min(min,equipeList.get(i).getNbJoueurs());
                System.out.println("nbJoueurs : "+equipeList.get(i).getNbJoueurs());
            }
            System.out.println("min : "+min);
            if (min >= NBJOUEURSMIN)
                verif = true;
        }
        return verif;
    }

    public void createListEquipe(String[][] listeJoueurs){
        equipeList = new ArrayList<Equipe>();
        for (int i=0; i<listeJoueurs.length; i++){
            ArrayList<String> joueursList = RemoveNull(listeJoueurs[i]);
            if (joueursList.size() > 0)
                equipeList.add(new Equipe(i,joueursList));
        }
    }

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
