package com.example.grupo07_crudcinica.Doctor;

import android.os.Bundle;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.grupo07_crudcinica.R;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grupo07_crudcinica.ClinicaDbHelper;
import com.example.grupo07_crudcinica.R;

public class InsertarDoctorActivity extends AppCompatActivity {

    EditText etNombre, etApellido;
    Button btnGuardar;
    ClinicaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_doctor);

        etNombre = findViewById(R.id.etNombreDoctor);
        etApellido = findViewById(R.id.etApellidoDoctor);
        btnGuardar = findViewById(R.id.btnGuardarDoctor);
        dbHelper = new ClinicaDbHelper(this);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String apellido = etApellido.getText().toString();
            if (!nombre.isEmpty() && !apellido.isEmpty()) {
                long resultado = dbHelper.insertarDoctor(nombre, apellido);
                if (resultado != -1) {
                    Toast.makeText(this, "Doctor insertado correctamente", Toast.LENGTH_SHORT).show();
                    etNombre.setText("");
                    etApellido.setText("");
                } else {
                    Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show();
                }
            } else {
                Toast.makeText(this, "Complete todos los campos", Toast.LENGTH_SHORT).show();
            }
        });
    }
}