package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import androidx.appcompat.app.AppCompatActivity;

import ies.tsds.letscooks.DetalleRecetasActivity;

public class ListadoItemsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_items); // Asegúrate de que esto coincida con tu XML

        // Botón de retroceso
        ImageView btnBack = findViewById(R.id.btnBack);
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish(); // Cierra la actividad actual y vuelve a la anterior
            }
        });

        // Redirigir a la actividad de detalles de receta al hacer clic en la imagen de la tortilla
        LinearLayout itemTortilla = findViewById(R.id.itemTortilla);
        itemTortilla.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ListadoItemsActivity.this, DetalleRecetasActivity.class);
                intent.putExtra("receta", "Tortilla de Papas"); // Puedes pasar datos adicionales si lo necesitas
                startActivity(intent);
            }
        });
    }
}
