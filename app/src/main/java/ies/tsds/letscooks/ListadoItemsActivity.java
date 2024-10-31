package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;

public class ListadoItemsActivity extends AppCompatActivity {

    private ImageView btnBackListRecetas; // Declara la variable aquí

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_items);

        // Configurar botón de retroceso
        btnBackListRecetas = findViewById(R.id.btnBackListRecetas);
        btnBackListRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish(); // Volver a la actividad anterior
            }
        });

        // Configurar recetas (Ejemplo para las primeras 5 recetas)
        setupRecipeItem(R.id.nombre_receta_1, R.id.recipe_image_1,  "Tortilla de Papas", R.drawable.tortilla_de_papa);
        setupRecipeItem(R.id.nombre_receta_2, R.id.recipe_image_2, "Higado Encebollado", R.drawable.higado_encebollado);
        setupRecipeItem(R.id.nombre_receta_3, R.id.recipe_image_3, "Pollo al Horno", R.drawable.pollo_horno);
        setupRecipeItem(R.id.nombre_receta_4, R.id.recipe_image_4, "Cerdo con mostaza y miel al horno", R.drawable.cerdo_mostaza);
        setupRecipeItem(R.id.nombre_receta_5, R.id.recipe_image_5, "Ensalada de atún: receta con kale", R.drawable.atun_fideos); // Añade tu imagen

        // Configurar listeners para los clics
        setupClickListener(R.id.recipe_image_1, R.id.nombre_receta_1, "Tortilla de Papas");
        setupClickListener(R.id.recipe_image_2, R.id.nombre_receta_2, "Higado Encebollado");
        setupClickListener(R.id.recipe_image_3, R.id.nombre_receta_3, "Pollo al Horno");
        setupClickListener(R.id.recipe_image_4, R.id.nombre_receta_4, "Cerdo con mostaza y miel al horno");
        setupClickListener(R.id.recipe_image_5, R.id.nombre_receta_5, "Ensalada de atún: receta con kale"); // Añade tu clic

    }

    private void setupRecipeItem(int textViewId, int imageViewId, String recipeName, int imageResourceId) {
        ImageView imageView = findViewById(imageViewId);
        TextView textView = findViewById(textViewId);

        // Establecer la imagen y el texto
        imageView.setImageResource(imageResourceId);
        textView.setText(recipeName);
    }

    private void setupClickListener(int imageViewId, int textViewId, String recipeName) {
        ImageView imageView = findViewById(imageViewId);
        TextView textView = findViewById(textViewId);

        View.OnClickListener listener = new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openRecipeDetailActivity(recipeName);
            }
        };

        imageView.setOnClickListener(listener);
        textView.setOnClickListener(listener);
    }

    private void openRecipeDetailActivity(String recipeName) {
        Intent intent = new Intent(ListadoItemsActivity.this, DetalleRecetasActivity.class);
        intent.putExtra("recipe_name", recipeName);
        startActivity(intent);
    }
}
