package com.ovokore.acoesnabolsa.model;

import androidx.room.Entity;
import androidx.room.Index;
import androidx.room.PrimaryKey;

@Entity(tableName = "acao",
        indices = {@Index(value = {"codigo"}, unique = true)}
)
public class Acao {

    @PrimaryKey(autoGenerate = true)
    private long id;

    private String codigo;

    private String nomeEmpresa;

    private String tipo;

    private boolean favorita;

    private String descricao;

    public Acao(String codigo, String nomeEmpresa, String tipo, boolean favorita, String descricao) {
        this.codigo = codigo;
        this.nomeEmpresa = nomeEmpresa;
        this.tipo = tipo;
        this.favorita = favorita;
        this.descricao = descricao;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCodigo() {
        return codigo;
    }

    public void setCodigo(String codigo) {
        this.codigo = codigo;
    }

    public String getNomeEmpresa() {
        return nomeEmpresa;
    }

    public void setNomeEmpresa(String nomeEmpresa) {
        this.nomeEmpresa = nomeEmpresa;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public boolean isFavorita() {
        return favorita;
    }

    public void setFavorita(boolean favorita) {
        this.favorita = favorita;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }
}

