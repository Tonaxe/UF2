package com.example.uf2redsocial;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder> {

    private List<Comentario> listaComentarios;

    // Constructor
    public ComentariosAdapter() {
        listaComentarios = new ArrayList<>();
    }

    public void setComentarios(List<Comentario> comentarios) {
        this.listaComentarios = comentarios;
        notifyDataSetChanged(); // Notificar al adaptador que los datos han cambiado
    }

    // Método para agregar un comentario a la lista
    public void agregarComentario(Comentario comentario) {
        listaComentarios.add(comentario);
        notifyItemInserted(listaComentarios.size() - 1); // Notificar al adaptador que se ha insertado un nuevo elemento
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

        private TextView nombreUsuarioTextView;
        private TextView contenidoComentarioTextView;

        ComentarioViewHolder(@NonNull View itemView) {
            super(itemView);

            nombreUsuarioTextView = itemView.findViewById(R.id.nombreUsuarioTextView);
            contenidoComentarioTextView = itemView.findViewById(R.id.contenidoComentarioTextView);
        }

        void bind(Comentario comentario) {
            // Establecer los datos del comentario en las vistas correspondientes
            nombreUsuarioTextView.setText(comentario.getNombreUsuario());
            contenidoComentarioTextView.setText(comentario.getContenido());
        }
    }
}
