package com.ovokore.acoesnabolsa.activity;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.UPDATE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioGroup;
import android.widget.Spinner;

import com.ovokore.acoesnabolsa.R;
import com.ovokore.acoesnabolsa.activity.base.FormBase;
import com.ovokore.acoesnabolsa.enumerable.TipoTransacaoEnum;
import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.model.Transacao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;
import com.ovokore.acoesnabolsa.utils.Utils;

import java.util.ArrayList;
import java.util.List;

public class TransacaoForm extends FormBase<Transacao> {

    private Spinner spinnerAcao;
    private EditText editTextQuantidade;
    private RadioGroup radioGroupTipoTransacao;

    private List<Acao> acaoList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        spinnerAcao = findViewById(R.id.spinnerAcao);
        editTextQuantidade = findViewById(R.id.editTextQuantidade);
        radioGroupTipoTransacao = findViewById(R.id.radioGroupTipoTransacao);

        acaoList = AppDatabase.getDatabase(this).acaoDao().getAll();

        List<String> codigos = new ArrayList<>();
        for (Acao acao : acaoList) {
            codigos.add(acao.getCodigo());
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, codigos);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerAcao.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.hasExtra(UPDATE)) {
            value = (Transacao) intent.getSerializableExtra(UPDATE);

            String quantidade = String.valueOf(value.getQuantidade());
            Acao acaoSelecionada = AppDatabase.getDatabase(this).acaoDao().getById(value.getId_acao());
            int spinnerIndex = codigos.indexOf(acaoSelecionada.getCodigo());
            int radioGroupId = TipoTransacaoEnum.getById(value.getTipoTransacao()).getRadioButtonId();

            editTextQuantidade.setText(quantidade);
            spinnerAcao.setSelection(spinnerIndex);
            radioGroupTipoTransacao.check(radioGroupId);
        }
        else {
            value = new Transacao();
        }
    }

    protected void clear() {
        editTextQuantidade.setText(new String());
        radioGroupTipoTransacao.clearCheck();
    }

    @Override
    protected void delete() {
        if (value.getId() > 0) {
            Utils.showDeleteConfirmationDialog(this, value);
        } else {
            Utils.showToast(this, getString(R.string.NotDelete));
        }
    }

    protected void save() {
        String codigoSelecionado = spinnerAcao.getSelectedItem().toString();

        Acao acaoSelecionada = null;
        for (Acao acao : acaoList) {
            if (acao.getCodigo().equals(codigoSelecionado)) {
                acaoSelecionada = acao;
                break;
            }
        }

        int quantidade = Integer.parseInt(editTextQuantidade.getText().toString());
        int tipoTransacao = obterTipoTransacaoSelecionado();

        if (acaoSelecionada == null || quantidade <= 0 || tipoTransacao <= 0) {
            Utils.showToast(this, getString(R.string.PreenchaCampos));
        } else {
            if (value.getId() > 0) {
                value.setId_acao(acaoSelecionada.getId());
                value.setQuantidade(quantidade);
                value.setTipoTransacao(tipoTransacao);
            }
            else {
                value = new Transacao(acaoSelecionada.getId(), quantidade, tipoTransacao);
            }

            try {
                AppDatabase.getDatabase(this).transacaoDao().insert(value);
                Utils.showToast(this, getString(R.string.CadastrarOk));
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showAlertDialog(this, e.getMessage());
            }
            finish();
        }
    }

    private int obterTipoTransacaoSelecionado() {
        int radioButtonId = radioGroupTipoTransacao.getCheckedRadioButtonId();
        if (radioButtonId == TipoTransacaoEnum.COMPRA.getRadioButtonId()) {
            return TipoTransacaoEnum.COMPRA.getId();
        }
        else if (radioButtonId == TipoTransacaoEnum.VENDA.getRadioButtonId()){
            return TipoTransacaoEnum.VENDA.getId();
        }
        return 0;
    }
}
