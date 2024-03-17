package com.example.uf2redsocial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ComentariosFragment extends Fragment {

    private EditText commentEditText;
    private Button sendCommentButton;
    private RecyclerView commentsRecyclerView;
    private ComentariosAdapter comentariosAdapter;

    private Post post; // El post seleccionado

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_comentarios, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Inicializar vistas
        commentEditText = view.findViewById(R.id.commentEditText);
        sendCommentButton = view.findViewById(R.id.sendCommentButton);
        commentsRecyclerView = view.findViewById(R.id.commentsRecyclerView);

        // Obtener el post seleccionado
        if (getArguments() != null) {
            post = getArguments().getParcelable("post");
        }

        // Configurar RecyclerView
        comentariosAdapter = new ComentariosAdapter();
        commentsRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        commentsRecyclerView.setAdapter(comentariosAdapter);

        // Configurar botón de enviar comentario
        sendCommentButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                enviarComentario();
            }
        });

        // Mostrar el post seleccionado
        mostrarPost();
    }

    private void mostrarPost() {
        // Aquí puedes usar el post para mostrar la información relevante en el RecyclerView
        // Por ejemplo, puedes mostrar el contenido del post y cargar los comentarios asociados
    }

    private void enviarComentario() {
        // Obtener el texto del comentario
        String comentario = commentEditText.getText().toString().trim();

        if (!comentario.isEmpty()) {
            // Aquí puedes agregar la lógica para enviar el comentario al servidor
            // Por ahora, simplemente mostraremos un mensaje de confirmación
            Toast.makeText(getContext(), "Comentario enviado: " + comentario, Toast.LENGTH_SHORT).show();

            // Limpiar el EditText después de enviar el comentario
            commentEditText.setText("");
        } else {
            Toast.makeText(getContext(), "El comentario está vacío", Toast.LENGTH_SHORT).show();
        }
    }

    // Clase de adaptador para la lista de comentarios
    private class ComentariosAdapter extends RecyclerView.Adapter<ComentariosAdapter.ComentarioViewHolder> {
        private List<String> comentarios = new ArrayList<>(); // Aquí deberías usar una lista de objetos Comentario

        @NonNull
        @Override
        public ComentarioViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_comentario, parent, false);
            return new ComentarioViewHolder(view);
        }

        @Override
        public void onBindViewHolder(@NonNull ComentarioViewHolder holder, int position) {
            String comentario = comentarios.get(position);
            holder.bind(comentario);
        }

        @Override
        public int getItemCount() {
            return comentarios.size();
        }

        // ViewHolder para un comentario individual
        class ComentarioViewHolder extends RecyclerView.ViewHolder {
            // Declarar vistas aquí

            ComentarioViewHolder(@NonNull View itemView) {
                super(itemView);
                // Inicializar vistas aquí
            }

            void bind(String comentario) {
                // Aquí puedes establecer el contenido del comentario en las vistas correspondientes
            }
        }
    }
}
