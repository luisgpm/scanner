package com.example.scanneractivos;

import android.os.Bundle;

import com.journeyapps.barcodescanner.CaptureActivity;
import com.journeyapps.barcodescanner.DecoratedBarcodeView;
import com.journeyapps.barcodescanner.BarcodeCallback;
import com.journeyapps.barcodescanner.BarcodeResult;
import android.widget.Toast;

import android.content.Intent;


public class CustomScannerActivity extends CaptureActivity {
    // Puedes personalizar aún más esta actividad si es necesario
    private DecoratedBarcodeView barcodeView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_custom_scanner);

        barcodeView = findViewById(R.id.zxing_barcode_scanner);

        barcodeView.decodeContinuous(new BarcodeCallback() {
            @Override
            public void barcodeResult(BarcodeResult result) {
                if (result.getText() != null) {
                    // Enviar el resultado de vuelta a MainActivity
                    Intent intent = new Intent();
                    intent.putExtra("SCANNED_RESULT", result.getText());
                    setResult(RESULT_OK, intent);
                    finish();
                }
            }
        }); // Configurar el callback para el escaneo continuo
    }

    // Callback para manejar el resultado del escaneo
    private BarcodeCallback callback = new BarcodeCallback() {
        @Override
        public void barcodeResult(BarcodeResult result) {
            if (result.getText() != null) {
                // Aquí puedes manejar el resultado del escaneo
                Toast.makeText(CustomScannerActivity.this, "Código escaneado: " + result.getText(), Toast.LENGTH_SHORT).show();
            }
        }
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

}
