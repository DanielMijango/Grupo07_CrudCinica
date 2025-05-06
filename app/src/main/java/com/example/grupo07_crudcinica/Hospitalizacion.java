package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Hospitalizacion extends AppCompatActivity {

    EditText editFechaIngreso, editFechaEgreso, editDuiPaciente, editIdHospital;
    Button btnGuardarHospitalizacion;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospitalizacion);

        editFechaIngreso = findViewById(R.id.editFechaIngreso);
        editFechaEgreso = findViewById(R.id.editFechaEgreso);
        editDuiPaciente = findViewById(R.id.editDuiPaciente);
        editIdHospital = findViewById(R.id.editIdHospital);
        btnGuardarHospitalizacion = findViewById(R.id.btnGuardar);

        btnGuardarHospitalizacion.setOnClickListener(v -> {
            String ingreso = editFechaIngreso.getText().toString();
            String egreso = editFechaEgreso.getText().toString();
            String dui = editDuiPaciente.getText().toString();
            String hospitalId = editIdHospital.getText().toString();

            // Aquí se pueden agregar validaciones o guardar los datos

            Toast.makeText(this, "Hospitalización guardada:\nDUI: " + dui, Toast.LENGTH_SHORT).show();
        });
    }
}
