package com.burguer.zap.burguer.vo;

import com.google.gson.annotations.SerializedName;

/**
 * Created by LucasOrso on 10/5/17.
 */

public class Usuario {

    @SerializedName("CPF")
    private String mCpf;

    @SerializedName("Nome")
    private String mNome;

    @SerializedName("IdUsuario")
    private Long mIdUsuario;

    @SerializedName("Email")
    private String mEmail;

    @SerializedName("IdApp")
    private Long mIdApp;

    @SerializedName("Senha")
    private String mSenha;

    public String getCpf() {
        return mCpf;
    }

    public void setCpf(String aCpf) {
        mCpf = aCpf;
    }

    public String getNome() {
        return mNome;
    }

    public void setNome(String aNome) {
        mNome = aNome;
    }

    public Long getId() {
        return mIdUsuario;
    }

    public void setId(Long aId) {
        mIdUsuario = aId;
    }

    public String getEmail() {
        return mEmail;
    }

    public void setEmail(String aEmail) {
        mEmail = aEmail;
    }

    public Long getIdApp() {
        return mIdApp;
    }

    public void setIdApp(Long aIdApp) {
        mIdApp = aIdApp;
    }

    public String getSenha() {
        return mSenha;
    }

    public void setSenha(String aSenha) {
        mSenha = aSenha;
    }
}
