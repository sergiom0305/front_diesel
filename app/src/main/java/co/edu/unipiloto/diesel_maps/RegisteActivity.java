package co.edu.unipiloto.diesel_maps;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RegisteActivity extends AppCompatActivity {

    EditText etNombre, etCorreo, etUsuario, etPassword, etConfirmPassword;
    Button btnRegistrar;


    ApiService apiService;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        etNombre = findViewById(R.id.etNombre);
        etCorreo = findViewById(R.id.etCorreo);
        etUsuario = findViewById(R.id.etUsuario);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegistrar = findViewById(R.id.btnRegistrar);


        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("http://10.0.2.2:8080/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        apiService = retrofit.create(ApiService.class);

        btnRegistrar.setOnClickListener(v -> registrarUsuario());
    }

    private void registrarUsuario() {
        String nombre = etNombre.getText().toString().trim();
        String correo = etCorreo.getText().toString().trim();
        String usuario = etUsuario.getText().toString().trim();
        String password = etPassword.getText().toString().trim();
        String confirmPassword = etConfirmPassword.getText().toString().trim();

        if (TextUtils.isEmpty(nombre)) { etNombre.setError("Ingresa nombre"); return; }
        if (TextUtils.isEmpty(correo)) { etCorreo.setError("Ingresa correo"); return; }
        if (TextUtils.isEmpty(usuario)) { etUsuario.setError("Ingresa usuario"); return; }
        if (TextUtils.isEmpty(password)) { etPassword.setError("Ingresa clave"); return; }
        if (!password.equals(confirmPassword)) {
            etConfirmPassword.setError("No coinciden");
            return;
        }


        UserAccount nuevoUsuario = new UserAccount(nombre, correo, usuario, password);


        btnRegistrar.setEnabled(false);

        apiService.registrarUsuario(nuevoUsuario).enqueue(new Callback<UserAccount>() {
            @Override
            public void onResponse(Call<UserAccount> call, Response<UserAccount> response) {
                btnRegistrar.setEnabled(true);
                if (response.isSuccessful()) {
                    Toast.makeText(RegisteActivity.this, "¡Registrado en MySQL con éxito!", Toast.LENGTH_LONG).show();
                    finish();
                } else {
                    Toast.makeText(RegisteActivity.this, "Error: " + response.code(), Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<UserAccount> call, Throwable t) {
                btnRegistrar.setEnabled(true);
                Log.e("RETROFIT_ERROR", t.getMessage());
                Toast.makeText(RegisteActivity.this, "No hay conexión con el servidor", Toast.LENGTH_SHORT).show();
            }
        });
    }
}