package com.example.scanneractivos;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface ApiService {

    @GET("activos/getActivo/{placa}")
    Call<ApiResponse> obtenerActivo(@Path("placa") String placa);

}
