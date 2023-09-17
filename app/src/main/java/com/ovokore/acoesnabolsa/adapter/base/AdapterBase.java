package com.ovokore.acoesnabolsa.adapter.base;

import android.content.Context;
import android.widget.BaseAdapter;

import java.util.List;

public abstract class AdapterBase<T> extends BaseAdapter {
    protected Context context;
    protected List<T> valueList;

    @Override
    public int getCount() {
        return valueList.size();
    }

    @Override
    public Object getItem(int i) {
        return valueList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    public AdapterBase(Context context, List<?> valueList) {
        this.context = context;
        this.valueList = (List<T>) valueList;
    }
}
