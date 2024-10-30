package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;



public class IndexActivity extends AppCompatActivity {

    private ImageView imageHome, imageChefHat, imageKitchen;
    private ImageButton btnSalir;
    private ImageView imageCarnes;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_index); // Asegúrate de que el nombre del layout sea correcto

        // Inicializa las vistas
        imageHome = findViewById(R.id.image_home);
        imageChefHat = findViewById(R.id.image_chef_hat);
        imageKitchen = findViewById(R.id.image_kitchen);
        btnSalir = findViewById(R.id.btnSalir);
        imageCarnes = findViewById(R.id.image_carnes);

        // Configura los listeners para cada imagen
        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexActivity.this, IndexActivity.class)); // Redirige a IndexActivity
            }
        });

        imageChefHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = 1; // Cambia esto por la lógica que uses para obtener el userId real
                Intent intent = new Intent(IndexActivity.this, PerfilActivity.class);
                intent.putExtra("USER_ID", userId); // Pasa el userId
                startActivity(intent); // Inicia PerfilActivity
            }
        });


        imageKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexActivity.this, SubirRecetasActivity.class)); // Redirige a SubirRecetasActivity
            }
        });

        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
                System.exit(0); // Cierra la aplicación
            }
        });

        imageCarnes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(IndexActivity.this, CategoriasActivity.class)); // Redirige a CategoriasActivity
            }
        });
    }
}
