package com.example.apprecetas;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecetaInfo extends AppCompatActivity {

    TextView tituloTv;
    TextView tiempoTv;
    TextView ingredientesTv;
    TextView instruccionesTv;

    String id_receta;
    ImageButton imgBtnVolverNuevaReceta;
    Receta receta;

    BaseDatos baseDatos;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_info);

        baseDatos = new BaseDatos(this);

        tituloTv = findViewById(R.id.textViewTitulo);
        tiempoTv = findViewById(R.id.textViewTiempo);
        ingredientesTv = findViewById(R.id.textViewIngredientes);
        instruccionesTv = findViewById(R.id.textViewInstrucciones);

        //Recogemos el id que nos pasa la otra actividad y la guardamos en una variable
        id_receta =  getIntent().getExtras().get("id_receta").toString();

        //llamamos al metodo para obtener la receta cuyo id sea igual al que le pasamos por parametro
        receta=obtenerDatosReceta(Integer.parseInt(id_receta));

        imgBtnVolverNuevaReceta = findViewById(R.id.imgBtnVolverNuevaReceta);
        imgBtnVolverNuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al pulsar el boton volver, finalizara la actividad y nos llevará a la última mostrada
                finish();
            }
        });
    }

    //Metodo para obtener los datos de la receta con el id que pasamos por parametro
    public Receta obtenerDatosReceta(int id) {
        //Un cursor al que llamamos al metodo de nuestra base de datos, en el cual obtendremos los datos cuyo id sea el que le pasamos por parametro
        Cursor datos = baseDatos.getById(id);
        String titulo, tiempo, ingredientes, instrucciones;
        while(datos.moveToNext()) {
            //Recogemos los datos y los guardamos en variables
            id = datos.getInt(datos.getColumnIndex("id_receta"));
            titulo = datos.getString(datos.getColumnIndex("titulo"));
            tiempo = datos.getString(datos.getColumnIndex("tiempo"));
            ingredientes = datos.getString(datos.getColumnIndex("ingredientes"));
            instrucciones = datos.getString(datos.getColumnIndex("instrucciones"));
            //Le pasamos los valores los campos de texto de nuestro layout
            tituloTv.setText(titulo);
            tiempoTv.setText(tiempo);
            ingredientesTv.setText(ingredientes);
            instruccionesTv.setText(instrucciones);
            //Creamos un objeto de tipo Receta y le pasamos los datos guardados en el constructor
            Receta receta = new Receta(id, titulo, tiempo, ingredientes, instrucciones);
            //Añadimos el objeto de tipo receta al arrayList
        }
        //El método retornará un objeto de tipo receta
        return receta;
    }
}
