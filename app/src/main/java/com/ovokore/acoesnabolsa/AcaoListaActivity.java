package com.ovokore.acoesnabolsa;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;
import com.ovokore.acoesnabolsa.utils.Utils;

import java.util.List;

    public class AcaoListaActivity extends AppCompatActivity {

//    private Button btnAcaoCadastro;

    private ListView listViewAcoes;
    private List<Acao> acoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_lista);

//        btnAcaoCadastro = findViewById(R.id.btnAcaoCadastro);
//        btnAcaoCadastro.setOnClickListener(v -> startActivity(new Intent(AcaoListaActivity.this, AcaoCadastroActivity.class)));

        listViewAcoes = findViewById(R.id.listViewAcoes);
        listViewAcoes.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Acao acao = (Acao) listViewAcoes.getItemAtPosition(position);
                    Utils.showToast(getApplicationContext(), acao.getCodigo());
                });
        popularLista();
    }

    private void popularLista() {
        acoes = AppDatabase.getDatabase(getApplicationContext()).acaoDao().getAll();
        AcaoAdapter acaoAdapter = new AcaoAdapter(this, acoes);
        listViewAcoes.setAdapter(acaoAdapter);
    }
}
