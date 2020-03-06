package database.dao;

import androidx.lifecycle.LiveData;
import androidx.room.*;

import java.util.List;

import Modele.Mot;


@Dao
public interface MotDao {

    @Query("SELECT * FROM Mot WHERE tableId = :tableId")
    LiveData<List<Mot>> getMots(int tableId);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    public void insertMots(Mot...mots);

    @Update
    int updateMot(Mot mot);

    @Query("DELETE FROM Mot WHERE id = :motId")
    int deleteMot(int motId);
}
