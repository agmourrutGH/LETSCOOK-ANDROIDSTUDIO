package ies.tsds.letscooks;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class SubirRecetasActivity extends AppCompatActivity {

    private static final int PICK_IMAGE = 1;
    private EditText recipeNameEditText, recipeDescriptionEditText, recipeIngredientsEditText;
    private ImageView imageRecipe;
    private Uri selectedImageUri;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subir_recetas);

        // Inicializar componentes
        recipeNameEditText = findViewById(R.id.recipe_name);
        recipeDescriptionEditText = findViewById(R.id.recipe_description);
        recipeIngredientsEditText = findViewById(R.id.recipe_ingredients);
        imageRecipe = findViewById(R.id.image_recipe);
        Button selectImageButton = findViewById(R.id.btn_select_image);
        Button submitRecipeButton = findViewById(R.id.btn_submit_recipe);
        ImageView btnBackRecetas = findViewById(R.id.btnBackRecetas);

        // Inicializar el helper de la base de datos
        databaseHelper = new DatabaseHelper(this);

        // Configurar botón de selección de imagen
        selectImageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openGallery();
            }
        });

        // Configurar botón de enviar receta
        submitRecipeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                submitRecipe();
            }
        });

        // Configurar botón de retroceso
        btnBackRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed(); // Regresa a la actividad anterior
            }
        });
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
            imageRecipe.setImageURI(selectedImageUri);
        }
    }

    private void submitRecipe() {
        String recipeName = recipeNameEditText.getText().toString();
        String recipeDescription = recipeDescriptionEditText.getText().toString();
        String recipeIngredients = recipeIngredientsEditText.getText().toString();

        // Validar campos
        if (recipeName.isEmpty() || recipeDescription.isEmpty() || recipeIngredients.isEmpty() || selectedImageUri == null) {
            Toast.makeText(this, "Por favor completa todos los campos y selecciona una imagen.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Guardar en la base de datos
        String imagePath = selectedImageUri.toString(); // Guardar URI de la imagen
        int userId = 1; // Cambia esto al ID real del usuario
        databaseHelper.addRecipe(recipeName, recipeDescription, recipeIngredients, imagePath, userId);

        // Mensaje de éxito y volver a la actividad anterior
        Toast.makeText(this, "Receta subida exitosamente!", Toast.LENGTH_SHORT).show();
        finish(); // Volver a la actividad anterior
    }
}
