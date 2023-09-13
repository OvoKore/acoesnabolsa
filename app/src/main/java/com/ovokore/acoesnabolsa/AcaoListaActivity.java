package com.ovokore.acoesnabolsa;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class AcaoListaActivity extends AppCompatActivity {

    private Button btnAcaoCadastro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_lista);

        btnAcaoCadastro = findViewById(R.id.btnAcaoCadastro);

        btnAcaoCadastro.setOnClickListener(v -> startActivity(new Intent(AcaoListaActivity.this, AcaoCadastroActivity.class)));

    }
}
