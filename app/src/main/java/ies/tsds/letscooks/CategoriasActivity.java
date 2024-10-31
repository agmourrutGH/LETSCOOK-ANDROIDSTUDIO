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
        btnBack = findViewById(R.id.btnBack);
        imageAltoProteina = findViewById(R.id.image_alto_proteina);


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

    }
}
