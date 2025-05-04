package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class ClinicaActivity extends AppCompatActivity {

    EditText etNombreClinica, etIdDistrito;
    Button btnGuardar, btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica);

        etNombreClinica = findViewById(R.id.etNombreClinica);
        etIdDistrito = findViewById(R.id.etIdDistrito);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(v -> finish());
    }
}
