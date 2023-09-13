package com.ovokore.acoesnabolsa.persistence;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.model.Transacao;

@Database(entities = {
        Acao.class, Transacao.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AcaoDao acaoDao();

    public abstract TransacaoDao transacaoDao();

    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, "app.db").allowMainThreadQueries().build();
                }
            }
        }
        return instance;
    }
}
