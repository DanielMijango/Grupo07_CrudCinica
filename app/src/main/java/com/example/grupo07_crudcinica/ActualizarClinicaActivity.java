package com.example.grupo07_crudcinica;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ActualizarClinicaActivity extends AppCompatActivity {

    EditText edtIdClinica, edtNombre, edtDireccion;
    Button btnBuscar, btnActualizar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_clinica);

        edtIdClinica = findViewById(R.id.edtIdClinica);
        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion = findViewById(R.id.edtDireccion);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnActualizar = findViewById(R.id.btnActualizar);

        btnBuscar.setOnClickListener(v -> {
            String id = edtIdClinica.getText().toString();
            if (id.equals("1")) {
                edtNombre.setText("Clínica Central");
                edtDireccion.setText("Av. Principal #123");
                Toast.makeText(this, "Clínica encontrada", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Clínica no encontrada", Toast.LENGTH_SHORT).show();
                edtNombre.setText("");
                edtDireccion.setText("");
            }
        });

        btnActualizar.setOnClickListener(v -> {
            String id = edtIdClinica.getText().toString();
            String nombre = edtNombre.getText().toString();
            String direccion = edtDireccion.getText().toString();

            // Simulación de actualización
            Toast.makeText(this, "Clínica actualizada:\nID: " + id + "\nNombre: " + nombre + "\nDirección: " + direccion, Toast.LENGTH_LONG).show();
        });
    }
}