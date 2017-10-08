package com.burguer.zap.burguer.activities;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v7.app.ActionBar;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.burguer.zap.burguer.R;
import com.burguer.zap.burguer.activities.base.BaseActivity;
import com.burguer.zap.burguer.rest.base.BaseRetrofit;
import com.burguer.zap.burguer.rest.usuario.UserRest;
import com.burguer.zap.burguer.rest.usuario.request.RegisterRequest;
import com.burguer.zap.burguer.vo.Usuario;
import com.google.gson.Gson;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.burguer.zap.burguer.util.FieldValidation.isEmailValid;
import static com.burguer.zap.burguer.util.FieldValidation.isPasswordValid;

/**
 * Created by LucasOrso on 10/5/17.
 */

public class RegisterActivity extends BaseActivity implements View.OnClickListener {


    private static final String TAG = "RegisterActivity";

    private Call<Usuario> mCAllGetUsers;
    private UserRest.Api mUserRestApi;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        configureActionBar();
        createInterface();
        createViewsListeners();
    }

    private void configureActionBar() {
        ActionBar lSupportActionBar = getSupportActionBar();
        if (lSupportActionBar != null) {
            lSupportActionBar.setDisplayHomeAsUpEnabled(true);
            lSupportActionBar.setTitle(R.string.register);
        }
    }

    private void createInterface() {
        Retrofit lRetrofit = new BaseRetrofit().buildRetrofit();
        mUserRestApi = lRetrofit.create(UserRest.Api.class);
    }

    private void createViewsListeners() {
        findViewById(R.id.button_register).setOnClickListener(this);
    }

    private void attempRegister() {
        EditText lNameView = findViewById(R.id.name);
        EditText lEmailView = findViewById(R.id.email);
        EditText lPasswordView = findViewById(R.id.password);

        lNameView.setError(null);
        lPasswordView.setError(null);
        lEmailView.setError(null);

        String lName = lNameView.getText().toString();
        String lEmail = lEmailView.getText().toString();
        String lPassword = lPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

        if (TextUtils.isEmpty(lName)) {
            lNameView.setError(getString(R.string.error_field_required));
            focusView = lNameView;
            cancel = true;
        }

        if (TextUtils.isEmpty(lPassword)) {
            lPasswordView.setError(getString(R.string.error_field_required));
            focusView = lPasswordView;
            cancel = true;
        } else if (!isPasswordValid(lPassword)) {
            lPasswordView.setError(getString(R.string.error_invalid_password));
            focusView = lPasswordView;
            cancel = true;
        }

        if (TextUtils.isEmpty(lEmail)) {
            lEmailView.setError(getString(R.string.error_field_required));
            focusView = lEmailView;
            cancel = true;
        } else if (isEmailValid(lEmail)) {
            lEmailView.setError(getString(R.string.error_invalid_email));
            focusView = lEmailView;
            cancel = true;
        }

        if (cancel) {
            focusView.requestFocus();
        } else {
            makeRequest(lName, lEmail, lPassword);
        }
    }

    private void makeRequest(String aName, String aEmail, String aPassword) {
        RegisterRequest lRequest = new RegisterRequest();
        lRequest.setNome(aName);
        lRequest.setEmail(aEmail);
        lRequest.setPassword(aPassword);
        doRegister(lRequest);
    }

    private void doRegister(RegisterRequest aRequest) {
        showProgress(true);
        mCAllGetUsers = mUserRestApi.registerUser(aRequest);
        createCallbackListeners();
    }

    private void createCallbackListeners() {
        mCAllGetUsers.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                Log.e(TAG, response.message());
                String lReponseJson = new Gson().toJson(response.body());
                showProgress(false);
                // TODO: 10/7/17 Lucas Implementar quando retorno do WS ficar pronto
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                showProgress(false);
                // TODO: 10/7/17 Lucas Implementar quando retorno do WS ficar pronto
            }
        });
    }

    private void showProgress(boolean aShow) {
        CircularProgressButton lView = findViewById(R.id.button_register);
        if (aShow) {
            lView.startAnimation();
        } else {
            lView.revertAnimation();
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        finish();
        return true;
    }

    @Override
    public void onClick(View aView) {
        int lIdView = aView.getId();
        switch (lIdView) {
            case R.id.button_register:
                attempRegister();
                break;
            case android.R.id.home:
                finish();
                break;
        }
    }
}
