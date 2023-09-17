package com.ovokore.acoesnabolsa.activity;

import static com.ovokore.acoesnabolsa.utils.GlobalValues.UPDATE;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import com.ovokore.acoesnabolsa.R;
import com.ovokore.acoesnabolsa.activity.base.FormBase;
import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;
import com.ovokore.acoesnabolsa.utils.Utils;

public class AcaoForm extends FormBase<Acao> {

    private EditText editTextCodigoAcao;
    private EditText editTextNomeEmpresa;
    private CheckBox checkBoxFavorita;
    private Spinner spinnerTipo;
    private EditText editTextDescricaoAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        editTextCodigoAcao = findViewById(R.id.editTextCodigoAcao);
        editTextNomeEmpresa = findViewById(R.id.editTextNomeEmpresa);
        checkBoxFavorita = findViewById(R.id.checkBoxFavorita);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        editTextDescricaoAcao = findViewById(R.id.editTextDescricaoAcao);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_acoes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        Intent intent = getIntent();
        if (intent.hasExtra(UPDATE)) {
            value = (Acao) intent.getSerializableExtra(UPDATE);
            editTextCodigoAcao.setText(value.getCodigo());
            editTextNomeEmpresa.setText(value.getNomeEmpresa());
            checkBoxFavorita.setChecked(value.isFavorita());
            spinnerTipo.setSelection(adapter.getPosition(value.getTipo()));
            editTextDescricaoAcao.setText(value.getDescricao());
        } else {
            value = new Acao();
        }
    }

    protected void clear() {
        editTextCodigoAcao.setText(new String());
        editTextNomeEmpresa.setText(new String());
        editTextDescricaoAcao.setText(new String());
        checkBoxFavorita.setChecked(false);
    }


    protected void delete() {
        if (value.getId() > 0) {
            Utils.showDeleteConfirmationDialog(this, value);
        } else {
            Utils.showToast(this, getString(R.string.NotDelete));
        }
    }

    protected void save() {

        String codigo = editTextCodigoAcao.getText().toString().trim();
        String nomeEmpresa = editTextNomeEmpresa.getText().toString().trim();
        String tipo = spinnerTipo.getSelectedItem().toString();
        boolean favorita = checkBoxFavorita.isChecked();
        String descricao = editTextDescricaoAcao.getText().toString().trim();

        if (codigo.isEmpty() || nomeEmpresa.isEmpty()) {
            Utils.showToast(this, getString(R.string.PreenchaCampos));
        } else {

            if (value.getId() > 0) {
                value.setCodigo(codigo);
                value.setNomeEmpresa(nomeEmpresa);
                value.setTipo(tipo);
                value.setFavorita(favorita);
                value.setDescricao(descricao);
            } else {
                value = new Acao(codigo, nomeEmpresa, tipo, favorita, descricao);
            }

            try {
                if (value.getId() > 0) {
                    AppDatabase.getDatabase(this).acaoDao().update(value);
                } else {
                    AppDatabase.getDatabase(this).acaoDao().insert(value);
                }
                Utils.showToast(this, getString(R.string.CadastrarOk));
                finish();
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showAlertDialog(this, e.getMessage());
            }
        }
    }
}