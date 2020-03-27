package Modele;


import android.media.audiofx.DynamicsProcessing;

import java.util.ArrayList;
import java.util.Random;


public final class ModeleJeu {

    private static volatile ModeleJeu instance = null;
    private final int NBJOUEURSMIN = 2;
    private final int NBEQUIPEMIN =2;
    private final String[] MOTSRAPIDE = {"King Kong", "Pirate", "Table", "Guitare", "Chaise",
                                         "Soda", "Pomme", "Épee", "Kirby", "Justin Trudeau",
                                         "Livre","Paysan", "Ordinateur", "Cycliste", "Donald Trump",
                                         "Jésus", "César", "Geralt de Riv", "Trou noir", "Neige",
                                         "Magicien", "Renard", "Peinture", "Piano", "Université",
                                         "Coquillage", "Zeus", "Vengeance", "Chevalier", "Viking",
                                         "Ninja", "Frisbee", "Serpent", "Thermostat", "Quarantaine"
                                        };

    private ArrayList<Equipe> equipeList;
    private int nbMotParJoueur;
    private ArrayList<Mot> tableMot;
    private Joueur joueurActif;
    private Mot motActif;
    private int numPhase;
    private int ordre; //Ordre a pour min 1 et max le nb d'equipe en jeu
    private ArrayList<Equipe> podiumFinal;


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

    public void setJoueurActif(Joueur joueurActif) { this.joueurActif = joueurActif; }

    public int getNbMotParJoueur() { return nbMotParJoueur; }

    public Joueur getJoueurActif() {
        return joueurActif;
    }

    public Mot getMotActif() { return motActif; }

    public int getNumPhase() { return numPhase; }

    public void setNumPhase(int numPhase) { this.numPhase = numPhase; }

    public ArrayList<Equipe> getEquipeList() { return equipeList; }

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

    public void addMotPartiePerso(ArrayList<String> listeMot){
        if (tableMot == null){
            tableMot = new ArrayList<Mot>();
        }
        for (int i=0; i<listeMot.size();i++){
            tableMot.add(new Mot(listeMot.get(i)));
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
     * Cree la liste des equipes, si une equipe ne possede pas de joueur elle n'est pas creee
     * @param listeJoueurs
     */
    public void createListEquipe(String[][] listeJoueurs){
        equipeList = new ArrayList<Equipe>();
        for (int i=0; i<listeJoueurs.length; i++){
            ArrayList<Joueur> joueursList = RemoveNull(listeJoueurs[i]);
            if (joueursList.size() > 0)
                equipeList.add(new Equipe(i,joueursList));
        }
    }

    /**
     * Change le joueur Actif et
     * @return Le nom du prochain joueur
     */
    public void NextJoueur(){
        tourSuivant();
        Equipe nextEquipe = getNextEquipe();
        if(!nextEquipe.equals(null)){
            if (nextEquipe.allPlayersPlayed()){
                nextEquipe.resetPlayersTurn();
            }
            do{
                int rand = getRandomNumberInRange(0,nextEquipe.getNbJoueurs());
                System.out.println(rand);
                joueurActif = nextEquipe.getJoueur(rand);
            }while (joueurActif.isaJouer());
        }
    }


    public void nextPlayerInsert(){
        boolean haschanged = false;
        for (int i=0; i<equipeList.size() && !haschanged;i++){
            if (!equipeList.get(i).allPlayersPlayed()){
                for (int j=0; j<equipeList.get(i).getNbJoueurs() && !haschanged;j++){
                    joueurActif = equipeList.get(i).getJoueur(j);
                    if (!joueurActif.isaJouer())
                        haschanged = true;
                }
            }
        }
    }

    public void SetOrdreTour(){
        ordre = 0;
        //Remet a 0 l'ordre de chaque Equipe
        for (int i=0; i<equipeList.size();i++){
            equipeList.get(i).setNumOrdre(0);
        }
        int nbOrdreAffecte = 0;
        do{
            //Affecte un ordre aleatoire aux equipes
            int rand = getRandomNumberInRange(0,equipeList.size());
            if (equipeList.get(rand).getNumOrdre() == 0){
                nbOrdreAffecte++;
                equipeList.get(rand).setNumOrdre(nbOrdreAffecte);
            }
        }while (nbOrdreAffecte<equipeList.size());
    }

    public void MotSuivant(){
        do {
            int rand = getRandomNumberInRange(0,tableMot.size());
            motActif = tableMot.get(rand);
        }while (motActif.isMotTrouve());
    }

    /**
     * Méthode calculant le nombre de mots non devinés (mots restants) dans une phase
     * @return nbMots   Le nombre de mots restants
     */
    public int calculMotsRestants(){
            int nbMots = 0;
            for(int i = 0; i<tableMot.size(); i++){
                if (!tableMot.get(i).isMotTrouve()){
                    nbMots++;
                }
            }
            return nbMots;
        }

    public void ajoutPoint(){
        motActif.setMotTrouve(true);
        joueurActif.ajouterScore();
    }

    /**
     * Enleve les chaines vides et retourne une liste de joueur sous le format ArrayList
     * @param listeJoueur String Array contenant les noms saisis dans la fennetre CreationEquipe
     * @return
     */
    private ArrayList<Joueur> RemoveNull(String[] listeJoueur){
        ArrayList<Joueur> joueursList = new ArrayList<Joueur>();
        for(int i=0; i<listeJoueur.length; i++){
            //Verification que la chaine n est pas vide ou nulle
            if (!listeJoueur[i].trim().isEmpty() && listeJoueur[i] != null){
                joueursList.add(new Joueur(listeJoueur[i].trim()));
            }
        }
        return joueursList;
    }

    public boolean listeMotIsValid(ArrayList<String> listeMot){
        for (int i=0; i<listeMot.size();i++){
            String temp = listeMot.get(i).trim();
            System.out.println("Mot : "+temp);
            if (temp.isEmpty() || temp == null){
                return false;
            }
        }
        return true;
    }

    /**
     * Retourne un chiffre aleatoire compris entre min [inclusif] et max [exclusif]
     * @param min
     * @param max
     * @return
     */
    private int getRandomNumberInRange(int min, int max) {
        if (min >= max) {
            throw new IllegalArgumentException("max must be greater than min");
        }
        Random r = new Random();
        return r.nextInt((max - min)) + min;
    }

    private Equipe getNextEquipe(){
        for (int i=0; i<equipeList.size();i++){
            if (equipeList.get(i).getNumOrdre() == ordre) {
                return equipeList.get(i);
            }
        }
        return null;
    }

    private void tourSuivant(){
        if (ordre < 1 || ordre >= equipeList.size()){
            ordre = 1;
        }
        else
            ordre++;
    }

    public boolean allMotsTrouve(){
        for (int i=0; i<tableMot.size();i++){
            if (!tableMot.get(i).isMotTrouve()){
                return false;
            }
        }
        return true;
    }

    /**
     * Méthode qui détermine l'ordre des équipes pour le podium final
     *
     * Winner = podiumFinal.get(0)
     * 2e = podiumFinal.get(1)
     * Dernier = podiumFinal.get(podiumFinal.size() - 1)
     *
     * @return podiumFinal : tableau des équipes en ordre
     */
    public ArrayList<Equipe> calculerPodiumFinal() {

        int[][] podiumTemp = new int[equipeList.size()][2];
        podiumFinal = new ArrayList<Equipe>();
        //podiumFinal.clear();

        for (int i = 0; i < equipeList.size(); i++) {

            int scoreEquipe = equipeList.get(i).getScore();
            /*int scoreEquipe = 0;

            for (int j = 0; j < equipeList.get(i).getNbJoueurs(); j++) {

                scoreEquipe += equipeList.get(i).getJoueur(j).getScore();
            }*/

            podiumTemp[i][0] = scoreEquipe;
            podiumTemp[i][1] = i;
        }

        java.util.Arrays.sort(podiumTemp, new java.util.Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return Double.compare(a[0], b[0]);
            }
        });

        for (int k = equipeList.size() - 1; k >= 0; k--) {

            podiumFinal.add(equipeList.get(podiumTemp[k][1]));
        }

        return podiumFinal;
    }
    public String getExplicationsPhases(){
        String explications = new String();
        switch (numPhase){
            case 2:
                explications = "Faites deviner le mot cible à l'aide d'un seul mot";
                break;
            case 3:
                explications = "Faites deviner le mot cible en le mimant";
        }
        return explications;
    }

    public void incrementePhase(){
        numPhase++;
    }

    public void resetEtatTableMot(){
        for (int i=0; i<tableMot.size();i++){
            tableMot.get(i).setMotTrouve(false);
        }
    }

}
