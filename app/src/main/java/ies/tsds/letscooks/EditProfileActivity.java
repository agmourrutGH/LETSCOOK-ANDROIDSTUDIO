package ies.tsds.letscooks;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.annotation.Nullable;

public class EditProfileActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private EditText nombreEditText, emailEditText;
    private ImageView profilePicture, btnBackEditPerfil;
    private DatabaseHelper databaseHelper;
    private int userId;
    private Uri selectedImageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);

        // Inicializa los componentes
        nombreEditText = findViewById(R.id.nombre);
        emailEditText = findViewById(R.id.email);
        profilePicture = findViewById(R.id.profile_picture);
        btnBackEditPerfil = findViewById(R.id.btnBackEditPerfil);
        Button changePhotoButton = findViewById(R.id.change_photo_btn);
        Button actualizarButton = findViewById(R.id.btn_actualizar_perfil);


        // Inicializa el helper de la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Obtener userId del Intent
        userId = getIntent().getIntExtra("USER_ID", -1);
        loadUserData(userId);

        // Configurar el botón para cambiar foto
        changePhotoButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Configurar el botón para actualizar perfil
        actualizarButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                updateProfile();
            }
        });

        // Configurar el botón de retroceso
        btnBackEditPerfil.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Regresa a la actividad anterior
            }
        });
    }

    private void loadUserData(int userId) {
        String nombre = databaseHelper.getUserName(userId);
        String email = databaseHelper.getUserEmail(userId);
        String imagePath = databaseHelper.getUserImagePath(userId);

        nombreEditText.setText(nombre);
        emailEditText.setText(email);

        // Cargar la imagen de perfil si existe
        if (imagePath != null) {
            Bitmap bitmap = BitmapFactory.decodeFile(imagePath);
            profilePicture.setImageBitmap(bitmap);
        }
    }

    private void openGallery() {
        Intent intent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(intent, PICK_IMAGE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PICK_IMAGE && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            profilePicture.setImageURI(selectedImageUri);
        }
    }

    private void updateProfile() {
        String nombre = nombreEditText.getText().toString();
        String email = emailEditText.getText().toString();

        // Actualiza en la base de datos
        databaseHelper.updateUserProfile(userId, nombre, email, selectedImageUri != null ? selectedImageUri.toString() : null);

        // Redirigir a PerfilActivity
        Intent intent = new Intent(EditProfileActivity.this, PerfilActivity.class);
        intent.putExtra("USER_ID", userId);
        startActivity(intent);
        finish(); // Finaliza esta actividad
    }
}
