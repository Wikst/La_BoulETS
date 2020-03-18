package Modele;

import java.util.List;

//Patern de Singleton inspire de https://openclassrooms.com/forum/sujet/android-meilleur-pattern-singleton
public final class ModeleJeu {

    private static volatile ModeleJeu instance = null;

    private List<Equipe> EquipeList;

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
}
