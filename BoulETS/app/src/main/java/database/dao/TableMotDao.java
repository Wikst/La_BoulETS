package database.dao;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import Modele.TableMot;

@Dao
public interface TableMotDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    void createTable(TableMot table);

    @Query("SELECT nomTable FROM TableMot WHERE id = :tableId")
    String getTableName(int tableId);

    @Query("SELECT id FROM TableMot WHERE nomTable = :nomTable")
    int getTableId(String nomTable);
}
