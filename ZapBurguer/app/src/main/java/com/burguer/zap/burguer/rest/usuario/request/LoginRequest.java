package com.burguer.zap.burguer.rest.usuario.request;

/**
 * Created by LucasOrso on 10/8/17.
 */

public class LoginRequest {

    private String Email;
    private String Password;

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
