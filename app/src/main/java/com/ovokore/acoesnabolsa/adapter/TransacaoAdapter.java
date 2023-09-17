package com.ovokore.acoesnabolsa.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ovokore.acoesnabolsa.R;
import com.ovokore.acoesnabolsa.adapter.base.AdapterBase;
import com.ovokore.acoesnabolsa.enumerable.TipoTransacaoEnum;
import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.model.Transacao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;

import java.util.List;

public class TransacaoAdapter extends AdapterBase<Transacao> {

    public TransacaoAdapter(Context context, List<?> valueList) {
        super(context, valueList);
    }

    private static class Holder {
        public TextView textViewCodigo;
        public TextView textViewQuantidade;
        public TextView textViewTipoTransacao;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Holder holder;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_transacao, viewGroup, false);

            holder = new Holder();

            holder.textViewCodigo = view.findViewById(R.id.textViewCodigo);
            holder.textViewQuantidade = view.findViewById(R.id.textViewQuantidade);
            holder.textViewTipoTransacao = view.findViewById(R.id.textViewTipoTransacao);

            view.setTag(holder);

        } else {
            holder = (TransacaoAdapter.Holder) view.getTag();
        }

        Acao acao = AppDatabase.getDatabase(view.getContext()).acaoDao().getById(valueList.get(position).getId_acao());
        String tipoTransacao = TipoTransacaoEnum.getById(valueList.get(position).getTipoTransacao()).getDescricao(view.getContext());

        holder.textViewCodigo.setText(acao.getCodigo());
        holder.textViewQuantidade.setText(Integer.toString(valueList.get(position).getQuantidade()));
        holder.textViewTipoTransacao.setText(tipoTransacao);

        return view;
    }
}
