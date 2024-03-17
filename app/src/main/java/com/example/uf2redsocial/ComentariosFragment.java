package com.example.uf2redsocial;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.Query;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class ComentariosFragment extends Fragment {

    private EditText commentEditText;
    private Button sendCommentButton;
    private RecyclerView commentsRecyclerView;
    private ComentariosAdapter comentariosAdapter;

    private FirebaseUser currentUser; // Usuario actualmente conectado
    private Post post; // El post seleccionado
    private View rootView; // Variable para almacenar la vista raíz del fragmento

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        rootView = inflater.inflate(R.layout.fragment_comentarios, container, false);
        return rootView;
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        // Obtener el usuario actualmente conectado
        currentUser = FirebaseAuth.getInstance().getCurrentUser();

        // Inicializar vistas
        commentEditText = rootView.findViewById(R.id.commentEditText);
        sendCommentButton = rootView.findViewById(R.id.sendCommentButton);
        commentsRecyclerView = rootView.findViewById(R.id.commentsRecyclerView);

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

        // Cargar comentarios del post
        cargarComentarios();
    }

    private void mostrarPost() {
        // Rellenar la información del post en las vistas correspondientes
        ImageView authorPhotoImageView = rootView.findViewById(R.id.authorPhotoImageView);
        TextView authorTextView = rootView.findViewById(R.id.authorTextView);
        TextView contentTextView = rootView.findViewById(R.id.contentTextView);

        // Aquí asigna la información del post a las vistas
        authorTextView.setText(post.getAuthor());
        contentTextView.setText(post.getContent());

        // Si hay una foto de autor disponible, cárgala en el ImageView
        if (post.getAuthorPhotoUrl() != null && !post.getAuthorPhotoUrl().isEmpty()) {
            Glide.with(requireContext())
                    .load(post.getAuthorPhotoUrl())
                    .placeholder(R.drawable.user) // imagen de carga mientras se descarga la imagen
                    .error(R.drawable.user) // imagen de error si la descarga falla
                    .circleCrop() // forma circular de la imagen
                    .into(authorPhotoImageView);
        } else {
            // Si no hay una URL de foto de autor válida, puedes establecer una imagen predeterminada
            authorPhotoImageView.setImageResource(R.drawable.user);
        }

        // Cargar los comentarios del post desde Firestore
        cargarComentariosDeFirestore(post.uid);
    }


    private void enviarComentario() {
        // Obtener el texto del comentario
        String comentario = commentEditText.getText().toString().trim();

        if (!comentario.isEmpty()) {
            if (currentUser != null) {
                // Crear un nuevo objeto Comentario con el nombre de usuario y la imagen del usuario actual
                Comentario nuevoComentario = new Comentario(currentUser.getDisplayName(), comentario, Objects.requireNonNull(currentUser.getPhotoUrl()).toString());

                // Guardar el comentario en Firestore
                guardarComentarioEnFirestore(post.uid, nuevoComentario);

                // Agregar el nuevo comentario a la lista de comentarios del adaptador
                comentariosAdapter.agregarComentario(nuevoComentario);

                // Limpiar el EditText después de enviar el comentario
                commentEditText.setText("");
            } else {
                Toast.makeText(getContext(), "Error al obtener la información del usuario", Toast.LENGTH_SHORT).show();
            }
        } else {
            Toast.makeText(getContext(), "El comentario está vacío", Toast.LENGTH_SHORT).show();
        }
    }


    private void cargarComentarios() {
        // Aquí debes cargar los comentarios del post desde la base de datos o cualquier otra fuente de datos
        // Luego, asigna la lista de comentarios al adaptador
        List<Comentario> listaComentarios = new ArrayList<>(); // Debes cargar los comentarios aquí
        comentariosAdapter.setComentarios(listaComentarios);
    }

    // Guardar un comentario en Firestore
    private void guardarComentarioEnFirestore(String postId, Comentario comentario) {
        // Obtener una referencia a la colección "comentarios"
        CollectionReference comentariosRef = FirebaseFirestore.getInstance().collection("comentarios");

        // Crear un nuevo documento para el comentario con un ID único
        comentariosRef.document()
                .set(comentario)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        // El comentario se guardó exitosamente en Firestore
                        Toast.makeText(getContext(), "Comentario guardado exitosamente", Toast.LENGTH_SHORT).show();
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        // Error al guardar el comentario en Firestore
                        Toast.makeText(getContext(), "Error al guardar el comentario: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    // Recuperar comentarios de Firestore para un post específico
    private void cargarComentariosDeFirestore(String postId) {
        // Obtener una referencia a la colección "comentarios" y realizar una consulta para obtener los comentarios del post
        CollectionReference comentariosRef = FirebaseFirestore.getInstance().collection("comentarios");
        Query query = comentariosRef.whereEqualTo("postId", postId);

        query.get().addOnSuccessListener(new OnSuccessListener<QuerySnapshot>() {
            @Override
            public void onSuccess(QuerySnapshot queryDocumentSnapshots) {
                // Procesar los resultados de la consulta y mostrar los comentarios en tu aplicación
                List<Comentario> comentarios = new ArrayList<>();
                for (QueryDocumentSnapshot documentSnapshot : queryDocumentSnapshots) {
                    Comentario comentario = documentSnapshot.toObject(Comentario.class);
                    comentarios.add(comentario);
                }
                // Actualizar tu RecyclerView o cualquier otra vista con los comentarios obtenidos
                comentariosAdapter.setComentarios(comentarios);
            }
        }).addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception e) {
                // Error al recuperar los comentarios de Firestore
                Toast.makeText(getContext(), "Error al cargar los comentarios: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }

}
