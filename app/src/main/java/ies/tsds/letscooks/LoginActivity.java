package ies.tsds.letscooks;

import static ies.tsds.letscooks.R.layout.activity_login;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;


public class LoginActivity extends AppCompatActivity {

    private EditText inUsuario, inContrasena;
    private Button btLogin, btRegister, btExit;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_login);

        // Inicializa las vistas
        inUsuario = findViewById(R.id.inUsuario);
        inContrasena = findViewById(R.id.inContrasena);
        btLogin = findViewById(R.id.btLogin);
        btRegister = findViewById(R.id.btRegister);
        btExit = findViewById(R.id.btExit);

        // Inicializa la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Configura el botón de "Ingresar"
        btLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                loginUser();
            }
        });

        // Configura el botón de "Registrarse"
        btRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
            }
        });

        // Configura el botón de "Salir"
        btExit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
                System.exit(0); // Cierra la aplicación
            }
        });
    }

    private void loginUser() {
        String email = inUsuario.getText().toString().trim();
        String password = inContrasena.getText().toString().trim();

        // Validar entradas
        if (email.isEmpty() || password.isEmpty()) {
            Toast.makeText(this, getString(R.string.error_complete_fields), Toast.LENGTH_SHORT).show();
            return;
        }

        // Verificar si el usuario es "admin" y la contraseña es "admin"
        if (email.equals("admin") && password.equals("admin")) {
            Toast.makeText(this, getString(R.string.login_success_admin), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, IndexActivity.class));
            finish(); // Cierra la actividad de login
            return;
        }

        // Verificar en la base de datos
        boolean isValidUser = databaseHelper.checkUser(email, password);
        if (isValidUser) {
            Toast.makeText(this, getString(R.string.login_success), Toast.LENGTH_SHORT).show();
            startActivity(new Intent(LoginActivity.this, IndexActivity.class));
            finish(); // Cierra la actividad de login
        } else {
            Toast.makeText(this, getString(R.string.login_failed), Toast.LENGTH_SHORT).show();
        }
    }
}
