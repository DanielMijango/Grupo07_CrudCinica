package com.example.grupo07_crudcinica;

import android.os.Bundle;

import com.google.android.material.snackbar.Snackbar;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;

import androidx.navigation.NavController;
import androidx.navigation.Navigation;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.example.grupo07_crudcinica.databinding.ActivityMainBinding;

import android.view.Menu;
import android.view.MenuItem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    Button btnClinica, btnEspecialidad, btnDoctor, btnPaciente, btnHospitalizacion, btnHospital, btnTratamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Inicializar botones
        btnClinica = findViewById(R.id.btnClinica);
        btnEspecialidad = findViewById(R.id.btnEspecialidad);
        btnDoctor = findViewById(R.id.btnDoctor);
        btnPaciente = findViewById(R.id.btnPaciente);
        btnHospitalizacion = findViewById(R.id.btnHospitalizacion);
        btnHospital = findViewById(R.id.btnHospital);
        btnTratamiento = findViewById(R.id.btnTratamiento);

        // Ir a la pantalla de Clínica
        btnClinica.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, ClinicaActivity.class);
            startActivity(intent);
        });

        // Ir a la pantalla de Especialidad
        btnEspecialidad.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, EspecialidadActivity.class);
            startActivity(intent);
        });

        // Ir a la pantalla de Doctor
        btnDoctor.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, DoctorActivity.class);
            startActivity(intent);
        });

        // Ir a la pantalla de Paciente
        btnPaciente.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
            startActivity(intent);
        });

        // Ir a la pantalla de hospitalizacion
        btnHospitalizacion.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
            startActivity(intent);
        });

        // Ir a la pantalla de hospital
        btnHospital.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
            startActivity(intent);
        });

        // Ir a la pantalla de Tratamiento
        btnTratamiento.setOnClickListener(v -> {
            Intent intent = new Intent(MainActivity.this, PacienteActivity.class);
            startActivity(intent);
        });

    }
}