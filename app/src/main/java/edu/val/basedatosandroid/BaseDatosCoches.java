package edu.val.basedatosandroid;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

import java.util.ArrayList;
import java.util.List;

public class BaseDatosCoches extends SQLiteOpenHelper {

    private static final String SQL_CREAR_TABLA_PERSONAS = "CREATE TABLE PERSONA (id INTEGER PRIMARY KEY, nombre TEXT)";
    private static final String SQL_CREAR_TABLA_COCHES = "CREATE TABLE COCHE (id INTEGER PRIMARY KEY AUTOINCREMENT, modelo TEXT, idpersona INTEGER, FOREIGN KEY (idpersona) REFERENCES PERSONA (id))";


    public BaseDatosCoches(@Nullable Context context, @Nullable String name, @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        //incluir las instrucciones para crear las tablas
        //IMPORTANTE EL ORDEN
        sqLiteDatabase.execSQL(SQL_CREAR_TABLA_PERSONAS);
        sqLiteDatabase.execSQL(SQL_CREAR_TABLA_COCHES);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        //1 leer los datos de las tablas
        //2 crear las nuevas tablas
        //3 escribir los datos del paso 1
    }

    //insertarPersona
    public void insertarPersona (Persona persona)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase(); //INSERT INTO PERSONA (id, nombre) VALUES (3, 'JAVI');
        //importante: AL INTRODUCIR EL VALOR DE UNA COLUMNA TIPO TEXT, (STRING), TIENE QUE ENTRE COMILLAS SIMPLES
        String instruccion_sql_inertar_persona = "INSERT INTO PERSONA (id, nombre) VALUES ("+persona.getId()+", '"+persona.getNombre()+"')";
        Log.d("ETIQUETA_LOG", "INSERTANDO persona = " + instruccion_sql_inertar_persona);
        sqLiteDatabase.execSQL(instruccion_sql_inertar_persona);
        cerrarBaseDatos(sqLiteDatabase);
    }

    //insertarCoche
    public void insertarCoche (Coche coche)
    {
        SQLiteDatabase sqLiteDatabase = getWritableDatabase();
        String instruccion_sql_insertar_coche = "INSERT INTO COCHE (modelo, idpersona) VALUES ('"+coche.getModelo()+"', "+coche.getPersona().getId()+")";
        Log.d("ETIQUETA_LOG", "INSERTANDO coche = " + instruccion_sql_insertar_coche);
        sqLiteDatabase.execSQL(instruccion_sql_insertar_coche);
        cerrarBaseDatos(sqLiteDatabase);
    }

    //cerrarBaseDatos

    private void cerrarBaseDatos (SQLiteDatabase sqLiteDatabase)
    {
        sqLiteDatabase.close();
    }

    ////obtenerCochesPersona SELECT - leer
    public List<Coche> obtenerCochesPersona (Persona persona)
    {
        List<Coche> lista_coches = null;
        SQLiteDatabase sqLiteDatabase = null;
        String instruccion_consulta = "SELECT modelo  FROM COCHE WHERE idpersona = " + persona.getId();
        Cursor cursor = null;//objeto que me permite recorrer los resultados de una consulta
        String modelo_aux = null;
        Coche coche_aux = null;

            sqLiteDatabase = this.getReadableDatabase();//obtengo la Base de datos en modo lectura
            cursor = sqLiteDatabase.rawQuery(instruccion_consulta, null);
            if (cursor !=null && cursor.getCount()>0) //si la consulta ha recuperado algún registro
            {
                Log.d("ETIQUETA_LOG", "La consulta ha recuperado datos");
                cursor.moveToFirst();
                lista_coches = new ArrayList<>(cursor.getCount());
                //tengo que ir leyendo los coches
                //bucle de 1 a N
                do {
                    modelo_aux = cursor.getString(0);//0 es la primera columna de la consulta -modelo en nuestro caso-
                    coche_aux = new Coche(modelo_aux);
                    lista_coches.add(coche_aux);

                }while (cursor.moveToNext());

                cursor.close();
            }
            cerrarBaseDatos(sqLiteDatabase);

        return lista_coches;
    }

}
