package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class PacienteActivity extends AppCompatActivity {

    EditText etNombrePaciente, etEdad, etGenero;
    Button btnGuardar, btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_paciente);

        etNombrePaciente = findViewById(R.id.etNombrePaciente);
        etEdad = findViewById(R.id.etEdad);
        etGenero = findViewById(R.id.etGenero);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(v -> finish());
    }
}