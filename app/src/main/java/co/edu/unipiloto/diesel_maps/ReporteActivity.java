package co.edu.unipiloto.diesel_maps;

import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

public class ReporteActivity extends AppCompatActivity {

    Spinner spTipoGasolina;
    EditText etPrecio;
    Button btnEnviar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reporte);

        spTipoGasolina = findViewById(R.id.spTipoGasolina);
        etPrecio = findViewById(R.id.etPrecio);
        btnEnviar = findViewById(R.id.btnEnviar);


        String[] tipos = {"Corriente", "Extra", "Diesel"};

        ArrayAdapter<String> adapter = new ArrayAdapter<>(
                this,
                android.R.layout.simple_spinner_item,
                tipos
        );

        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spTipoGasolina.setAdapter(adapter);


        btnEnviar.setOnClickListener(v -> enviarReporte());
    }

    private void enviarReporte() {
        String tipo = spTipoGasolina.getSelectedItem().toString();
        String precio = etPrecio.getText().toString().trim();

        if (precio.isEmpty()) {
            etPrecio.setError("Ingresa un precio");
            return;
        }

        Toast.makeText(this,
                "Tipo: " + tipo + " | Precio: " + precio,
                Toast.LENGTH_SHORT).show();
    }
}