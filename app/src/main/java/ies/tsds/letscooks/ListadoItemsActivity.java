package ies.tsds.letscooks;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ListadoItemsActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private RecipeAdapter recipeAdapter;
    private List<Recipe> recipeList;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_items); // Crea un layout para la actividad

        recyclerView = findViewById(R.id.recycler_view); // Asegúrate de tener un RecyclerView en el layout
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        recipeList = new ArrayList<>();

        // Añadir recetas estáticas
        recipeList.add(new Recipe(1, "Tortilla de papas", "La tortilla de patatas, tortilla de papas o tortilla española es una tortilla u omelet  a la que se le agrega patatas troceadas.", "1 kg de papas\n" +
                "8 huevos\n" +
                "Aceite, para freír\n" +
                "Sal a gusto", R.drawable.tortilla_de_papa));
        recipeList.add(new Recipe(2, "Higado Encebollado", "Este platillo es súper común en los menús de “comida corrida” y hacerlo en casa es tradición; porque el hígado encebollado es una comida súper noble que alimenta a familias completas.", "1½ kg bisteces de hígado\n" +
                "6 cda aceite\n" +
                "4 dientes de ajo pelados\n" +
                "2 cebollas grandes en rodajas\n" +
                "12 granos de pimienta negra\n" +
                "sal al gusto", R.drawable.higado_encebollado));

        // Cargar recetas desde la base de datos
        loadRecipesFromDatabase();

        recipeAdapter = new RecipeAdapter(this, recipeList);
        recyclerView.setAdapter(recipeAdapter);
    }
    private void loadRecipesFromDatabase() {
        DatabaseHelper dbHelper = new DatabaseHelper(this);
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.rawQuery("SELECT * FROM " + DatabaseHelper.TABLE_RECIPES, null);
        Log.d("RecipeListActivity", "Número de recetas: " + cursor.getCount());


        try {
            if (cursor.moveToFirst()) {
                do {
                    int id = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_RECIPE_ID));
                    String name = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RECIPE_NAME));
                    String description = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RECIPE_DESCRIPTION));
                    String ingredients = cursor.getString(cursor.getColumnIndex(DatabaseHelper.COLUMN_RECIPE_INGREDIENTS));
                    int image = cursor.getInt(cursor.getColumnIndex(DatabaseHelper.COLUMN_RECIPE_IMAGE));
                    recipeList.add(new Recipe(id, name, description, ingredients, image));
                } while (cursor.moveToNext());
            } else {
                Log.d("RecipeListActivity", "No hay recetas en la base de datos.");
            }
        } catch (Exception e) {
            Log.e("RecipeListActivity", "Error al cargar recetas: " + e.getMessage());
        }

    }

}