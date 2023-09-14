package com.ovokore.acoesnabolsa;

import android.os.Bundle;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.model.Transacao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;
import com.ovokore.acoesnabolsa.utils.Utils;

import java.util.List;

public class TransacaoListaActivity extends AppCompatActivity {

//    private Button btnTransacaoCadastro;


    private ListView listViewAcoes;
    private List<Transacao> transacoes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao_lista);

//        btnTransacaoCadastro = findViewById(R.id.btnTransacaoCadastro);
//        btnTransacaoCadastro.setOnClickListener(v -> startActivity(new Intent(TransacaoListaActivity.this, TransacaoCadastroActivity.class)));

        listViewAcoes = findViewById(R.id.listViewAcoes);
        listViewAcoes.setOnItemClickListener(
                (parent, view, position, id) -> {
                    Transacao transacao = (Transacao) listViewAcoes.getItemAtPosition(position);
                    Utils.showToast(getApplicationContext(),
                            transacao.getTipoTransacao() + " " + transacao.getQuantidade()
                    );
                });
        popularLista();
    }

    private void popularLista() {
        transacoes = AppDatabase.getDatabase(getApplicationContext()).transacaoDao().getAll();
        TransacaoAdapter transacaoAdapter = new TransacaoAdapter(this, transacoes);
        listViewAcoes.setAdapter(transacaoAdapter);
    }
}
