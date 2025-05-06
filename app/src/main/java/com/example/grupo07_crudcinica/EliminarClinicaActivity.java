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

public class EliminarClinicaActivity extends AppCompatActivity {

    EditText edtIdClinica, edtNombre, edtDireccion;
    Button btnBuscar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eliminar_clinica);

        edtIdClinica = findViewById(R.id.edtIdClinica);
        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion = findViewById(R.id.edtDireccion);
        btnBuscar = findViewById(R.id.btnBuscar);
        btnEliminar = findViewById(R.id.btnEliminar);

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

        btnEliminar.setOnClickListener(v -> {
            String id = edtIdClinica.getText().toString();
            Toast.makeText(this, "Clínica con ID " + id + " eliminada (simulado)", Toast.LENGTH_LONG).show();
            edtNombre.setText("");
            edtDireccion.setText("");
        });
    }
}