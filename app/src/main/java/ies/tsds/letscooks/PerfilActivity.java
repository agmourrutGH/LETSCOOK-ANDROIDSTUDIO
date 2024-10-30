package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;

public class PerfilActivity extends AppCompatActivity {

    private TextView nameTextView;
    private TextView emailTextView;
    private ImageView profilePicture;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_perfil); // Asegúrate de que el layout sea correcto

        nameTextView = findViewById(R.id.name);
        emailTextView = findViewById(R.id.email);
        profilePicture = findViewById(R.id.profiles_picture);

        // Inicializar el helper de la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Obtener el userId desde el Intent
        int userId = getIntent().getIntExtra("USER_ID", -1); // Cambia "USER_ID" por la clave que uses

        // Verificar si el userId es válido
        if (userId != -1) {
            // Cargar datos del usuario
            loadUserData(userId);
        } else {
            // Manejar el caso donde el userId no es válido
            // Tal vez mostrar un mensaje de error o cerrar la actividad
        }

        // Redirigir a la actividad de editar perfil
        TextView editPerfil = findViewById(R.id.edit_perfil);
        editPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(PerfilActivity.this, EditProfileActivity.class);
                startActivity(intent);
            }
        });

        // Cerrar sesión
        Button logoutButton = findViewById(R.id.logout);
        logoutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                clearUserSession(); // Implementa este método según tu lógica
                Intent intent = new Intent(PerfilActivity.this, LoginActivity.class);
                startActivity(intent);
                finish(); // Finaliza la actividad actual
            }
        });
    }

    private void loadUserData(int userId) {
        String nombreUsuario = getUserNameFromDatabase(userId);
        String emailUsuario = getUserEmailFromDatabase(userId);
        String imagePath = getUserImagePath(userId);

        nameTextView.setText(nombreUsuario);
        emailTextView.setText(emailUsuario);

        // Cargar la imagen de perfil
        if (imagePath != null && !imagePath.isEmpty()) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            profilePicture.setImageBitmap(bitmap);
        } else {
            profilePicture.setImageResource(R.drawable.image_null);
        }
    }


    private String getUserNameFromDatabase(int userId) {
        return databaseHelper.getUserName(userId);
    }

    private String getUserEmailFromDatabase(int userId) {
        return databaseHelper.getUserEmail(userId);
    }

    private String getUserImagePath(int userId) {
        return databaseHelper.getUserImagePath(userId);
    }

    private void clearUserSession() {
        // Implementa la lógica para limpiar la sesión del usuario
    }
}
