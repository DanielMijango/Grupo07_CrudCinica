package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Hospital extends AppCompatActivity {

    EditText editNombre, editDireccion, editIdDist;
    Button btnGuardarHospital;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hospital);

        editNombre = findViewById(R.id.editNombre);
        editDireccion = findViewById(R.id.editDireccion);
        editIdDist = findViewById(R.id.editIdDist);
        btnGuardarHospital = findViewById(R.id.btnGuardarHospital);

        btnGuardarHospital.setOnClickListener(v -> {
            String nombre = editNombre.getText().toString();
            String direccion = editDireccion.getText().toString();
            String idDist = editIdDist.getText().toString();

            // Solo muestra los datos como prueba
            Toast.makeText(this,
                    "Nombre: " + nombre + "\nDirección: " + direccion + "\nIdDist: " + idDist,
                    Toast.LENGTH_LONG).show();
        });
    }
}
