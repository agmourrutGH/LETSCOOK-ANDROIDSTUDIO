package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleRecetasActivity extends AppCompatActivity {

    private ImageView recipeImage;
    private TextView nombreReceta;
    private TextView descripcionReceta;
    private TextView ingredientesLista;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_recetas); // Asegúrate de que este sea el nombre correcto de tu archivo XML

        // Obtener referencias a las vistas
        recipeImage = findViewById(R.id.recipe_image);
        nombreReceta = findViewById(R.id.nombre_receta);
        descripcionReceta = findViewById(R.id.descripcion_receta);
        ingredientesLista = findViewById(R.id.ingredientes_lista);

        // Obtener el objeto Recipe pasado desde la actividad anterior
        Recipe recipe = (Recipe) getIntent().getSerializableExtra("recipe");

        if (recipe != null) {
            // Configurar los datos en las vistas
            // Cargar la imagen utilizando Glide o Picasso si está disponible
            // Glide.with(this).load(recipe.getImage()).into(recipeImage);

            // recipeImage.setImageResource(R.drawable.placeholder); // Asegúrate de tener una imagen en drawable
            nombreReceta.setText(recipe.getName());
            descripcionReceta.setText(recipe.getDescription());
            ingredientesLista.setText(recipe.getIngredients());
        }
    }
}
