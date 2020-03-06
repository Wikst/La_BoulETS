package Modele;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.util.List;

@Entity
public class TableMot {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String nomTable;

    public TableMot() {}

    public TableMot(String nomTable) {
        this.nomTable = nomTable;
    }

    // ---- GETTER ----
    public int getId() { return id; }
    public String getNomTable() { return nomTable; }

    // ---- SETTER ----
    public void setId(int id) { this.id = id; }
    public void setNomTable(String nomTable) { this.nomTable = nomTable; }


    // ---- POPULATE ----

    public static TableMot populateData() {
        return new TableMot("Default");
    }
}
