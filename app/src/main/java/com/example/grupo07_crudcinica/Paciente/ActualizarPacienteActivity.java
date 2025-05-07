package com.example.grupo07_crudcinica.Paciente;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grupo07_crudcinica.ClinicaDbHelper;
import com.example.grupo07_crudcinica.R;
import java.util.ArrayList;

public class ActualizarPacienteActivity extends AppCompatActivity {
    Spinner spinnerPaciente;
    EditText etNombre, etApellido, etDui, etAseguradora;
    Button btnActualizar;
    ClinicaDbHelper dbHelper;
    ArrayList<String> listaIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actualizar_paciente);

        spinnerPaciente = findViewById(R.id.spinnerPaciente);
        etNombre = findViewById(R.id.etNombrePaciente);
        etApellido = findViewById(R.id.etApellidoPaciente);
        etDui = findViewById(R.id.etDuiPaciente);
        etAseguradora = findViewById(R.id.etAseguradoraPaciente);
        btnActualizar = findViewById(R.id.btnActualizarPaciente);
        dbHelper = new ClinicaDbHelper(this);

        cargarPacientes();

        spinnerPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String idPaciente = listaIds.get(pos);
                Cursor c = dbHelper.obtenerPacientePorId(idPaciente);
                if (c.moveToFirst()) {
                    etNombre.setText(c.getString(1));
                    etApellido.setText(c.getString(2));
                    etDui.setText(c.getString(3));
                    etAseguradora.setText(c.getString(4));
                }
                c.close();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
        });

        btnActualizar.setOnClickListener(v -> {
            String id = listaIds.get(spinnerPaciente.getSelectedItemPosition());
            String nombre = etNombre.getText().toString();
            String apellido = etApellido.getText().toString();
            String dui = etDui.getText().toString();
            String aseguradora = etAseguradora.getText().toString();

            boolean resultado = dbHelper.actualizarPaciente(id, nombre, apellido, dui, aseguradora);
            if (resultado) {
                Toast.makeText(this, "Paciente actualizado correctamente", Toast.LENGTH_SHORT).show();
            } else {
                Toast.makeText(this, "Error al actualizar paciente", Toast.LENGTH_SHORT).show();
            }
        });
    }

    private void cargarPacientes() {
        Cursor cursor = dbHelper.consultarPacientes();
        ArrayList<String> nombres = new ArrayList<>();
        while (cursor.moveToNext()) {
            listaIds.add(cursor.getString(0));
            nombres.add(cursor.getString(1) + " " + cursor.getString(2));
        }
        spinnerPaciente.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, nombres));
    }
}
