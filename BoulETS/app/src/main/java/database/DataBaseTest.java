package database;

import android.content.Context;

import androidx.room.Room;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import Modele.Mot;

public class DataBaseTest {
    private AppDatabase db;

    @Before
    public void createDb() {
        Context context = ApplicationProvider.getApplicationContext();
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
        Mot = AppDatabase.getInstance();
    }

    @Test
    public void verifieurMot() {
        List<Mot> test = db.motDao().getMots(1).getValue();
    }

}
