package com.example.scanneractivos;

import android.os.Bundle;
import com.journeyapps.barcodescanner.CaptureActivity;
import androidx.appcompat.app.AppCompatActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import android.content.Intent;
import androidx.appcompat.widget.Toolbar;
import android.view.MenuItem;
import android.widget.TextView;
import android.view.View;

public class CustomScannerActivity extends AppCompatActivity  {
    // Puedes personalizar aún más esta actividad si es necesario
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
                // Aquí puedes manejar el resultado del escaneo
                // Enviar el resultado de vuelta a MainActivity
                Intent intent = new Intent();
                intent.putExtra("SCANNED_RESULT", result.getText());
                setResult(RESULT_OK, intent);
                finish();
            }

        };
    };

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
