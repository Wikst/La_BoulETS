package Modele;


import java.util.ArrayList;

public class Mot {
    private String mot;
    private Boolean trouve; //le mot a ete trouve

    public Mot() {}

    public Mot(String mot){
        this.mot = mot;
        this.trouve = false;
    }

    //---- GETTER ----
    public String getMot() { return mot; }
    public Boolean getTrouve() { return trouve; }

    //---- SETTER ----
    public void setMot(String mot) { this.mot = mot; }
    public void setTrouve(Boolean trouve) { this.trouve = trouve; }

}
