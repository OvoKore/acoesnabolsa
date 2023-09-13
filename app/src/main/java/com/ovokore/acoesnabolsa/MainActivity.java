package com.ovokore.acoesnabolsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    private Button btnAcao;
    private Button btnTransacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnAcao = findViewById(R.id.btnAcao);
        btnTransacao = findViewById(R.id.btnTransacao);

        btnAcao.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, AcaoListaActivity.class)));
        btnTransacao.setOnClickListener(v -> startActivity(new Intent(MainActivity.this, TransacaoListaActivity.class)));
    }
}