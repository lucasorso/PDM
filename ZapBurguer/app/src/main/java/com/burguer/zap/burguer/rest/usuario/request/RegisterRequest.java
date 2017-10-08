package com.burguer.zap.burguer.rest.usuario.request;

/**
 * Created by LucasOrso on 10/8/17.
 */

public class RegisterRequest {

    private String Nome;
    private String Email;
    private String Password;

    public String getNome() {
        return Nome;
    }

    public void setNome(String aNome) {
        Nome = aNome;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String aEmail) {
        Email = aEmail;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String aPassword) {
        Password = aPassword;
    }
}
