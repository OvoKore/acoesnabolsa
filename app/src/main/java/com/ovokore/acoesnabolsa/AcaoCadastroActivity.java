package com.ovokore.acoesnabolsa;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;

import com.ovokore.acoesnabolsa.model.Acao;
import com.ovokore.acoesnabolsa.persistence.AppDatabase;
import com.ovokore.acoesnabolsa.utils.Utils;

public class AcaoCadastroActivity extends AppCompatActivity {

    private EditText editTextCodigoAcao;
    private EditText editTextNomeEmpresa;
    private CheckBox checkBoxFavorita;
    private Spinner spinnerTipo;
    private EditText editTextDescricaoAcao;

    private Button btnLimparAcao;
    private Button btnCadastrarAcao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_acao_cadastro);

        editTextCodigoAcao = findViewById(R.id.editTextCodigoAcao);
        editTextNomeEmpresa = findViewById(R.id.editTextNomeEmpresa);
        checkBoxFavorita = findViewById(R.id.checkBoxFavorita);
        spinnerTipo = findViewById(R.id.spinnerTipo);
        editTextDescricaoAcao = findViewById(R.id.editTextDescricaoAcao);
        btnLimparAcao = findViewById(R.id.btnLimparAcao);
        btnCadastrarAcao = findViewById(R.id.btnCadastrarAcao);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(
                this,
                R.array.tipos_acoes,
                android.R.layout.simple_spinner_item
        );
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTipo.setAdapter(adapter);

        btnLimparAcao.setOnClickListener(v -> limparAcao());
        btnCadastrarAcao.setOnClickListener(v -> cadastrarAcao());
    }

    private void limparAcao() {
        editTextCodigoAcao.setText("");
        editTextNomeEmpresa.setText("");
        editTextDescricaoAcao.setText("");
        checkBoxFavorita.setChecked(false);
    }

    private void cadastrarAcao() {

        String codigoAcao = editTextCodigoAcao.getText().toString().trim();
        String nomeEmpresa = editTextNomeEmpresa.getText().toString().trim();
        String tipoAcao = spinnerTipo.getSelectedItem().toString();
        boolean favorita = checkBoxFavorita.isChecked();
        String descricaoAcao = editTextDescricaoAcao.getText().toString().trim();

        if (codigoAcao.isEmpty() || nomeEmpresa.isEmpty()) {
            Utils.showToast(this, getString(R.string.PreenchaCampos));
        } else {
            Acao acao = new Acao(codigoAcao, nomeEmpresa, tipoAcao, favorita, descricaoAcao);
            try {
                AppDatabase.getDatabase(this).acaoDao().insert(acao);
                Utils.showToast(this, getString(R.string.CadastrarOk));
            } catch (Exception e) {
                e.printStackTrace();
                Utils.showAlertDialog(this, e.getMessage());
            }
        }
    }
}