package com.ovokore.acoesnabolsa.activity;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.PREFERENCE_STAR;
import static com.ovokore.acoesnabolsa.utils.GlobalValues.SHARED_PREFERENCES;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.Switch;

import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.R;

public class Main extends AppCompatActivity {

    private Button btnAcao;
    private Button btnTransacao;
    private Button btnSobre;

    private MenuItem menuItemStar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcao = findViewById(R.id.btnAcao);
        btnTransacao = findViewById(R.id.btnTransacao);
        btnSobre = findViewById(R.id.btnSobre);

        btnAcao.setOnClickListener(v -> startActivity(new Intent(Main.this, AcaoList.class)));
        btnTransacao.setOnClickListener(v -> startActivity(new Intent(Main.this, TransacaoList.class)));
        btnSobre.setOnClickListener(v -> startActivity(new Intent(Main.this, Sobre.class)));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);

        SharedPreferences sp = getSharedPreferences(SHARED_PREFERENCES, MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        int v = sp.getInt(PREFERENCE_STAR, 0);
        editor.putInt(PREFERENCE_STAR, v);
        editor.apply();

        MenuItem menuItem = menu.findItem(R.id.menuItemStar);
        View actionView = menuItem.getActionView();

        Switch switchStar = actionView.findViewById(R.id.menuSwitchStar);
        if (v == 0) {
            switchStar.setChecked(false);
        } else if (v == 1) {
            switchStar.setChecked(true);
        }

        switchStar.setOnCheckedChangeListener((buttonView, isChecked) -> {
            if (isChecked) {
                editor.putInt(PREFERENCE_STAR, 1);
                editor.apply();
            } else {
                editor.putInt(PREFERENCE_STAR, 0);
                editor.apply();
            }
        });

        return true;
    }

}