package com.ovokore.acoesnabolsa.activity.base;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.ACTIVITY_NAME_FORM;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.BTN_SAVE;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.ID;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.LAYOUT;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.R;

import java.lang.reflect.ParameterizedType;

public abstract class FormBase<T> extends AppCompatActivity {

    protected T value;

    protected Button btnSave;

    private Class<T> getCurrentClassName() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<T> cls = getCurrentClassName();
        String clsName = cls.getSimpleName();
        String activity_name_list = String.format(ACTIVITY_NAME_FORM, clsName).toLowerCase();
        String btnSaveName = BTN_SAVE + clsName;

        int contentViewId = getResources().getIdentifier(activity_name_list, LAYOUT, getPackageName());
        int btnSaveId = getResources().getIdentifier(btnSaveName, ID, getPackageName());

        setContentView(contentViewId);

        btnSave = findViewById(btnSaveId);
        btnSave.setOnClickListener(v -> save());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuItemClear) {
            clear();
            return true;
        } else if (id == R.id.menuItemDelete) {
            delete();
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_form, menu);
        return true;
    }

    protected abstract void clear();

    protected abstract void delete();

    protected abstract void save();
}
