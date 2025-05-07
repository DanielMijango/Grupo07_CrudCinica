package com.example.grupo07_crudcinica.Clinica;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.grupo07_crudcinica.ClinicaDbHelper;

import java.util.ArrayList;
import java.util.List;

public class ClinicaDAO {

    private ClinicaDbHelper dbHelper;

    public ClinicaDAO(Context context) {
        dbHelper = new ClinicaDbHelper(context);
    }

    // Insertar una clínica con distrito
    public boolean insertarClinica(int id, String nombre, String direccion, int idDistrito) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("idClinica", id);
        valores.put("nombre", nombre);
        valores.put("direccion", direccion);
        valores.put("id_distrito", idDistrito);

        long resultado = db.insert("Clinica", null, valores);
        db.close();
        return resultado != -1;
    }

    // Consultar por ID
    public Cursor consultarClinicaPorId(int id) {
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        return db.rawQuery("SELECT * FROM Clinica WHERE idClinica = ?", new String[]{String.valueOf(id)});
    }

    // Actualizar clínica
    public boolean actualizarClinica(int id, String nuevoNombre, String nuevaDireccion) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues valores = new ContentValues();
        valores.put("nombre", nuevoNombre);
        valores.put("direccion", nuevaDireccion);
        // puedes actualizar id_distrito si lo deseas también

        int filas = db.update("Clinica", valores, "idClinica = ?", new String[]{String.valueOf(id)});
        db.close();
        return filas > 0;
    }

    // Eliminar clínica
    public boolean eliminarClinica(int id) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        int filas = db.delete("Clinica", "idClinica = ?", new String[]{String.valueOf(id)});
        db.close();
        return filas > 0;
    }

    // Obtener lista de distritos
    public List<Distrito> obtenerDistritos() {
        List<Distrito> lista = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT id_distrito, nombre FROM distrito", null);

        if (cursor.moveToFirst()) {
            do {
                int id = cursor.getInt(0);
                String nombre = cursor.getString(1);
                lista.add(new Distrito(id, nombre));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return lista;
    }
}
