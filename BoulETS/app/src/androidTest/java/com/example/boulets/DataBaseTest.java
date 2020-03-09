package database;

import android.app.Instrumentation;
import android.content.Context;

import androidx.room.Room;

import com.example.boulets.MainActivity;

import org.junit.Before;
import org.junit.Test;

import java.util.List;

import Modele.Mot;

public class DataBaseTest {
    private AppDatabase db;
    private Context context;

    @Before
    public void createDb() {
        context = MainActivity.this;
        db = Room.inMemoryDatabaseBuilder(context, AppDatabase.class).build();
    }

    @Test
    public void verifieurMot() {
        List<Mot> test = db.motDao().getMots(1).getValue();
    }

}
