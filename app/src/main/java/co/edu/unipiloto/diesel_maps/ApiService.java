package co.edu.unipiloto.diesel_maps;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.POST;

public interface ApiService {
    @POST("api/auth/register")
    Call<UserAccount> registrarUsuario(@Body UserAccount user);

    @POST("api/auth/login")
    Call<String> loginUsuario(@Body UserAccount user);
}
