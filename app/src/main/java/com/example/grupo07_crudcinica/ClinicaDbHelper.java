package com.example.grupo07_crudcinica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.database.Cursor;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;
import java.util.UUID;

public class ClinicaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Clinica.db";
    private static final int DATABASE_VERSION = 8; // Incrementé la versión por el cambio
    private Context context;

    public ClinicaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // Tabla Clinica
        db.execSQL("CREATE TABLE Clinica (" +
                "idClinica INTEGER PRIMARY KEY," +
                "nombre TEXT," +
                "direccion TEXT," +
                "id_distrito INTEGER," +
                "FOREIGN KEY (id_distrito) REFERENCES distrito(id_distrito));");

        // Tablas de ubicación
        db.execSQL("CREATE TABLE departamento (" +
                "id_departamento INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL);");

        db.execSQL("CREATE TABLE distrito (" +
                "id_distrito INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "id_departamento INTEGER," +
                "FOREIGN KEY (id_departamento) REFERENCES departamento(id_departamento));");

        db.execSQL("CREATE TABLE municipio (" +
                "id_municipio INTEGER PRIMARY KEY AUTOINCREMENT," +
                "nombre TEXT NOT NULL," +
                "id_distrito INTEGER," +
                "FOREIGN KEY (id_distrito) REFERENCES distrito(id_distrito));");

        // Tabla Especialidad
        db.execSQL("CREATE TABLE ESPECIALIDAD (" +
                "ID_ESPECIALIDAD CHAR(4) PRIMARY KEY," +
                "NOMBRE_ESPECIALIDAD VARCHAR(50));");

        // Tabla Doctor
        db.execSQL("CREATE TABLE DOCTOR (" +
                "ID_DOCTOR CHAR(4) PRIMARY KEY," +
                "NOMBRE_DOC VARCHAR(50)," +
                "APELLIDO_DOC VARCHAR(50));");

        // Tabla Paciente
        db.execSQL("CREATE TABLE PACIENTE (" +
                "ID_PACIENTE CHAR(4) PRIMARY KEY," +
                "ID_ASEGURADORA CHAR(4)," +
                "NOMBRE_PACIENTE VARCHAR(50)," +
                "APELLIDO_PACIENTE VARCHAR(50)," +
                "DUI_PACIENTE CHAR(12));");

        // Tabla Medicamento (NUEVA)
        db.execSQL("CREATE TABLE MEDICAMENTO (" +
                "ID_MEDICAMENTO INTEGER PRIMARY KEY AUTOINCREMENT," +
                "NOMBRE_MEDICAMENTO TEXT NOT NULL," +
                "FECHA_VENCIMIENTO TEXT NOT NULL," +
                "PRECIO_MEDICAMENTO REAL NOT NULL);");

        // Tabla RELATIONSHIP_14 (Medicamento-DetalleFactura)
        db.execSQL("CREATE TABLE RELATIONSHIP_14 (" +
                "ID_DETALLE TEXT NOT NULL," +
                "ID_MEDICAMENTO INTEGER NOT NULL," +
                "PRIMARY KEY (ID_DETALLE, ID_MEDICAMENTO)," +
                "FOREIGN KEY (ID_DETALLE) REFERENCES DETALLE_FACTURA(ID_DETALLE)," +
                "FOREIGN KEY (ID_MEDICAMENTO) REFERENCES MEDICAMENTO(ID_MEDICAMENTO));");

        cargarDatosDesdeJSON(db);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clinica;");
        db.execSQL("DROP TABLE IF EXISTS municipio;");
        db.execSQL("DROP TABLE IF EXISTS distrito;");
        db.execSQL("DROP TABLE IF EXISTS departamento;");
        db.execSQL("DROP TABLE IF EXISTS ESPECIALIDAD;");
        db.execSQL("DROP TABLE IF EXISTS DOCTOR;");
        db.execSQL("DROP TABLE IF EXISTS PACIENTE;");
        db.execSQL("DROP TABLE IF EXISTS MEDICAMENTO;");
        db.execSQL("DROP TABLE IF EXISTS RELATIONSHIP_14;");
        onCreate(db);
    }

    // ------------------------- CRUD ESPECIALIDAD -------------------------

    public Cursor obtenerEspecialidadPorId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM ESPECIALIDAD WHERE ID_ESPECIALIDAD = ?", new String[]{id});
    }
    public long insertarEspecialidad(String nombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_ESPECIALIDAD", generarId("ESP"));
        values.put("NOMBRE_ESPECIALIDAD", nombre);
        return db.insert("ESPECIALIDAD", null, values);
    }

    public Cursor consultarEspecialidades() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM ESPECIALIDAD", null);
    }

    public boolean actualizarEspecialidad(String id, String nuevoNombre) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE_ESPECIALIDAD", nuevoNombre);
        return db.update("ESPECIALIDAD", values, "ID_ESPECIALIDAD = ?", new String[]{id}) > 0;
    }

    public boolean eliminarEspecialidad(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("ESPECIALIDAD", "ID_ESPECIALIDAD = ?", new String[]{id}) > 0;
    }

    // ------------------------- CRUD DOCTOR -------------------------

    public Cursor obtenerDoctorPorId(String id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM DOCTOR WHERE ID_DOCTOR = ?", new String[]{id});
    }
    public long insertarDoctor(String nombre, String apellido) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_DOCTOR", generarId("DOC"));
        values.put("NOMBRE_DOC", nombre);
        values.put("APELLIDO_DOC", apellido);
        return db.insert("DOCTOR", null, values);
    }

    public Cursor consultarDoctores() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM DOCTOR", null);
    }

    public boolean actualizarDoctor(String id, String nuevoNombre, String nuevoApellido) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE_DOC", nuevoNombre);
        values.put("APELLIDO_DOC", nuevoApellido);
        return db.update("DOCTOR", values, "ID_DOCTOR = ?", new String[]{id}) > 0;
    }

    public boolean eliminarDoctor(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("DOCTOR", "ID_DOCTOR = ?", new String[]{id}) > 0;
    }

    // ------------------------- CRUD PACIENTE -------------------------
    public long insertarPaciente(String nombre, String apellido, String dui, String idAseguradora) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_PACIENTE", generarId("PAC"));
        values.put("NOMBRE_PACIENTE", nombre);
        values.put("APELLIDO_PACIENTE", apellido);
        values.put("DUI_PACIENTE", dui);
        values.put("ID_ASEGURADORA", idAseguradora);
        return db.insert("PACIENTE", null, values);
    }

    public Cursor consultarPacientes() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery("SELECT * FROM PACIENTE", null);
    }

    public boolean actualizarPaciente(String id, String nombre, String apellido, String dui, String idAseguradora) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE_PACIENTE", nombre);
        values.put("APELLIDO_PACIENTE", apellido);
        values.put("DUI_PACIENTE", dui);
        values.put("ID_ASEGURADORA", idAseguradora);
        return db.update("PACIENTE", values, "ID_PACIENTE = ?", new String[]{id}) > 0;
    }

    public boolean eliminarPaciente(String id) {
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete("PACIENTE", "ID_PACIENTE = ?", new String[]{id}) > 0;
    }

    // ------------------------- Utilidades -------------------------
    private String generarId(String prefijo) {
        return prefijo + String.format("%03d", (int)(Math.random() * 1000));
    }

    //--------------------------- CRUD MEDICAMENTO-------------------------
    public long insertarMedicamento(String nombre, String fechaVencimiento, double precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE_MEDICAMENTO", nombre);
        values.put("FECHA_VENCIMIENTO", fechaVencimiento);
        values.put("PRECIO_MEDICAMENTO", precio);

        long id = db.insert("MEDICAMENTO", null, values);
        db.close();
        return id;
    }

    public Cursor obtenerTodosMedicamentos() {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("MEDICAMENTO",
                new String[]{"ID_MEDICAMENTO", "NOMBRE_MEDICAMENTO", "FECHA_VENCIMIENTO", "PRECIO_MEDICAMENTO"},
                null, null, null, null,
                "NOMBRE_MEDICAMENTO ASC");
    }

    public Cursor obtenerMedicamentoPorId(long id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("MEDICAMENTO",
                new String[]{"ID_MEDICAMENTO", "NOMBRE_MEDICAMENTO", "FECHA_VENCIMIENTO", "PRECIO_MEDICAMENTO"},
                "ID_MEDICAMENTO = ?",
                new String[]{String.valueOf(id)},
                null, null, null);
    }

    public int actualizarMedicamento(long id, String nombre, String fechaVencimiento, double precio) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NOMBRE_MEDICAMENTO", nombre);
        values.put("FECHA_VENCIMIENTO", fechaVencimiento);
        values.put("PRECIO_MEDICAMENTO", precio);

        int rowsAffected = db.update("MEDICAMENTO", values,
                "ID_MEDICAMENTO = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return rowsAffected;
    }


    public int eliminarMedicamento(long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        int rowsDeleted = db.delete("MEDICAMENTO",
                "ID_MEDICAMENTO = ?",
                new String[]{String.valueOf(id)});
        db.close();
        return rowsDeleted;
    }


    public Cursor buscarMedicamentosPorNombre(String nombre) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.query("MEDICAMENTO",
                new String[]{"ID_MEDICAMENTO", "NOMBRE_MEDICAMENTO", "FECHA_VENCIMIENTO", "PRECIO_MEDICAMENTO"},
                "NOMBRE_MEDICAMENTO LIKE ?",
                new String[]{"%" + nombre + "%"},
                null, null,
                "NOMBRE_MEDICAMENTO ASC");
    }


    public int contarMedicamentos() {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM MEDICAMENTO", null);
        cursor.moveToFirst();
        int count = cursor.getInt(0);
        cursor.close();
        return count;
    }


    public boolean agregarMedicamentoADetalle(String idDetalle, long idMedicamento) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ID_DETALLE", idDetalle);
        values.put("ID_MEDICAMENTO", idMedicamento);

        long result = db.insert("RELATIONSHIP_14", null, values);
        db.close();
        return result != -1;
    }


    public Cursor obtenerMedicamentosPorDetalle(String idDetalle) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT M.* FROM MEDICAMENTO M " +
                "INNER JOIN RELATIONSHIP_14 R ON M.ID_MEDICAMENTO = R.ID_MEDICAMENTO " +
                "WHERE R.ID_DETALLE = ?";
        return db.rawQuery(query, new String[]{idDetalle});
    }

    // ------------------------- Cargar ubicaciones desde JSON -------------------------
    private void cargarDatosDesdeJSON(SQLiteDatabase db) {
        try {
            InputStream is = context.getAssets().open("el_salvador.json");
            BufferedReader reader = new BufferedReader(new InputStreamReader(is));
            StringBuilder jsonBuilder = new StringBuilder();
            String line;

            while ((line = reader.readLine()) != null) {
                jsonBuilder.append(line);
            }
            reader.close();

            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());
            JSONArray departamentosArray = jsonObject.getJSONArray("departamentos");

            for (int i = 0; i < departamentosArray.length(); i++) {
                JSONObject departamentoObj = departamentosArray.getJSONObject(i);
                String nombreDepartamento = departamentoObj.getString("nombre");

                ContentValues deptoValues = new ContentValues();
                deptoValues.put("nombre", nombreDepartamento);
                long departamentoId = db.insert("departamento", null, deptoValues);

                JSONArray distritosArray = departamentoObj.getJSONArray("municipios");

                for (int j = 0; j < distritosArray.length(); j++) {
                    JSONObject distritoObj = distritosArray.getJSONObject(j);
                    String nombreDistrito = distritoObj.getString("nombre");

                    ContentValues distritoValues = new ContentValues();
                    distritoValues.put("nombre", nombreDistrito);
                    distritoValues.put("id_departamento", departamentoId);
                    long distritoId = db.insert("distrito", null, distritoValues);

                    JSONArray municipiosArray = distritoObj.getJSONArray("distritos");

                    for (int k = 0; k < municipiosArray.length(); k++) {
                        String nombreMunicipio = municipiosArray.getString(k);

                        ContentValues municipioValues = new ContentValues();
                        municipioValues.put("nombre", nombreMunicipio);
                        municipioValues.put("id_distrito", distritoId);
                        db.insert("municipio", null, municipioValues);
                    }
                }
            }
        } catch (IOException | JSONException e) {
            e.printStackTrace();
        }
    }

    // Alias para mantener compatibilidad con ConsultarEspecialidadActivity
    public Cursor obtenerTodasEspecialidades() {
        return consultarEspecialidades();
    }

}
