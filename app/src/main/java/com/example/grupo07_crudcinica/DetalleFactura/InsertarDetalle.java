package com.example.grupo07_crudcinica.DetalleFactura;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import com.example.grupo07_crudcinica.ClinicaDbHelper;
import com.example.grupo07_crudcinica.R;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertarDetalle extends AppCompatActivity {

    private EditText edtIdFactura, edtMontoDetalle, edtFormaPago;
    private Button btnGuardarDetalle;

    private ClinicaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_detalle);

        dbHelper = new ClinicaDbHelper(this);

        // Referenciar vistas
        edtIdFactura = findViewById(R.id.edtIdFactura);
        edtMontoDetalle = findViewById(R.id.edtMontoDetalle);
        edtFormaPago = findViewById(R.id.edtFormaPago);
        btnGuardarDetalle = findViewById(R.id.btnAgregarDetalle);


    }

    private void guardarDetalleFactura() {
        String idFactura = edtIdFactura.getText().toString().trim();
        String montoStr = edtMontoDetalle.getText().toString().trim();
        String formaPago = edtFormaPago.getText().toString().trim();

        // Validaciones
        if (idFactura.isEmpty()) {
            edtIdFactura.setError("Ingrese el ID de la factura");
            return;
        }

        if (montoStr.isEmpty()) {
            edtMontoDetalle.setError("Ingrese el monto del detalle");
            return;
        }

        if (formaPago.isEmpty()) {
            edtFormaPago.setError("Ingrese la forma de pago");
            return;
        }

        try {
            double monto = Double.parseDouble(montoStr);

            long resultado = dbHelper.insertarDetalleFactura(idFactura, monto, formaPago);

            if (resultado != -1) {
                Toast.makeText(this, "Detalle guardado exitosamente", Toast.LENGTH_SHORT).show();
                limpiarCampos();
            } else {
                Toast.makeText(this, "Error al guardar el detalle", Toast.LENGTH_SHORT).show();
            }

        } catch (NumberFormatException e) {
            edtMontoDetalle.setError("Monto inválido");
            e.printStackTrace();
        }
    }

    private void limpiarCampos() {
        edtIdFactura.setText("");
        edtMontoDetalle.setText("");
        edtFormaPago.setText("");
        edtIdFactura.requestFocus();
    }

    @Override
    protected void onDestroy() {
        dbHelper.close();
        super.onDestroy();
    }
}