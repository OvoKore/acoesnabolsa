package com.ovokore.acoesnabolsa.enumerable;

import android.content.Context;

import androidx.annotation.StringRes;

import com.ovokore.acoesnabolsa.R;

public enum TipoTransacaoEnum {
    COMPRA(1, R.string.Compra, R.id.radioButtonCompra),
    VENDA(2, R.string.Venda, R.id.radioButtonVenda);

    private final int id;
    private final int descricaoResId;
    private final int radioButtonId;

    TipoTransacaoEnum(int id, @StringRes int descricaoResId, int radioButtonId) {
        this.id = id;
        this.descricaoResId = descricaoResId;
        this.radioButtonId = radioButtonId;
    }

    public int getId() {
        return id;
    }

    public static TipoTransacaoEnum getById(int id) {
        for (TipoTransacaoEnum tipo : values()) {
            if (tipo.id == id) {
                return tipo;
            }
        }
        throw new IllegalArgumentException("No enum constant with id " + id);
    }

    public String getDescricao(Context context) {
        return context.getString(descricaoResId);
    }

    public int getRadioButtonId() {
        return radioButtonId;
    }
}

