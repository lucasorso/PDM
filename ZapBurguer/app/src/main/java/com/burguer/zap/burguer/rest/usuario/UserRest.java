package com.burguer.zap.burguer.rest.usuario;

import com.burguer.zap.burguer.rest.usuario.request.LoginRequest;
import com.burguer.zap.burguer.rest.usuario.request.RegisterRequest;
import com.burguer.zap.burguer.vo.Usuario;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * Created by LucasOrso on 10/5/17.
 */

public class UserRest {

    public interface Api {
        @POST("usuario/inserir")
        Call<Usuario> registerUser(@Body RegisterRequest aRequest);

        @POST("usuario/login")
        Call<Usuario> doLogin(@Body LoginRequest aRequest);

        @GET("usuario")
        Call<List<Usuario>> getUsersList();
    }
}