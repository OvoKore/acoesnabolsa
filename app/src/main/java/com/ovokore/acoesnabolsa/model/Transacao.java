package com.ovokore.acoesnabolsa.model;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.ID;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.ID_ACAO;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.TRANSACAO;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(
        tableName = TRANSACAO,
        foreignKeys = @ForeignKey(
                entity = Acao.class,
                parentColumns = ID,
                childColumns = ID_ACAO,
                onDelete = ForeignKey.CASCADE
        )
)
public class Transacao implements Serializable {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long id_acao;

    private int quantidade;

    private int tipoTransacao;

    public Transacao() {

    }

    public Transacao(long id_acao, int quantidade, int tipoTransacao) {
        this.id_acao = id_acao;
        this.quantidade = quantidade;
        this.tipoTransacao = tipoTransacao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public long getId_acao() {
        return id_acao;
    }

    public void setId_acao(long id_acao) {
        this.id_acao = id_acao;
    }

    public int getQuantidade() {
        return quantidade;
    }

    public void setQuantidade(int quantidade) {
        this.quantidade = quantidade;
    }

    public int getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(int tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
