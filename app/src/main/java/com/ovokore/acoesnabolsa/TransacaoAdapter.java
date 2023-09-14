package com.ovokore.acoesnabolsa;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.model.Transacao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;

import java.util.List;

public class TransacaoAdapter extends BaseAdapter {

    private Context context;
    private List<Transacao> transacaos;

    private static class TransacaoHolder {
        public TextView textViewCodigo;
        public TextView textViewQuantidade;
        public TextView textViewTipoTransacao;
    }

    public TransacaoAdapter(Context context, List<Transacao> transacaos) {
        this.context = context;
        this.transacaos = transacaos;
    }

    @Override
    public int getCount() {
        return transacaos.size();
    }

    @Override
    public Object getItem(int i) {
        return transacaos.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        TransacaoHolder holder;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_transacao, viewGroup, false);

            holder = new TransacaoHolder();

            holder.textViewCodigo = view.findViewById(R.id.textViewCodigo);
            holder.textViewQuantidade = view.findViewById(R.id.textViewQuantidade);
            holder.textViewTipoTransacao = view.findViewById(R.id.textViewTipoTransacao);

            view.setTag(holder);

        } else {
            holder = (TransacaoAdapter.TransacaoHolder) view.getTag();
        }

        Acao acao = AppDatabase.getDatabase(view.getContext()).acaoDao().getById(transacaos.get(position).getId_acao());

        holder.textViewCodigo.setText(acao.getCodigo());
        holder.textViewQuantidade.setText(Integer.toString(transacaos.get(position).getQuantidade()));
        holder.textViewTipoTransacao.setText(transacaos.get(position).getTipoTransacao());

        return view;
    }
}
