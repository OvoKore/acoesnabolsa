package com.ovokore.acoesnabolsa.adapter;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.PREFERENCE_STAR;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.SHARED_PREFERENCES;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.core.content.ContextCompat;

import com.ovokore.acoesnabolsa.R;
import com.ovokore.acoesnabolsa.adapter.base.AdapterBase;
import com.ovokore.acoesnabolsa.model.Acao;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class AcaoAdapter extends AdapterBase<Acao> {

    public AcaoAdapter(Context context, List<?> valueList) {
        super(context, valueList);

        SharedPreferences sp = this.context.getSharedPreferences(SHARED_PREFERENCES, this.context.MODE_PRIVATE);
        int v = sp.getInt(PREFERENCE_STAR, 0);
        if (v == 1) {
            Comparator<Acao> comparator = new Comparator<Acao>() {
                @Override
                public int compare(Acao acao1, Acao acao2) {
                    return Boolean.compare(acao2.isFavorita(), acao1.isFavorita());
                }
            };
            Collections.sort((List<Acao>) valueList, comparator);
        }
    }


    private static class Holder {
        public TextView textViewCodigo;
        public TextView textViewNomeEmpresa;
        public TextView textViewTipo;
        public ImageView imageViewFavorita;
    }

    @Override
    public View getView(int position, View view, ViewGroup viewGroup) {

        Holder holder;

        if (view == null) {

            LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(R.layout.adapter_acao, viewGroup, false);

            holder = new Holder();

            holder.textViewCodigo = view.findViewById(R.id.textViewCodigo);
            holder.textViewNomeEmpresa = view.findViewById(R.id.textViewNomeEmpresa);
            holder.textViewTipo = view.findViewById(R.id.textViewTipo);
            holder.imageViewFavorita = view.findViewById(R.id.imageViewFavorita);

            view.setTag(holder);

        } else {
            holder = (Holder) view.getTag();
        }

        holder.textViewCodigo.setText(valueList.get(position).getCodigo());
        holder.textViewNomeEmpresa.setText(valueList.get(position).getNomeEmpresa());
        holder.textViewTipo.setText(valueList.get(position).getTipo());

        Drawable drawable;
        if (valueList.get(position).isFavorita()) {
            drawable = ContextCompat.getDrawable(context, android.R.drawable.btn_star_big_on);
        } else {
            drawable = ContextCompat.getDrawable(context, android.R.drawable.btn_star);
        }
        holder.imageViewFavorita.setImageDrawable(drawable);

        return view;
    }
}
