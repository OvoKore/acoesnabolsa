package com.ovokore.acoesnabolsa.persistence;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.APP_DB;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.DAO;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.model.Transacao;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

@Database(entities = {
        Acao.class,
        Transacao.class
}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {

    public abstract AcaoDao acaoDao();

    public abstract TransacaoDao transacaoDao();

    private static AppDatabase instance;

    public static AppDatabase getDatabase(final Context context) {
        if (instance == null) {
            synchronized (AppDatabase.class) {
                if (instance == null) {
                    instance = Room.databaseBuilder(context, AppDatabase.class, APP_DB).allowMainThreadQueries().build();
                }
            }
        }
        return instance;

    }

    public Object callDynamicMethod(Class<?> cls, String method) {
        return callDynamicMethod(cls, method, null);
    }

    public Object callDynamicMethod(Class<?> cls, String method, Object arg) {
        String clsName = cls.getSimpleName();
        String daoMethodName = clsName.toLowerCase() + DAO;

        Method daoMethod = null;
        try {
            daoMethod = this.getClass().getDeclaredMethod(daoMethodName);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Object dao = null;
        try {
            dao = daoMethod.invoke(this);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
        Method getMethod = null;
        try {
            if (arg == null) {
                getMethod = dao.getClass().getDeclaredMethod(method);
            } else {
                getMethod = dao.getClass().getDeclaredMethod(method, arg.getClass());
            }
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }

        try {
            if (arg == null) {
                return getMethod.invoke(dao);
            } else {
                return getMethod.invoke(dao, arg);
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }
    }
}
