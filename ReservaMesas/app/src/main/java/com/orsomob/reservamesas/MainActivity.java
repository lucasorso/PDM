package com.orsomob.reservamesas;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private EditText mNumeroMesa;
    private Button mLiberarMesa;
    private Button mSalvarMesa;
    private Adapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        recuperResources();
        configuraRecyclerView();
        acoesComponentes();
    }

    private void configuraRecyclerView() {
        List<Mesa> lMesas = criaListaMesa();
        mAdapter = new Adapter(this, lMesas);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    private List<Mesa> criaListaMesa() {
        SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        List<Mesa> lMesas = new ArrayList<>();
        for (int i = 1; i < 10; i++) {
            lMesas.add(new Mesa(i, lPreferences.getBoolean("reservada_" + i, false)));
        }
        return lMesas;
    }

    private void acoesComponentes() {

        mSalvarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (Mesa lMesa : mAdapter.getMesas()) {
                    SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
                    SharedPreferences.Editor lEditor = lPreferences.edit();
                    lEditor.putBoolean("reservada_" + lMesa.getNumeromesa(), lMesa.isReservada());
                    lEditor.apply();
                }
            }
        });

        mLiberarMesa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int lNumeroMesa = Integer.parseInt(mNumeroMesa.getText().toString());
                lNumeroMesa--;
                Mesa lMesa = mAdapter.getMesas().get(lNumeroMesa);
                lMesa.setReservada(false);
                mAdapter.atualizaMesa(lMesa, lNumeroMesa);
            }
        });
    }

    private void recuperResources() {
        mRecyclerView = (RecyclerView) findViewById(R.id.recycler_view_mesa);
        mNumeroMesa = (EditText) findViewById(R.id.edit_text_numero_mesa);
        mLiberarMesa = (Button) findViewById(R.id.btn_liberar);
        mSalvarMesa = (Button) findViewById(R.id.btn_salvar);
    }
}
