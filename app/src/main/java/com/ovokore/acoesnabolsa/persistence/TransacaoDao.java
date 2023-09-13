package com.ovokore.acoesnabolsa.persistence;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.ovokore.acoesnabolsa.model.Transacao;

import java.util.List;

@Dao
public interface TransacaoDao {
    @Insert
    void insert(Transacao transacao);

    @Update
    void update(Transacao transacao);

    @Delete
    void delete(Transacao transacao);

    @Query("SELECT * FROM transacao")
    List<Transacao> getAll();

    @Query("SELECT * FROM transacao WHERE id = :id")
    Transacao getById(long id);
}
