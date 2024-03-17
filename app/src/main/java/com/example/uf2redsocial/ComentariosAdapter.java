package com.example.uf2redsocial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder> {

    private List<Comentario> listaComentarios;

    // Constructor
    public ComentariosAdapter(List<Comentario> listaComentarios) {
        this.listaComentarios = listaComentarios;
    }

    @NonNull
    @Override
    public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
        return new ComentarioViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
        Comentario comentario = listaComentarios.get(position);
        holder.bind(comentario);
    }

    @Override
    public int getItemCount() {
        return listaComentarios.size();
    }

    // ViewHolder para un comentario individual
    static class ComentarioViewHolder extends RecyclerView.ViewHolder {

        // Declarar vistas aquí

        ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);
            // Inicializar vistas aquí
        }

        void bind(Comentario comentario) {
            // Aquí puedes establecer el contenido del comentario en las vistas correspondientes
        }
    }
}
