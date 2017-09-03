package com.orsomob.reservamesas;

import android.app.Activity;
import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by LucasOrso on 8/24/17.
 */

class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {

    private List<Mesa> mMesaList = new ArrayList<>();
    private Context mContext;

    Adapter(Activity aActivity, List<Mesa> aMesas) {
        mMesaList = aMesas;
        mContext = aActivity;
    }

    @Override
    public Adapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_mesa, parent, false);
        return new ViewHolder(view);
    }

    public List<Mesa> getMesas() {
        return mMesaList;
    }

    @Override
    public void onBindViewHolder(final Adapter.ViewHolder holder, int position) {

        final Mesa lMesa = mMesaList.get(position);

        if (lMesa.isReservada()){
            holder.mBackgroud.setBackgroundColor(mContext.getResources().getColor(R.color.vermelho));
            holder.mButtonReservar.setEnabled(false);
        } else {
            holder.mBackgroud.setBackgroundColor(mContext.getResources().getColor(R.color.azul));
            holder.mButtonReservar.setEnabled(true);
        }
        holder.mNumeroMesa.setText(String.valueOf(lMesa.getNumeromesa()));
        holder.mButtonReservar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                holder.mButtonReservar.setEnabled(false);
                lMesa.setReservada(true);
                holder.mBackgroud.setBackgroundColor(mContext.getResources().getColor(R.color.vermelho));
            }
        });
    }

    @Override
    public int getItemCount() {
        return mMesaList.size();
    }

    public void atualizaMesa(Mesa aMesa, int posicao) {
        mMesaList.set(posicao, aMesa);
        notifyItemChanged(posicao);
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        private TextView mNumeroMesa;
        private Button mButtonReservar;
        private LinearLayout mBackgroud;

        ViewHolder(View itemView) {
            super(itemView);
            mNumeroMesa = (TextView) itemView.findViewById(R.id.text_view_numero);
            mButtonReservar = (Button) itemView.findViewById(R.id.btn_reservar);
            mBackgroud = (LinearLayout) itemView.findViewById(R.id.linear_background_mesa);
        }
    }
}
