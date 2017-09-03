package com.orsomob.lancamento;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;

import java.text.NumberFormat;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by LucasOrso on 8/17/17.
 */

public class ConfirmarActivity extends BaseActivity {

    @BindView(R.id.tx_codigo)
    TextView tx_codigo;

    @BindView(R.id.tx_quantidade)
    TextView tx_quantidade;

    @BindView(R.id.tx_valor)
    TextView tx_valor;

    private Integer mCodigo;
    private Integer mQuatidade;
    private Double mValor;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_confirmar);
        ButterKnife.bind(this);

        setTitle(R.string.confirmar);

        Bundle lBundle = getIntent().getExtras();
        getValores(lBundle);
        setValores();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_confirmar, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.action_confirmar) {
            salvarNoSharedPreferences();
            this.finishAfterTransition();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void salvarNoSharedPreferences() {
        SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        SharedPreferences.Editor lEditor = lPreferences.edit();
        lEditor.putInt("codigo", mCodigo);
        lEditor.putInt("quantidade", mQuatidade);
        lEditor.putFloat("valor", mValor.floatValue());
        lEditor.apply();
    }


    private void setValores() {
        tx_codigo.setText(String.valueOf(mCodigo));
        tx_quantidade.setText(String.valueOf(mQuatidade));
        tx_valor.setText(NumberFormat.getNumberInstance().format(mValor));
    }

    private void getValores(Bundle aBundle) {
        if (aBundle.containsKey("codigo") && aBundle.containsKey("quantidade") && aBundle.containsKey("valor")) {
            mCodigo = aBundle.getInt("codigo");
            mQuatidade = aBundle.getInt("quantidade");
            mValor = aBundle.getDouble("valor");
        } else {
            getValoreSharePreferences();
        }
    }

    public void getValoreSharePreferences() {
        SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        lPreferences.getInt("codigo", -1);
        lPreferences.getInt("quantidade", -1);
        lPreferences.getFloat("valor", -1);
    }
}
