package com.orsomob.lancamento;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.design.widget.TextInputEditText;
import android.support.v7.widget.Toolbar;
import android.transition.TransitionManager;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class LancamentoActivity extends BaseActivity {

    @BindView(R.id.ed_codigo)
     TextInputEditText ed_codigo;

    @BindView(R.id.ed_quantidade)
     TextInputEditText ed_quantidade;

    @BindView(R.id.ed_valor)
     TextInputEditText ed_valor;

    @BindView(R.id.transitions_container)
    ViewGroup mTransitionContainer;

    @BindView(R.id.text)
    TextView mText;

    @BindView(R.id.button)
    Button mButton;

    private Integer mCodigo;
    private Integer mQuantidade;
    private Double mValor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_lancamento);
        ButterKnife.bind(this);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mButton.setOnClickListener(new View.OnClickListener() {
            boolean visible;
            @Override
            public void onClick(View v) {
                TransitionManager.beginDelayedTransition(mTransitionContainer);
                visible = !visible;
                mText.setVisibility(visible ? View.VISIBLE : View.GONE);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_lancamento, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_proximo) {
            if (validarCampos()) {
                Bundle lBundle = new Bundle();
                lBundle.putInt("codigo", mCodigo);
                lBundle.putInt("quantidade", mQuantidade);
                lBundle.putDouble("valor", mValor);
                Intent lIntent = new Intent(this, ConfirmarActivity.class);
                lIntent.putExtras(lBundle);
                startActivity(lIntent);
            } else {
                alertaCamposInvalidos();
            }
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void alertaCamposInvalidos() {
        Snackbar lSnackbar = Snackbar.make(this.getCurrentFocus(), "Campos inválidos", Snackbar.LENGTH_SHORT);
        lSnackbar.show();
    }

    @Override
    protected void onResume() {
        super.onResume();
        limparCampos();
    }

    private void limparCampos() {
        ed_codigo.setText("");
        ed_quantidade.setText("");
        ed_valor.setText("");
    }

    private boolean validarCampos() {
        Boolean lOk;
        if (ed_codigo.getText().toString().isEmpty()) {
            lOk = false;
        } else if (ed_quantidade.getText().toString().isEmpty()) {
            lOk = false;
        } else if (ed_valor.getText().toString().isEmpty()) {
            lOk = false;
        } else {
            mCodigo = Integer.valueOf(ed_codigo.getText().toString());
            mQuantidade = Integer.valueOf(ed_quantidade.getText().toString());
            mValor = Double.valueOf(ed_valor.getText().toString());
            lOk = true;
        }
        return lOk;
    }

    @Override
    public void finish() {
        onLeaveThisActivity();
    }
}
