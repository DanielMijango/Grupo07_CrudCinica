package com.example.grupo07_crudcinica.DetalleFactura;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.grupo07_crudcinica.Medicamento.ActualizarMedicamento;
import com.example.grupo07_crudcinica.Medicamento.ConsultarMedicamento;
import com.example.grupo07_crudcinica.Medicamento.EliminarMedicamento;
import com.example.grupo07_crudcinica.Medicamento.InsertarMedicamento;
import com.example.grupo07_crudcinica.R;

public class DetalleFacturaMenuActivity extends AppCompatActivity {

    Button btnInsertar, btnConsultar, btnActualizar, btnEliminar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_detalle_factura_menu);

        btnInsertar = findViewById(R.id.btnInsertarDetalle);
        btnConsultar = findViewById(R.id.btnConsultarDetalle);
        btnActualizar = findViewById(R.id.btnActualizarDetalle);
        btnEliminar = findViewById(R.id.btnEliminarDetalle);


        btnInsertar.setOnClickListener(v -> startActivity(new Intent(this, InsertarDetalle.class)));
        btnConsultar.setOnClickListener(v -> startActivity(new Intent(this, ConsultarDetalle.class)));
        btnActualizar.setOnClickListener(v -> startActivity(new Intent(this, ActualizarDetalle.class)));
        btnEliminar.setOnClickListener(v -> startActivity(new Intent(this, EliminarDetalle.class)));

    }
}