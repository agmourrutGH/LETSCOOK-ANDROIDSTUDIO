package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

public class CategoriasActivity extends AppCompatActivity {
    private ImageView imageHome, imageChefHat, imageKitchen;
    private ImageView btnBack, imageAltoProteina;
    private ImageButton btnSalir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categorias); // Asegúrate de que el nombre del layout sea correcto

        // Inicializa las vistas
        imageHome = findViewById(R.id.image_home);
        imageChefHat = findViewById(R.id.image_chef_hat);
        imageKitchen = findViewById(R.id.image_kitchen);
        btnBack = findViewById(R.id.btnBack);
        imageAltoProteina = findViewById(R.id.image_alto_proteina);
        btnSalir = findViewById(R.id.btnSalir);

        // Configura el listener para el botón de retroceso
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });

        // Configura el listener para la imagen de alto en proteínas
        imageAltoProteina.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriasActivity.this, ListadoItemsActivity.class)); // Redirige a ListadoItemsActivity
            }
        });

        // Configura el listener para el botón de salir
        btnSalir.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual
                System.exit(0); // Cierra la aplicación
            }
        });

        imageHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriasActivity.this, IndexActivity.class)); // Redirige a IndexActivity
            }
        });

        imageChefHat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int userId = 1; // Cambia esto por la lógica que uses para obtener el userId real
                Intent intent = new Intent(CategoriasActivity.this, PerfilActivity.class);
                intent.putExtra("USER_ID", userId); // Pasa el userId
                startActivity(intent); // Inicia PerfilActivity
            }
        });


        imageKitchen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(CategoriasActivity.this, SubirRecetasActivity.class)); // Redirige a SubirRecetasActivity
            }
        });
    }
}
