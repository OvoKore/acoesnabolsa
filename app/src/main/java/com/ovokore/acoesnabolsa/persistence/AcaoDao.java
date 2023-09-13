package com.ovokore.acoesnabolsa.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ovokore.acoesnabolsa.model.Acao;

import java.util.List;

@Dao
public interface AcaoDao {
    @Insert
    void insert(Acao acao);

    @Update
    void update(Acao acao);

    @Delete
    void delete(Acao acao);

    @Query("SELECT * FROM acao")
    List<Acao> getAll();

    @Query("SELECT * FROM acao WHERE id = :id")
    Acao getById(long id);
}
