package com.ovokore.acoesnabolsa.model;

import androidx.room.Entity;
import androidx.room.ForeignKey;
import androidx.room.PrimaryKey;

@Entity(
        tableName = "transacao",
        foreignKeys = @ForeignKey(
                entity = Acao.class,
                parentColumns = "id",
                childColumns = "id_acao",
                onDelete = ForeignKey.CASCADE
        )
)
public class Transacao {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private long id_acao;

    private int quantidade;

    private String tipoTransacao;

    public Transacao(long id_acao, int quantidade, String tipoTransacao) {
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

    public String getTipoTransacao() {
        return tipoTransacao;
    }

    public void setTipoTransacao(String tipoTransacao) {
        this.tipoTransacao = tipoTransacao;
    }
}
