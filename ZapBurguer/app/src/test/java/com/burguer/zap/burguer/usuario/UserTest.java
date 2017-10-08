package com.burguer.zap.burguer.usuario;

import android.support.annotation.NonNull;

import com.burguer.zap.burguer.rest.base.BaseRetrofit;
import com.burguer.zap.burguer.rest.usuario.UserRest;
import com.burguer.zap.burguer.vo.Usuario;
import com.google.gson.Gson;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Implements;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

import static org.junit.Assert.assertFalse;

/**
 * Created by LucasOrso on 10/5/17.
 */

@RunWith(RobolectricTestRunner.class)
@Implements
public class UserTest {

    @Test
    public void getUsers() throws IOException {
        Retrofit lRetrofit = new BaseRetrofit().buildRetrofit();
        UserRest.Api usuarioRest = lRetrofit.create(UserRest.Api.class);
        Call<List<Usuario>> lCall = usuarioRest.getUsersList();
        lCall.enqueue(new Callback<List<Usuario>>() {
            @Override
            public void onResponse(@NonNull Call<List<Usuario>> call, @NonNull Response<List<Usuario>> response) {
                String lReponseJson = new Gson().toJson(response.body());
                System.out.println(lReponseJson);
                List<Usuario> lList = response.body();
                assert lList != null;
                assertFalse(lList.size() > 0);
            }

            @Override
            public void onFailure(@NonNull Call<List<Usuario>> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
    }
}
