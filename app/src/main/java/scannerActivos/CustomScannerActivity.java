package scannerActivos;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.apollo.scanneractivos.R;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;

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
            statusView.setTextColor(getResources().getColor(R.color.white));
        }

        barcodeView.decodeContinuous(callback);
        Toolbar toolbar = findViewById(R.id.toolbar);

        setSupportActionBar(toolbar);
        if (getSupportActionBar() != null){
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }

    }

    // Callback para manejar el resultado del escaneo
    private final BarcodeCallback callback = new BarcodeCallback() {
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
                    String foto = apiResponse.getFoto();
                    Activo activo = apiResponse.getActivo();
                    Intent intent = new Intent();
                    intent.putExtra("ACTIVO", activo);
                    intent.putExtra("FOTO", foto);
                    setResult(RESULT_OK, intent);
                    finish();
                } else {
                    Log.e("Error", "Respuesta no exitosa");
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
