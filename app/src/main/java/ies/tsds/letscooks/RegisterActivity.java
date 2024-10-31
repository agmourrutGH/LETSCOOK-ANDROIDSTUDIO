package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;



public class RegisterActivity extends AppCompatActivity {

    private EditText inUsuario, inEmail, inContrasena;
    private Button btRegister, btBack;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        inUsuario = findViewById(R.id.inUsuario);
        inEmail = findViewById(R.id.inEmail);
        inContrasena = findViewById(R.id.inContrasena);
        btRegister = findViewById(R.id.btRegister);
        btBack = findViewById(R.id.btBack);


        databaseHelper = new DatabaseHelper(this);


        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                registerUser();
            }
        });


        btBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
                startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
            }
        });
    }

    private void registerUser() {
        String username = inUsuario.getText().toString().trim();
        String email = inEmail.getText().toString().trim();
        String password = inContrasena.getText().toString().trim();

        // Validar entradas
        if (username.isEmpty() || email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, "Por favor completa todos los campos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Validar que la contraseña tenga al menos 8 caracteres y solo contenga caracteres alfanuméricos
        if (password.length() < 5) {
            Toast.makeText(this, "La contraseña debe tener al menos 8 caracteres", Toast.LENGTH_SHORT).show();
            return;
        }

        if (!password.matches("^[a-zA-Z0-9]*$")) {
            Toast.makeText(this, "La contraseña solo debe contener caracteres alfanuméricos", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar en la base de datos
        try {
            databaseHelper.addUser(username, email, password);
            Toast.makeText(this, "Registro exitoso", Toast.LENGTH_SHORT).show();
            finish(); // O redirigir a la pantalla de inicio de sesión
        } catch (Exception e) {
            Toast.makeText(this, "Error al registrar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

}
