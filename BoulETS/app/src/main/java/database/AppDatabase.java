package database;


import android.content.Context;

import androidx.annotation.NonNull;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import androidx.sqlite.db.SupportSQLiteDatabase;

import java.util.concurrent.Executors;

import Modele.Mot;
import Modele.TableMot;
import database.dao.MotDao;
import database.dao.TableMotDao;

@Database(entities = {Mot.class, TableMot.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    // ---- SINGLETON ----
    private static AppDatabase INSTANCE;

    // ---- DAO ----
    public abstract MotDao motDao();
    public abstract TableMotDao tableMotDao();

    // ---- INSTANCE ----
    public synchronized static AppDatabase getInstance(Context context) {
        if (INSTANCE == null) {
            INSTANCE = buildDatabase(context);
        }
        return INSTANCE;
    }

    private static AppDatabase buildDatabase(final Context context) {
        return Room.databaseBuilder(context,
                AppDatabase.class,
                "GTI510-Database")
                .addCallback(new Callback() {
                    @Override
                    public void onCreate(@NonNull SupportSQLiteDatabase db) {
                        super.onCreate(db);
                        Executors.newSingleThreadScheduledExecutor().execute(new Runnable() {
                            @Override
                            public void run() {
                                getInstance(context).tableMotDao().createTable(TableMot.populateData());
                                getInstance(context).motDao().insertMots(Mot.populateData(1));
                            }
                        });
                    }
                })
                .build();
    }
}
