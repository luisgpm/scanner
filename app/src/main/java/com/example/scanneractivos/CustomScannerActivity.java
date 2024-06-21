package com.example.scanneractivos;

import android.os.Bundle;
import androidx.appcompat.app.AppCompatActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;

import android.util.Log;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class CustomScannerActivity extends AppCompatActivity  {

    private ApiService apiService;
    private DecoratedBarcodeView barcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_custom_scanner);
        barcodeView = findViewById(R.id.zxing_barcode_scanner);

        TextView statusView = barcodeView.findViewById(com.google.zxing.client.android.R.id.zxing_status_view);
        if (statusView != null ) {
            statusView.setText("Coloca el código de barras o QR dentro del área");
            statusView.setVisibility(View.VISIBLE);
            statusView.setTextColor(getResources().getColor(R.color.rojo_brillante));
        }

        barcodeView.decodeContinuous(callback);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    // Callback para manejar el resultado del escaneo
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                String scannedResult = result.getText();

                Retrofit retrofit = RetrofitClient.getClient("https://apiprueba.apollo.mx/api/");
                apiService = retrofit.create(ApiService.class);
                obtenerActivo(scannedResult);
            }

        };
    };

    private void obtenerActivo(String placa) {
        Call<ApiResponse> call = apiService.obtenerActivo(placa);
        call.enqueue(new Callback<ApiResponse>() {
            @Override
            public void onResponse(Call<ApiResponse> call, Response<ApiResponse> response) {
                if (response.isSuccessful() && response.body() != null) {
                    ApiResponse apiResponse = response.body();
                    Activo activo = apiResponse.getActivos();

                    // Procesar el objeto activo como sea necesario
                    // Por ejemplo, podrías guardar el objeto activo en SharedPreferences
                    // o pasar el objeto a MainActivity usando un Intent
                    Intent intent = new Intent();
                    intent.putExtra("ACTIVO", activo);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Log.e("Error", "Respuesta no exitosa");
                    // Manejar el error si la respuesta no es exitosa
                    setResult(RESULT_CANCELED);
                    finish();
                }
            }

            @Override
            public void onFailure(Call<ApiResponse> call, Throwable t) {
                Log.e("Error", "Error al obtener el activo: " + t.getMessage());
                // Manejar el fallo en la solicitud
                setResult(RESULT_CANCELED);
                finish();
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        // Iniciar la captura continua cuando la actividad se reanuda
        barcodeView.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        // Pausar la captura continua cuando la actividad está en pausa
        barcodeView.pause();
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == android.R.id.home) {
            // Maneja el evento del icono de regreso
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
