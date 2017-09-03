package com.orsomob.reservamesas;

/**
 * Created by LucasOrso on 8/24/17.
 */

class Mesa {

    private int mNumeromesa;
    private boolean mReservada;

    public Mesa(){

    }

    public Mesa(int aNumeroMesa, boolean aReservada) {
        mNumeromesa = aNumeroMesa;
        mReservada = aReservada;
    }

    public int getNumeromesa() {
        return mNumeromesa;
    }

    public void setNumeromesa(int aNumeromesa) {
        mNumeromesa = aNumeromesa;
    }

    public boolean isReservada() {
        return mReservada;
    }

    public void setReservada(boolean aReservada) {
        mReservada = aReservada;
    }
}
