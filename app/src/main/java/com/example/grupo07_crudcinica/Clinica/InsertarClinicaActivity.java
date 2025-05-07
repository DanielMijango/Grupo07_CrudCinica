package com.example.grupo07_crudcinica.Clinica;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import com.example.grupo07_crudcinica.R;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class InsertarClinicaActivity extends AppCompatActivity {

    EditText edtId, edtNombre, edtDireccion;
    Spinner spinnerDistrito;
    Button btnInsertar;
    ClinicaDAO clinicaDAO;

    List<Distrito> listaDistritos;
    Map<String, Integer> distritoMap;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_insertar_clinica);

        edtId = findViewById(R.id.edtId);
        edtNombre = findViewById(R.id.edtNombre);
        edtDireccion = findViewById(R.id.edtDireccion);
        spinnerDistrito = findViewById(R.id.spinnerDistrito);
        btnInsertar = findViewById(R.id.btnInsertar);

        clinicaDAO = new ClinicaDAO(this);

        // Cargar distritos
        listaDistritos = clinicaDAO.obtenerDistritos();
        List<String> nombresDistritos = new ArrayList<>();
        distritoMap = new HashMap<>();

        for (Distrito d : listaDistritos) {
            nombresDistritos.add(d.getNombre());
            distritoMap.put(d.getNombre(), d.getId());
        }

        ArrayAdapter<String> adapterDistritos = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, nombresDistritos);
        adapterDistritos.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerDistrito.setAdapter(adapterDistritos);

        btnInsertar.setOnClickListener(v -> {
            try {
                int id = Integer.parseInt(edtId.getText().toString());
                String nombre = edtNombre.getText().toString();
                String direccion = edtDireccion.getText().toString();

                String nombreDistrito = spinnerDistrito.getSelectedItem().toString();
                int idDistrito = distritoMap.get(nombreDistrito);

                boolean insertado = clinicaDAO.insertarClinica(id, nombre, direccion, idDistrito);

                if (insertado) {
                    Toast.makeText(this, "Clínica insertada correctamente", Toast.LENGTH_SHORT).show();
                    edtId.setText("");
                    edtNombre.setText("");
                    edtDireccion.setText("");
                    spinnerDistrito.setSelection(0);
                } else {
                    Toast.makeText(this, "Error: ID ya existe", Toast.LENGTH_SHORT).show();
                }
            } catch (Exception e) {
                Toast.makeText(this, "Error al insertar: " + e.getMessage(), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
