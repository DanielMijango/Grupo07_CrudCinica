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

public class ConsultarClinicaActivity extends AppCompatActivity {

    EditText edtIdClinica, edtNombre, edtDireccion;
    Button btnBuscar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_clinica);

        edtIdClinica = findViewById(R.id.edtIdClinica);
        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion = findViewById(R.id.edtDireccion);
        btnBuscar = findViewById(R.id.btnBuscar);

        btnBuscar.setOnClickListener(v -> {
            String id = edtIdClinica.getText().toString();

            // Aquí simulamos datos encontrados
            if (id.equals("1")) {
                edtNombre.setText("Clínica Central");
                edtDireccion.setText("Av. Principal #123");
            } else {
                Toast.makeText(this, "Clínica no encontrada", Toast.LENGTH_SHORT).show();
                edtNombre.setText("");
                edtDireccion.setText("");
            }
        });
    }
}