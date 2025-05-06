package com.example.grupo07_crudcinica;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class ClinicaMenuActivity extends AppCompatActivity {

    Button btnInsertar, btnConsultar, btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clinica_menu);

        btnInsertar = findViewById(R.id.btnInsertar);
        btnConsultar = findViewById(R.id.btnConsultar);
        btnActualizar = findViewById(R.id.btnActualizar);
        btnEliminar = findViewById(R.id.btnEliminar);

        btnInsertar.setOnClickListener(v -> startActivity(new Intent(this, InsertarClinicaActivity.class)));
        btnConsultar.setOnClickListener(v -> startActivity(new Intent(this, ConsultarClinicaActivity.class)));
        btnActualizar.setOnClickListener(v -> startActivity(new Intent(this, ActualizarClinicaActivity.class)));
        btnEliminar.setOnClickListener(v -> startActivity(new Intent(this, EliminarClinicaActivity.class)));
    }
}