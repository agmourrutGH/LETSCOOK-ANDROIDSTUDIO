package ies.tsds.letscooks;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class DetalleRecetasActivity extends AppCompatActivity {
    private ImageView recipeImage;
    private TextView nombreReceta;
    private TextView descripcionReceta;
    private TextView ingredientesLista;
    private DatabaseHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_recetas);

    }
}
