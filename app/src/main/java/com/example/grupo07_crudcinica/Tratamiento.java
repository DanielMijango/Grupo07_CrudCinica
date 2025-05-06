package com.example.grupo07_crudcinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Tratamiento extends AppCompatActivity {

    EditText editDescripcion, editFechaTratamiento, editIdConsulta;
    Button btnGuardarTratamiento;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tratamiento);

        editDescripcion = findViewById(R.id.editDescripcion);
        editFechaTratamiento = findViewById(R.id.editFechaTratamiento);
        editIdConsulta = findViewById(R.id.editIdConsulta);
        btnGuardarTratamiento = findViewById(R.id.btnGuardarTratamiento);

        btnGuardarTratamiento.setOnClickListener(v -> {
            String descripcion = editDescripcion.getText().toString();
            String fecha = editFechaTratamiento.getText().toString();
            String idConsulta = editIdConsulta.getText().toString();

            // Mostrar los datos como prueba
            Toast.makeText(this,
                    "Descripción: " + descripcion + "\nFecha: " + fecha + "\nID Consulta: " + idConsulta,
                    Toast.LENGTH_LONG).show();
        });
    }
}
