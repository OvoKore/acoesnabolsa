package com.ovokore.acoesnabolsa.activity.base;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.ACTIVITY_NAME_LIST;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.ADAPTER_PACKAGE;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.FORM_PACKAGE;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.GET_ALL;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.ID;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.LAYOUT;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.LIST_VIEW_NAME;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.UPDATE;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ListAdapter;
import android.widget.ListView;

import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.R;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;

import java.io.Serializable;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.ParameterizedType;
import java.util.List;

public abstract class ListBase<T> extends AppCompatActivity {

    protected ListView listView;
    protected List<T> listValue;
    protected int selectedPosition = -1;

    private Class<T> getCurrentClass() {
        return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    private Intent goToForm() {
        Class<T> cls = getCurrentClass();
        String clsName = cls.getSimpleName();
        String nameForm = String.format(FORM_PACKAGE, this.getPackageName(), clsName);
        Class<?> classForm;
        try {
            classForm = Class.forName(nameForm);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        return new Intent(this, classForm);
    }

    private final ActivityResultLauncher<Intent> activityLauncher = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(),
            result -> {
                fillList();
            }
    );

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Class<T> cls = getCurrentClass();
        String clsName = cls.getSimpleName();
        String activity_name_list = String.format(ACTIVITY_NAME_LIST, clsName).toLowerCase();
        String listViewName = String.format(LIST_VIEW_NAME, clsName);

        int contentViewId = getResources().getIdentifier(activity_name_list, LAYOUT, getPackageName());
        int listViewId = getResources().getIdentifier(listViewName, ID, getPackageName());

        setContentView(contentViewId);
        listView = findViewById(listViewId);

        listView.setOnItemClickListener(
                (parent, view, position, id) -> {
                    selectedPosition = position;
                    update();
                });
        listView.setOnItemLongClickListener(
                (parent, view, position, id) -> {
                    selectedPosition = position;
                    update();
                    return true;
                });

        fillList();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lista, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.menuItemAdd) {
            activityLauncher.launch(goToForm());
            return true;
        } else {
            return super.onOptionsItemSelected(item);
        }
    }

    private void fillList() {
        Class<T> cls = getCurrentClass();
        String clsName = cls.getSimpleName();

        listValue = (List<T>) AppDatabase.getDatabase(this).callDynamicMethod(cls, GET_ALL);

        String adapterClassName = String.format(ADAPTER_PACKAGE, this.getPackageName(), clsName);

        Class<?> adapterClass = null;
        try {
            adapterClass = Class.forName(adapterClassName);
        } catch (ClassNotFoundException e) {
            throw new RuntimeException(e);
        }
        Constructor<?> constructorAdapter = null;
        try {
            constructorAdapter = adapterClass.getDeclaredConstructor(Context.class, List.class);
        } catch (NoSuchMethodException e) {
            throw new RuntimeException(e);
        }
        Object adapter = null;
        try {
            adapter = constructorAdapter.newInstance(this, listValue);
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        } catch (InstantiationException e) {
            throw new RuntimeException(e);
        } catch (InvocationTargetException e) {
            throw new RuntimeException(e);
        }

        listView.setAdapter((ListAdapter) adapter);
    }

    private void update() {
        T value = listValue.get(selectedPosition);
        Intent intent = goToForm();
        intent.putExtra(UPDATE, (Serializable) value);
        activityLauncher.launch(intent);
    }
}
