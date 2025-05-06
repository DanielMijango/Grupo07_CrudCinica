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

public class InsertarClinicaActivity extends AppCompatActivity {

    EditText edtIdClinica, edtNombre, edtDireccion;
    Button btnGuardar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_clinica);

        edtIdClinica = findViewById(R.id.edtIdClinica);
        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion = findViewById(R.id.edtDireccion);
        btnGuardar = findViewById(R.id.btnGuardar);

        btnGuardar.setOnClickListener(v -> {
            String id = edtIdClinica.getText().toString();
            String nombre = edtNombre.getText().toString();
            String direccion = edtDireccion.getText().toString();

            // Solo mostramos los datos como validación temporal
            Toast.makeText(this, "Clínica insertada:\nID: " + id + "\nNombre: " + nombre + "\nDirección: " + direccion, Toast.LENGTH_LONG).show();

            // Aquí luego agregaremos la lógica para guardar en SQLite o API
        });
    }
}