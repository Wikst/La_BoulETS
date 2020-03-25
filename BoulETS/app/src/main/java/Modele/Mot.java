package Modele;


public class Mot {
    private String mot;
    private Boolean motTrouve; //le mot a ete trouve

    public Mot() {}

    public Mot(String mot){
        this.mot = mot;
        this.motTrouve = false;
    }

    //---- GETTER ----
    public String getMot() { return mot; }
    public Boolean isMotTrouve() { return motTrouve; }

    //---- SETTER ----
    public void setMot(String mot) { this.mot = mot; }
    public void setMotTrouve(Boolean motTrouve) { this.motTrouve = motTrouve; }

}
