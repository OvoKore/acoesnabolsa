package com.ovokore.acoesnabolsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class TransacaoListaActivity extends AppCompatActivity {

    private Button btnTransacaoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao_lista);

        btnTransacaoCadastro = findViewById(R.id.btnTransacaoCadastro);

        btnTransacaoCadastro.setOnClickListener(v -> startActivity(new Intent(TransacaoListaActivity.this, TransacaoCadastroActivity.class)));

    }
}
