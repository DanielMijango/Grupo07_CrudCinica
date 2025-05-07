package com.example.grupo07_crudcinica.Paciente;


import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grupo07_crudcinica.ClinicaDbHelper;
import com.example.grupo07_crudcinica.R;

public class InsertarPacienteActivity extends AppCompatActivity {
    EditText etNombre, etApellido, etDui, etAseguradora;
    Button btnGuardar;
    ClinicaDbHelper dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_paciente);

        etNombre = findViewById(R.id.etNombrePaciente);
        etApellido = findViewById(R.id.etApellidoPaciente);
        etDui = findViewById(R.id.etDuiPaciente);
        etAseguradora = findViewById(R.id.etAseguradoraPaciente);
        btnGuardar = findViewById(R.id.btnGuardarPaciente);
        dbHelper = new ClinicaDbHelper(this);

        btnGuardar.setOnClickListener(v -> {
            String nombre = etNombre.getText().toString();
            String apellido = etApellido.getText().toString();
            String dui = etDui.getText().toString();
            String aseguradora = etAseguradora.getText().toString();

            long resultado = dbHelper.insertarPaciente(nombre, apellido, dui, aseguradora);
            if (resultado != -1) {
                Toast.makeText(this, "Paciente insertado correctamente", Toast.LENGTH_SHORT).show();
                etNombre.setText("");
                etApellido.setText("");
                etDui.setText("");
                etAseguradora.setText("");
            } else {
                Toast.makeText(this, "Error al insertar", Toast.LENGTH_SHORT).show();
            }
        });
    }
}