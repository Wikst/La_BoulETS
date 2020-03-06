package Modele;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(foreignKeys = @ForeignKey(entity = TableMot.class,
        parentColumns = "id",
        childColumns = "tableId"))
public class Mot {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String mot;
    private Boolean trouve; //le mot a ete trouve
    private int tableId;

    public Mot() {}

    public Mot(String mot, int tableId){
        this.mot = mot;
        this.tableId = tableId;
        this.trouve = false;
    }

    //---- GETTER ----
    public int getId() { return id; }
    public String getMot() { return mot; }
    public Boolean getTrouve() { return trouve; }
    public int getTableId() { return tableId; }

    //---- SETTER ----
    public void setMot(String mot) { this.mot = mot; }
    public void setTrouve(Boolean trouve) { this.trouve = trouve; }
    public void setId(int id) { this.id = id; }
    public void setTableId(int tableId) { this.tableId = tableId; }

    // ---- POPULATE ----
    public static Mot[] populateData(int tableId) {
        return new Mot[] {
                new Mot("King Kong",tableId),
                new Mot("Kirby",tableId),
                new Mot("Trou Noir",tableId),
                new Mot("Chaise",tableId),
                new Mot("Cahier",tableId),
                new Mot("Michael Jackson",tableId),
                new Mot("Guitare",tableId),
                new Mot("Revolver",tableId),
                new Mot("Tasse",tableId),
        };
    }
}
