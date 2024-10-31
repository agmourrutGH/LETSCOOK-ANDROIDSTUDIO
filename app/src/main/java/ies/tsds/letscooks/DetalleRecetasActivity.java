package ies.tsds.letscooks;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;

public class DetalleRecetasActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle_recetas);

        ImageView btnBack = findViewById(R.id.btnBack);

        // Configurar el evento click para el botón de retroceso
        btnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Regresar a la actividad anterior
                finish(); // Esto cierra la actividad actual
                // También puedes usar Intent si necesitas pasar datos
                // Intent intent = new Intent(DetalleRecetasActivity.this, PreviousActivity.class);
                // startActivity(intent);
            }
        });
    }
}
