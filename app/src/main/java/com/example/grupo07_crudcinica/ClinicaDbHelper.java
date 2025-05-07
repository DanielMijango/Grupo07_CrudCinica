package com.example.grupo07_crudcinica;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import android.util.Log;


public class ClinicaDbHelper extends SQLiteOpenHelper {

    private static final String DATABASE_NAME = "Clinica.db";
    private static final int DATABASE_VERSION = 5;

    private Context context;

    public ClinicaDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
        this.context = context; //
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sql = "CREATE TABLE Clinica (" +
                "idClinica INTEGER PRIMARY KEY," +
                "nombre TEXT," +
                "direccion TEXT," +
                "id_distrito INTEGER," +
                "FOREIGN KEY (id_distrito) REFERENCES distrito(id_distrito));";
        db.execSQL(sql);
        //Agregando tablas de departamento,distrito y municipio
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
        cargarDatosDesdeJSON(db);
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS Clinica");
        db.execSQL("DROP TABLE IF EXISTS municipio;");
        db.execSQL("DROP TABLE IF EXISTS distrito;");
        db.execSQL("DROP TABLE IF EXISTS departamento;");
        onCreate(db);
    }

//Carga Municipios,Departamentos y Distritos
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

            // Verifica si el JSON está bien cargado
            //Log.d("DBHELPER", "Contenido del JSON: " + jsonBuilder.toString());

            // Convierte el contenido del StringBuilder a JSONObject
            JSONObject jsonObject = new JSONObject(jsonBuilder.toString());

            // Extrae el array de departamentos del JSON
            JSONArray departamentosArray = jsonObject.getJSONArray("departamentos");

            // Procesa el array de departamentos
            for (int i = 0; i < departamentosArray.length(); i++) {
                JSONObject departamentoObj = departamentosArray.getJSONObject(i);
                String nombreDepartamento = departamentoObj.getString("nombre");

                ContentValues deptoValues = new ContentValues();
                deptoValues.put("nombre", nombreDepartamento);
                long departamentoId = db.insert("departamento", null, deptoValues);

                // Procesa distritos
                JSONArray distritosArray = departamentoObj.getJSONArray("municipios");

                for (int j = 0; j < distritosArray.length(); j++) {
                    JSONObject distritoObj = distritosArray.getJSONObject(j);
                    String nombreDistrito = distritoObj.getString("nombre");

                    ContentValues distritoValues = new ContentValues();
                    distritoValues.put("nombre", nombreDistrito);
                    distritoValues.put("id_departamento", departamentoId);
                    long distritoId = db.insert("distrito", null, distritoValues);

                    // Procesa municipios
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


}