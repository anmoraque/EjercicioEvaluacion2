package edu.val.basedatosandroid;

import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

//ESTO ES COMO CADA FILA "holder" - CONTENEDOR
public class CocheHolder extends RecyclerView.ViewHolder {

    private TextView titulo_coche;

    //ESTE CONSTRUCTOR A INICIAR CADA FILA
    //TODO crear un layout para definir el aspecto de las filas
    //que las filas, se van a ir inflando después
    //itemView, es una fila
    public CocheHolder(@NonNull View itemView) {
        super(itemView);
        this.titulo_coche = itemView.findViewById(R.id.tituloCoche);
    }

    //rellenar una fila, con los datos concretos de un libro
    public  void cargarCocheEnHolder (Coche c)
    {
        this.titulo_coche.setText(c.getModelo());
    }
}