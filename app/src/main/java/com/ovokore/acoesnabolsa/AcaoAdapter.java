package com.ovokore.acoesnabolsa;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.graphics.drawable.DrawableCompat;

import com.ovokore.acoesnabolsa.model.Acao;

import java.util.List;

public class AcaoAdapter extends BaseAdapter {

    private Context context;
    private List<Acao> acoes;

    private static class AcaoHolder {
        public TextView textViewCodigoAcao;
        public TextView textViewNomeEmpresa;
        public TextView textViewTipo;
        public ImageView imageViewFavorita;
    }

    public AcaoAdapter(Context context, List<Acao> acoes) {
        this.context = context;
        this.acoes = acoes;
    }

    @Override
    public int getCount() {
        return acoes.size();
    }

    @Override
    public Object getItem(int i) {
        return acoes.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        AcaoHolder holder;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.list_item_acao, viewGroup, false);

            holder = new AcaoHolder();

            holder.textViewCodigoAcao = view.findViewById(R.id.textViewCodigoAcao);
            holder.textViewNomeEmpresa = view.findViewById(R.id.textViewNomeEmpresa);
            holder.textViewTipo = view.findViewById(R.id.textViewTipo);
            holder.imageViewFavorita = view.findViewById(R.id.imageViewFavorita);

            view.setTag(holder);

        } else {
            holder = (AcaoHolder) view.getTag();
        }

        holder.textViewCodigoAcao.setText(acoes.get(position).getCodigo());
        holder.textViewNomeEmpresa.setText(acoes.get(position).getNomeEmpresa());
        holder.textViewTipo.setText(acoes.get(position).getTipo());
        if (acoes.get(position).isFavorita()) {
            Drawable drawable = holder.imageViewFavorita.getDrawable();
            DrawableCompat.setTint(drawable, Color.parseColor("#FFFF00"));
        }

        return view;
    }
}
