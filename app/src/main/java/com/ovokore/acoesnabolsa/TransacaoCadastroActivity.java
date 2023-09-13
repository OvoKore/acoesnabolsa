package com.ovokore.acoesnabolsa;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.model.Transacao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;
import com.ovokore.acoesnabolsa.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TransacaoCadastroActivity extends AppCompatActivity {

    private Spinner spinnerAcao;
    private EditText editTextQuantidade;
    private RadioGroup radioGroupTipoTransacao;

    private Button btnLimparTransacao;
    private Button btnCadastrarTransacao;

    private List<Acao> acaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_transacao_cadastro);

        spinnerAcao = findViewById(R.id.spinnerAcao);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        radioGroupTipoTransacao = findViewById(R.id.radioGroupTipoTransacao);
        btnLimparTransacao = findViewById(R.id.btnLimparTransacao);
        btnCadastrarTransacao = findViewById(R.id.btnCadastrarTransacao);

        acaoList = obterAcoesDisponiveis();

        List<String> codigos = new ArrayList<>();
        for (Acao acao : acaoList) {
            codigos.add(acao.getCodigo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, codigos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAcao.setAdapter(adapter);


        btnLimparTransacao.setOnClickListener(v -> limparTransacao());
        btnCadastrarTransacao.setOnClickListener(v -> cadastrarTransacao());

    }

    private List<Acao> obterAcoesDisponiveis() {
        List<Acao> acoesDisponiveis = AppDatabase.getDatabase(this).acaoDao().getAll();
        return acoesDisponiveis;
    }

    private void limparTransacao() {
        editTextQuantidade.setText("0");
    }

    private void cadastrarTransacao() {
        String codigoSelecionado = spinnerAcao.getSelectedItem().toString();

        Acao acaoSelecionada = null;
        for (Acao acao : acaoList) {
            if (acao.getCodigo().equals(codigoSelecionado)) {
                acaoSelecionada = acao;
            }
        }

        int quantidade = Integer.parseInt(editTextQuantidade.getText().toString());
        String tipoTransacao = obterTipoTransacaoSelecionado();

        if (acaoSelecionada == null || quantidade <= 0 || tipoTransacao.isEmpty()) {
            Utils.showToast(this, getString(R.string.PreenchaCampos));
        } else {
            Transacao transacao = new Transacao(acaoSelecionada.getId(), quantidade, tipoTransacao);
            try {
                AppDatabase.getDatabase(this).transacaoDao().insert(transacao);
                Utils.showToast(this, getString(R.string.CadastrarOk));
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showAlertDialog(this, e.getMessage());
            }
        }
    }

    private String obterTipoTransacaoSelecionado() {
        int radioButtonId = radioGroupTipoTransacao.getCheckedRadioButtonId();
        if (radioButtonId == R.id.radioButtonCompra) {
            return "Compra";
        } else if (radioButtonId == R.id.radioButtonVenda) {
            return "Venda";
        }
        return "";
    }
}
