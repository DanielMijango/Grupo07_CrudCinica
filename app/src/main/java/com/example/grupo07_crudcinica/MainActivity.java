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
    Button btnClinica, btnEspecialidad, btnDoctor, btnPaciente, btnConsulta, btnReceta, btnMedicamento, btnSeguro, btnHospital, btnDetalleReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnClinica = findViewById(R.id.btnClinica);
        btnEspecialidad = findViewById(R.id.btnEspecialidad);
        btnDoctor = findViewById(R.id.btnDoctor);
        btnPaciente = findViewById(R.id.btnPaciente);
        btnConsulta = findViewById(R.id.btnConsulta);
        btnReceta = findViewById(R.id.btnReceta);
        btnMedicamento = findViewById(R.id.btnMedicamento);
        btnSeguro = findViewById(R.id.btnSeguro);
        btnHospital = findViewById(R.id.btnHospital);
        btnDetalleReceta = findViewById(R.id.btnDetalleReceta);

        btnClinica.setOnClickListener(v -> startActivity(new Intent(this, ClinicaMenuActivity.class)));
        // Los demás aún no están implementados, los agregaremos después
    }
}