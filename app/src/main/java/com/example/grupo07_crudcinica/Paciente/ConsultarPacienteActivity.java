package com.example.grupo07_crudcinica.Paciente;

import android.os.Bundle;
import android.view.View;
import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.grupo07_crudcinica.R;

import android.os.Bundle;
import android.widget.*;
import androidx.appcompat.app.AppCompatActivity;
import android.database.Cursor;
import com.example.grupo07_crudcinica.ClinicaDbHelper;
import com.example.grupo07_crudcinica.R;

import java.util.ArrayList;

public class ConsultarPacienteActivity extends AppCompatActivity {
    Spinner spinnerPaciente;
    TextView tvResultado;
    ClinicaDbHelper dbHelper;
    ArrayList<String> listaIds = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_consultar_paciente);

        spinnerPaciente = findViewById(R.id.spinnerPaciente);
        tvResultado = findViewById(R.id.tvResultadoPaciente);
        dbHelper = new ClinicaDbHelper(this);

        cargarPacientes();

        spinnerPaciente.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            public void onItemSelected(AdapterView<?> parent, View view, int pos, long id) {
                String idPaciente = listaIds.get(pos);
                Cursor c = dbHelper.obtenerPacientePorId(idPaciente);
                if (c.moveToFirst()) {
                    String nombre = c.getString(1);
                    String apellido = c.getString(2);
                    String dui = c.getString(3);
                    String aseg = c.getString(4);
                    tvResultado.setText("Nombre: " + nombre + "\nApellido: " + apellido + "\nDUI: " + dui + "\nAseguradora: " + aseg);
                }
                c.close();
            }

            public void onNothingSelected(AdapterView<?> parent) {}
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