package com.orsomob.apppesquisa;

import android.Manifest;
import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.Map;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private static final int WRITE_EXTERNAL_STORAGE_PERMISSION = 10;

    private Button mBtnRuim;
    private Button mBtnBom;
    private Button mBtnResultado;
    private Button mBtnIniciaPesquisa;
    private Button mBtnExportar;

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        switch (requestCode) {
            case WRITE_EXTERNAL_STORAGE_PERMISSION: {
                if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                    exportarParaSD();
                } else {
                    Toast.makeText(this, "Permissão negada !", Toast.LENGTH_SHORT).show();
                }
                return;
            }
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init() {
        initComponentes();
        acoesBotoes();
    }

    private void acoesBotoes() {
        mBtnRuim.setOnClickListener(this);
        mBtnBom.setOnClickListener(this);
        mBtnResultado.setOnClickListener(this);
        mBtnIniciaPesquisa.setOnClickListener(this);
        mBtnExportar.setOnClickListener(this);
    }

    private void initComponentes() {
        mBtnRuim = (Button) findViewById(R.id.btn_ruim);
        mBtnBom = (Button) findViewById(R.id.btn_bom);
        mBtnResultado = (Button) findViewById(R.id.btn_resultado);
        mBtnIniciaPesquisa = (Button) findViewById(R.id.btn_inicia_pesquisa);
        mBtnExportar = (Button) findViewById(R.id.btn_exportar);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_ruim:
                salvarResposta(R.id.btn_ruim);
                break;
            case R.id.btn_bom:
                salvarResposta(R.id.btn_bom);
                break;
            case R.id.btn_resultado:
                mostrarPesquisa();
                break;
            case R.id.btn_inicia_pesquisa:
                limparShared();
                break;
            case R.id.btn_exportar:
                exportarParaSD();
                break;
        }
    }

    private void exportarParaSD() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) == PackageManager.PERMISSION_GRANTED) {
            SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
            File myPath = new File(Environment.getExternalStorageDirectory().toString());
            File myFile = new File(myPath, "Pesquisa_SharedPreferences.txt");
            try {
                FileWriter fw = new FileWriter(myFile);
                PrintWriter pw = new PrintWriter(fw);
                Map<String, ?> prefsMap = lPreferences.getAll();
                for (Map.Entry<String, ?> entry : prefsMap.entrySet()) {
                    pw.println(entry.getKey() + ": " + entry.getValue().toString());
                }
                pw.close();
                fw.close();
            } catch (Exception e) {
                e.printStackTrace();
                Log.wtf(getClass().getName(), e.toString());
            }
        } else {
            ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, WRITE_EXTERNAL_STORAGE_PERMISSION);
        }
    }

    private void salvarResposta(int aIdButtons) {
        SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        SharedPreferences.Editor lEditor = lPreferences.edit();
        switch (aIdButtons) {
            case R.id.btn_ruim:
                lEditor.putInt("quantidade_ruim", (lPreferences.getInt("quantidade_ruim", 0) + 1));
                break;
            case R.id.btn_bom:
                lEditor.putInt("quantidade_bom", (lPreferences.getInt("quantidade_bom", 0) + 1));
                break;
        }
        lEditor.apply();
    }

    private void mostrarPesquisa() {
        SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        String lMensagem = String.format(getResources().getString(R.string.quantidade_pesquisas), lPreferences.getInt("quantidade_ruim", 0), lPreferences.getInt("quantidade_bom", 0));
        AlertDialog alertDialog = new AlertDialog.Builder(MainActivity.this).create();
        alertDialog.setTitle(R.string.resulta_pesquisa);
        alertDialog.setMessage(lMensagem);
        alertDialog.setButton(AlertDialog.BUTTON_NEUTRAL, "OK",
                new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                });
        alertDialog.show();
    }

    private void limparShared() {
        SharedPreferences lPreferences = getSharedPreferences(BuildConfig.APPLICATION_ID, MODE_PRIVATE);
        SharedPreferences.Editor lEditor = lPreferences.edit();
        lEditor.clear();
        lEditor.apply();
    }
}
