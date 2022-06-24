package edu.val.basedatosandroid;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BaseDatosCoches baseDatosCoches;
    private Integer[] ids_persona = {1, 2, 3};
    private Spinner spinner;
    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        this.recyclerView = findViewById(R.id.recicler_view);

        baseDatosCoches = new BaseDatosCoches(this, "BDCOCHE", null, 1);

        this.spinner = findViewById(R.id.spinner_longitud);
        ArrayAdapter<Integer> arrayAdapter = new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item, ids_persona);
        arrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        this.spinner.setAdapter(arrayAdapter);
    }

    public void insertarDatos(View view) {
        Persona persona1 = new Persona(1, "Conchi");
        Persona persona2 = new Persona(2, "Manolo");
        Persona persona3 = new Persona(3, "Paco");

        baseDatosCoches.insertarPersona(persona1);
        baseDatosCoches.insertarPersona(persona2);
        baseDatosCoches.insertarPersona(persona3);

        Log.d("ETIQUETA_LOG", "PERSONAS INSERTADAS");

        Coche coche = new Coche("FERRARI", persona1);
        Coche coche1 = new Coche("SEAT", persona1);
        Coche coche2 = new Coche("RENAULT", persona2);

        baseDatosCoches.insertarCoche(coche);
        baseDatosCoches.insertarCoche(coche1);
        baseDatosCoches.insertarCoche(coche2);

        Log.d("ETIQUETA_LOG", "COCHES INSERTADOS");
    }

    public void leerDatos(View view) {
//leer el spinner
     String id_seleccionado = this.spinner.getSelectedItem().toString();
     Persona persona = new Persona();
     persona.setId(Integer.parseInt(id_seleccionado));
     Log.d("ETIQUETA_LOG", "buscando los coches de la persona con id = " + id_seleccionado);
     List<Coche> lista_coches =  baseDatosCoches.obtenerCochesPersona(persona);


     if (null!=lista_coches)
     {
         Log.d("ETIQUETA_LOG", "La consulta ha recuperado coches");
         for (Coche c : lista_coches)
         {
             Log.d("ETIQUETA_LOG", "Coche = " + c.getModelo());
             AdapterListaCoche adapterListaCoche = new AdapterListaCoche(lista_coches);
             RecyclerView.LayoutManager layoutManager =  new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
             this.recyclerView.setLayoutManager(layoutManager);
             this.recyclerView.setAdapter(adapterListaCoche);
         }

     } else {
         Log.d("ETIQUETA_LOG", "La consulta NO ha recuperado coches");
     }

    }
}