package com.ldm.quiz;

import androidx.appcompat.app.AlertDialog;
import androidx.core.content.ContextCompat;
import androidx.recyclerview.widget.RecyclerView;

import android.content.DialogInterface;
import android.content.Intent;
import android.content.res.ColorStateList;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.ldm.quiz.dummy.DummyContent.DummyItem;

import java.util.List;

/**
 * {@link RecyclerView.Adapter} that can display a {@link DummyItem}.
 * TODO: Replace the implementation with code for your data type.
 */
public class MyPartidaRecyclerViewAdapter extends RecyclerView.Adapter<MyPartidaRecyclerViewAdapter.ViewHolder> {

    private final List<Partida> mValues;

    public MyPartidaRecyclerViewAdapter(List<Partida> items) {
        mValues = items;
        System.out.println(items);
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.partida_layout, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(final ViewHolder holder, int position) {

        // Coloreamos de verde las preguntas correctas y de rojo las incorrectas y ponemos los puntos
        holder.puntos.setText(holder.puntos.getContext().getString(R.string.puntos_acumulados, mValues.get(position).getPuntos()));
        if (mValues.get(position).isP1())
            holder.p1.setBackgroundTintList(ColorStateList.valueOf(holder.p1.getResources().getColor(R.color.respuestaCorrecta)));
        else
            holder.p1.setBackgroundTintList(ColorStateList.valueOf(holder.p1.getResources().getColor(R.color.respuestaIncorrecta)));
        if (mValues.get(position).isP2())
            holder.p2.setBackgroundTintList(ColorStateList.valueOf(holder.p2.getResources().getColor(R.color.respuestaCorrecta)));
        else
            holder.p2.setBackgroundTintList(ColorStateList.valueOf(holder.p2.getResources().getColor(R.color.respuestaIncorrecta)));
        if (mValues.get(position).isP3())
            holder.p3.setBackgroundTintList(ColorStateList.valueOf(holder.p3.getResources().getColor(R.color.respuestaCorrecta)));
        else
            holder.p3.setBackgroundTintList(ColorStateList.valueOf(holder.p3.getResources().getColor(R.color.respuestaIncorrecta)));
        if (mValues.get(position).isP4())
            holder.p4.setBackgroundTintList(ColorStateList.valueOf(holder.p4.getResources().getColor(R.color.respuestaCorrecta)));
        else
            holder.p4.setBackgroundTintList(ColorStateList.valueOf(holder.p4.getResources().getColor(R.color.respuestaIncorrecta)));
        if (mValues.get(position).isP5())
            holder.p5.setBackgroundTintList(ColorStateList.valueOf(holder.p5.getResources().getColor(R.color.respuestaCorrecta)));
        else
            holder.p5.setBackgroundTintList(ColorStateList.valueOf(holder.p5.getResources().getColor(R.color.respuestaIncorrecta)));

        // Si se hace clic en el botón eliminar aparece un dialogo. Si se confirma, se elimina de la base de datos y de la lista de elementos
        holder.btn_eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(final View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setMessage("¿Desea eliminar la partida?");
                builder.setPositiveButton("Eliminar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        MiRoom.getMiRoom(v.getContext()).partidaDAO().deletePartida(mValues.get(holder.getAdapterPosition()).getId());
                        mValues.remove(holder.getAdapterPosition());
                        notifyItemRemoved(holder.getAdapterPosition());
                    }
                });
                builder.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                AlertDialog dialog = builder.create();
                dialog.show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return mValues.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        public final TextView puntos;
        public final TextView p1;
        public final TextView p2;
        public final TextView p3;
        public final TextView p4;
        public final TextView p5;
        public final ImageButton btn_eliminar;

        public ViewHolder(View view) {
            super(view);
            puntos = view.findViewById(R.id.prueba);
            p1 = view.findViewById(R.id.p1);
            p2 = view.findViewById(R.id.p2);
            p3 = view.findViewById(R.id.p3);
            p4 = view.findViewById(R.id.p4);
            p5 = view.findViewById(R.id.p5);
            btn_eliminar = view.findViewById(R.id.btn_eliminar_partida);
        }

    }
}
