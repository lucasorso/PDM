package com.burguer.zap.burguer.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

import com.burguer.zap.burguer.R;
import com.burguer.zap.burguer.activities.base.BaseActivity;
import com.burguer.zap.burguer.rest.base.BaseRetrofit;
import com.burguer.zap.burguer.rest.usuario.UserRest;
import com.burguer.zap.burguer.rest.usuario.request.LoginRequest;
import com.burguer.zap.burguer.vo.Usuario;
import com.google.gson.Gson;

import br.com.simplepass.loading_button_lib.customViews.CircularProgressButton;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static com.burguer.zap.burguer.util.FieldValidation.isEmailValid;
import static com.burguer.zap.burguer.util.FieldValidation.isPasswordValid;


public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";

    private Call<Usuario> mCAllGetUsers;
    private UserRest.Api mUserRestApi;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        createInterface();
        createViewsListeners();
    }

    @Override
    public void onClick(View aView) {
        int lIdView = aView.getId();
        switch (lIdView) {
            case R.id.register:
                openRegisterActivity();
                break;
            case R.id.button_login:
                attemptLogin();
                break;
        }
    }

    private void openRegisterActivity() {
        Intent lIntent = new Intent(this, RegisterActivity.class);
        lIntent.setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(lIntent);
    }

    private void createCallbackListeners() {
        mCAllGetUsers.enqueue(new Callback<Usuario>() {
            @Override
            public void onResponse(@NonNull Call<Usuario> call, @NonNull Response<Usuario> response) {
                Log.e(TAG, response.message());
                String lReponseJson = new Gson().toJson(response.body());
                showProgress(false);
                // TODO: 10/7/17 Lucas Implementar quando retorno do WS ficar pronto
                doFakeLogin();
            }

            @Override
            public void onFailure(@NonNull Call<Usuario> call, @NonNull Throwable t) {
                Log.e(TAG, t.getMessage());
                showProgress(false);
                // TODO: 10/7/17 Lucas Implementar quando retorno do WS ficar pronto
                doFakeLogin();
            }
        });
    }

    private void doFakeLogin() {
    }

    private void createViewsListeners() {
        findViewById(R.id.email).setOnClickListener(this);
        findViewById(R.id.password).setOnClickListener(this);
        findViewById(R.id.register).setOnClickListener(this);
        findViewById(R.id.button_login).setOnClickListener(this);
    }

    private void createInterface() {
        Retrofit lRetrofit = new BaseRetrofit().buildRetrofit();
        mUserRestApi = lRetrofit.create(UserRest.Api.class);
    }

    private void attemptLogin() {
        EditText lPasswordView = findViewById(R.id.email);
        EditText lEmailView = findViewById(R.id.password);

        lPasswordView.setError(null);
        lEmailView.setError(null);

        String lEmail = lEmailView.getText().toString();
        String lPassword = lPasswordView.getText().toString();

        boolean cancel = false;
        View focusView = null;

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
            makeRequest(lEmail, lPassword);
        }
    }

    private void makeRequest(String aEmail, String aPassword) {
        LoginRequest lRequest = new LoginRequest();
        lRequest.setEmail(aEmail);
        lRequest.setPassword(aPassword);
        doLogin(lRequest);
    }

    private void doLogin(LoginRequest aRequest) {
        showProgress(true);
        mCAllGetUsers = mUserRestApi.doLogin(aRequest);
        createCallbackListeners();
    }


    private void showProgress(boolean aShow) {
        CircularProgressButton lView = findViewById(R.id.button_login);
        if (aShow) {
            lView.startAnimation();
        } else {
            lView.revertAnimation();
        }
    }
}

