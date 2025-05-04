package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class DoctorActivity extends AppCompatActivity {

    EditText etNombreDoctor, etIdEspecialidad;
    Button btnGuardar, btnAtras;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_doctor);

        etNombreDoctor = findViewById(R.id.etNombreDoctor);
        etIdEspecialidad = findViewById(R.id.etIdEspecialidad);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(v -> finish());
    }
}