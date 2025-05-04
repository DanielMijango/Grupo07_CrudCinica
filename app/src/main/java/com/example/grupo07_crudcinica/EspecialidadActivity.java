package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class EspecialidadActivity extends AppCompatActivity {

    EditText etNombreEspecialidad;
    Button btnGuardar, btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_especialidad);

        etNombreEspecialidad = findViewById(R.id.etNombreEspecialidad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(v -> finish());
    }
}