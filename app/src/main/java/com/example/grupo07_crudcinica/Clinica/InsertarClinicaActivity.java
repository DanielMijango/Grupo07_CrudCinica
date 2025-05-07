package com.example.grupo07_crudcinica.Clinica;

import android.os.Bundle;

import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.grupo07_crudcinica.R;

public class InsertarClinicaActivity extends AppCompatActivity {

    EditText edtId, edtNombre, edtDireccion;
    Button btnInsertar;
    ClinicaDAO clinicaDAO;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_clinica);

        edtId = findViewById(R.id.edtId);
        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion = findViewById(R.id.edtDireccion);
        btnInsertar = findViewById(R.id.btnInsertar);

        clinicaDAO = new ClinicaDAO(this);

        btnInsertar.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(edtId.getText().toString());
                String nombre = edtNombre.getText().toString();
                String direccion = edtDireccion.getText().toString();

                boolean insertado = clinicaDAO.insertarClinica(id, nombre, direccion);

                if (insertado) {
                    Toast.makeText(this, "Clínica insertada correctamente", Toast.LENGTH_SHORT).show();
                    edtId.setText("");
                    edtNombre.setText("");
                    edtDireccion.setText("");
                } else {
                    Toast.makeText(this, "Error: ID ya existe", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al insertar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}