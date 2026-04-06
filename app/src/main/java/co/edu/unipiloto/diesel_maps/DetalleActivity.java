package co.edu.unipiloto.diesel_maps;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.maps.*;
import com.google.android.gms.maps.model.*;

public class DetalleActivity extends AppCompatActivity implements OnMapReadyCallback {

    private GoogleMap mMap;
    String nombre;
    double lat, lng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalle);

        TextView txt = findViewById(R.id.txtEstacion);

        nombre = getIntent().getStringExtra("estacion");
        txt.setText(nombre);

        lat = 4.7110;
        lng = -74.0721;

        if(nombre != null){
            if(nombre.contains("Norte")){
                lat = 4.7610;
                lng = -74.0315;

            } else if(nombre.contains("Sur")){
                lat = 4.5075;
                lng = -74.1617;

            } else if(nombre.contains("Central")){
                lat = 4.5981;
                lng = -74.0760;
            }
        }

        SupportMapFragment mapFragment = (SupportMapFragment)
                getSupportFragmentManager().findFragmentById(R.id.map);

        if (mapFragment != null) {
            mapFragment.getMapAsync(this);
        }
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        LatLng ubicacion = new LatLng(lat, lng);

        mMap.addMarker(new MarkerOptions()
                .position(ubicacion)
                .title(nombre));

        mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(ubicacion, 15));
    }

    public void irReporte(View view){
        startActivity(new Intent(this, ReporteActivity.class));
    }
}