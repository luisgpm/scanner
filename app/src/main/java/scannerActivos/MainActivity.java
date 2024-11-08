package scannerActivos;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Base64;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import com.apollo.scanneractivos.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 100;

    private static final int SCANNER_REQUEST_CODE = 101;

    private TextView scannedResultTextView;
    private TextView nombre;
    private TextView puesto;
    private TextView depto;
    private TextView no_activo;
    private TextView placa;
    private TextView desc;
    private TextView modelo;
    private TextView marca;
    private TextView serie;
    private TextView estado;
    private TextView banner;

    private ImageView foto;
    private TextView fecha;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        FloatingActionButton fabScan = findViewById(R.id.fab_scan);
        scannedResultTextView = findViewById(R.id.text_cent);
        nombre = findViewById(R.id.nombre);
        puesto = findViewById(R.id.puesto);
        depto = findViewById(R.id.depto);
        no_activo = findViewById(R.id.no_activo);
        placa = findViewById(R.id.placa);
        desc = findViewById(R.id.desc);
        modelo = findViewById(R.id.modelo);
        marca = findViewById(R.id.marca);
        serie = findViewById(R.id.serie);
        estado = findViewById(R.id.estado);
        banner = findViewById(R.id.text_in_rectangle);
        foto = findViewById(R.id.foto);
        fecha = findViewById(R.id.fecha);


        fabScan.setOnClickListener(view -> checkCameraPermission());
    }

    private void checkCameraPermission() {
        if (ContextCompat.checkSelfPermission(MainActivity.this, Manifest.permission.CAMERA)
                != PackageManager.PERMISSION_GRANTED) {
            // Si el permiso de la cámara no está concedido, solicítalo
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.CAMERA},
                    CAMERA_PERMISSION_REQUEST_CODE);
        } else {
            // Si el permiso de la cámara está concedido, abre el escáner
            startScanner();
        }
    }

    private void startScanner() {
        Intent intent = new Intent(this, CustomScannerActivity.class);
        startActivityForResult(intent, SCANNER_REQUEST_CODE);
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == CAMERA_PERMISSION_REQUEST_CODE) {
            if (grantResults.length > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                // El permiso de la cámara fue otorgado, ahora puedes abrir el escáner
                startScanner();
            } else {
                // El permiso de la cámara fue denegado, muestra un mensaje o toma alguna otra acción
                Toast.makeText(this, "Permiso de la cámara denegado", Toast.LENGTH_SHORT).show();
            }
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Procesa el resultado del escaneo
        limpiarCampos();
        if (requestCode == SCANNER_REQUEST_CODE){
            if (resultCode == RESULT_OK && data != null){
                Activo activo = (Activo) data.getSerializableExtra("ACTIVO");
                String fotoString = data.getStringExtra("FOTO");
                if (activo != null){
                    Empleado empleado = (Empleado) activo.getEmpleado();
                    if (fotoString != null ){
                        byte[] decodedString = Base64.decode(fotoString, Base64.DEFAULT);
                        Bitmap decodedByte = BitmapFactory.decodeByteArray(decodedString,0 , decodedString.length);
                        foto.setImageBitmap(decodedByte);
                    }

                    scannedResultTextView.setVisibility(View.GONE);
                    if (empleado != null){
                        nombre.setText(activo.getEmpleado().getEmpNombre() + ' ' + activo.getEmpleado().getEmpPaterno() + ' ' + activo.getEmpleado().getEmpMaterno() + " | " + activo.getEmpleado().getEmpCodigo() );
                        puesto.setText(activo.getEmpleado().getPuesto().getDescripcion());
                        depto.setText(activo.getEmpleado().getEstructuraNominal().getDescripcion());
                    }else{
                        nombre.setText("No Asignado");
                    }
                    no_activo.setText("Número de activo: " + activo.getNo_activo());
                    placa.setText("Placa: " + activo.getPlaca());
                    desc.setText("Descripción: " + activo.getDescCorta());
                    modelo.setText("Modelo: " + activo.getModelo());
                    marca.setText("Marca: " + activo.getMarca());
                    serie.setText("Serie: " + activo.getNoSerie());
                    estado.setText("Estado: " + activo.getEstado());
                    if (activo.getFecha() != null){
                        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd", Locale.getDefault());
                        String fechaFormateada = sdf.format(activo.getFecha());
                        fecha.setText("Fecha: " + fechaFormateada);
                    }else{
                        fecha.setText("Fecha: N/A");
                    }
                    mostrarCampos();

                }else{
                    Toast.makeText(this, "No se encontró el activo", Toast.LENGTH_SHORT).show();
                    ocultarCampos();
                }
            }else{
                Toast.makeText(this, "No se encontró el activo", Toast.LENGTH_SHORT).show();
                ocultarCampos();
            }
        }
    }

    private void limpiarCampos(){
        foto.setImageDrawable(null);
        nombre.setText("");
        puesto.setText("");
        depto.setText("");
        no_activo.setText("");
        placa.setText("");
        desc.setText("");
        modelo.setText("");
        marca.setText("");
        serie.setText("");
        estado.setText("");
        fecha.setText("");
    }
    private void mostrarCampos(){
        foto.setVisibility(View.VISIBLE);
        banner.setVisibility(View.VISIBLE);
        nombre.setVisibility(View.VISIBLE);
        puesto.setVisibility(View.VISIBLE);
        depto.setVisibility(View.VISIBLE);
        no_activo.setVisibility(View.VISIBLE);
        placa.setVisibility(View.VISIBLE);
        desc.setVisibility(View.VISIBLE);
        modelo.setVisibility(View.VISIBLE);
        marca.setVisibility(View.VISIBLE);
        serie.setVisibility(View.VISIBLE);
        estado.setVisibility(View.VISIBLE);
        fecha.setVisibility(View.VISIBLE);
    }

    private void ocultarCampos(){
        scannedResultTextView.setVisibility(View.VISIBLE);
        foto.setVisibility(View.GONE);
        banner.setVisibility(View.GONE);
        nombre.setVisibility(View.GONE);
        puesto.setVisibility(View. GONE);
        depto.setVisibility(View.GONE);
        no_activo.setVisibility(View.GONE);
        placa.setVisibility(View.GONE);
        desc.setVisibility(View.GONE);
        modelo.setVisibility(View.GONE);
        marca.setVisibility(View.GONE);
        serie.setVisibility(View.GONE);
        estado.setVisibility(View.GONE);
        fecha.setVisibility(View.GONE);
    }
}