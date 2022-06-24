package edu.val.basedatosandroid;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

/**
 * ESTA CLASE ACTÚA COMO PROVEEDOR DE DATOS DEL RECYCLER
 * Y CONTIENE LA LISTA/LA INFORMACIÓN A MOSTRAR
 */

public class AdapterListaCoche extends RecyclerView.Adapter<CocheHolder> {

    private List<Coche> lista_coches;

    public AdapterListaCoche(List<Coche> lista_coches)
    {
        this.lista_coches = lista_coches;//guardamos la lista, para luego ir dándosela al Recycler cuando nos la pida
    }

    //este método, se va a invocar cuando el recycler necesite una fila nueva
    @NonNull
    @Override
    public CocheHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        CocheHolder cocheHolder = null;

        LayoutInflater layoutInflater =  LayoutInflater.from(parent.getContext());
        View fila_coche = layoutInflater.inflate(R.layout.fila_coche, parent, false);
        cocheHolder = new CocheHolder(fila_coche);


        return cocheHolder;
    }

    //este método, se va a invocar cuando el recycler necesite rellenar una fila
    @Override
    public void onBindViewHolder(@NonNull CocheHolder holder, int position) {
        Coche coche =  lista_coches.get(position);
        holder.cargarCocheEnHolder(coche);
    }

    //este método le dice al Recycler cuántos items tiene que dibujar
    @Override
    public int getItemCount() {
        return this.lista_coches.size();
    }
}
