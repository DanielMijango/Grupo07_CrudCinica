package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ConsultaActivity extends AppCompatActivity {

    EditText etFechaConsulta,etEmergencia,etDiagnosticoConsulta,etCuotaConsulta;
    Button btnGuardar, btnAtras;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_consulta);

        etFechaConsulta = findViewById(R.id.etFechaConsulta);
        etEmergencia = findViewById(R.id.etEmergencia);
        etCuotaConsulta=findViewById(R.id.etCuotaConsulta);
        etDiagnosticoConsulta = findViewById(R.id.etDiagnosticoConsulta);
        btnGuardar = findViewById(R.id.btnGuardar);
        btnAtras = findViewById(R.id.btnAtras);

        btnAtras.setOnClickListener(v -> finish());

    }
}