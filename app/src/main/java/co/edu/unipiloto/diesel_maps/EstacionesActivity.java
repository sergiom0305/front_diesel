package co.edu.unipiloto.diesel_maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class EstacionesActivity extends AppCompatActivity {

    LinearLayout contenedor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_estaciones);

        contenedor = findViewById(R.id.contenedorEstaciones);

        agregarEstacion("Estación Norte", "Terpel", "Corriente: $12.000 | Extra: $14.000 | Diesel: $11.500", "8", "3001234567");
        agregarEstacion("Estación Sur", "Texaco", "Corriente: $11.800 | Extra: $13.900 | Diesel: $11.300", "5", "3019876543");
        agregarEstacion("Estación Central", "Primax", "Corriente: $12.200 | Extra: $14.200 | Diesel: $11.700", "6", "3025558888");
    }

    private void agregarEstacion(String nombreEstacion, String marca, String precios, String trabajadores, String telefono) {

        View card = getLayoutInflater().inflate(R.layout.item_estacion, null);

        TextView tvNombre = card.findViewById(R.id.tvNombre);
        TextView tvGasolina = card.findViewById(R.id.tvGasolina);
        TextView tvTrabajadores = card.findViewById(R.id.tvTrabajadores);
        TextView tvTelefono = card.findViewById(R.id.tvTelefono);

        tvNombre.setText(nombreEstacion);

        tvGasolina.setText("Marca: " + marca + "\n" + precios);

        tvTrabajadores.setText("Trabajadores: " + trabajadores);
        tvTelefono.setText("Contacto: " + telefono);

        card.setOnClickListener(v -> {
            Intent intent = new Intent(EstacionesActivity.this, DetalleActivity.class);
            intent.putExtra("estacion", nombreEstacion);
            startActivity(intent);
        });

        contenedor.addView(card);
    }
}