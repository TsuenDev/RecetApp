package com.example.apprecetas;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class RecetaInfo extends AppCompatActivity {

    TextView mainTitulo, tituloTv, tiempoTv, ingredientesTv, instruccionesTv;
    int id_receta;
    ImageButton imgBtnVolverNuevaReceta, imageButtonEdit;
    Button btnEliminar;
    Receta receta;

    AlertDialog.Builder builder;
    BaseDatos baseDatos;
    @Override

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.receta_info);

        baseDatos = new BaseDatos(this);

        mainTitulo = findViewById(R.id.textViewMainTitulo);
        tituloTv = findViewById(R.id.textViewTitulo);
        tiempoTv = findViewById(R.id.textViewTiempo);
        ingredientesTv = findViewById(R.id.textViewIngredientes);
        instruccionesTv = findViewById(R.id.textViewInstrucciones);

        //Recogemos el id que nos pasa la otra actividad y la guardamos en una variable
        id_receta = (int) getIntent().getExtras().get("id_receta");
        //llamamos al metodo para obtener y mostrar la receta cuyo id sea igual al que le pasamos por parametro
        receta=mostrarDatosReceta(id_receta);

        imgBtnVolverNuevaReceta = findViewById(R.id.imgBtnVolverNuevaReceta);
        imgBtnVolverNuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al pulsar el boton volver, finalizara la actividad y nos llevará a la última mostrada
                finish();
            }
        });

        imageButtonEdit = findViewById(R.id.imageButtonEdit);
        imageButtonEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un intent para que nos lleve a otra actividad y le pasaremos el id de la receta que este mostrando en ese momento
                Intent intent = new Intent(getApplicationContext(), EditarReceta.class);
                intent.putExtra("id_receta",id_receta);//guardamos este valor con un nombre para enviarla a la otra actividad
                startActivity(intent);
            }
        });

        btnEliminar = findViewById(R.id.btnEliminar);
        btnEliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Al pulsar borrar creamos un AlerDialog que nos preguntara si queremos borrar
                AlertDialog.Builder alert = new AlertDialog.Builder(RecetaInfo.this);
                alert.setTitle(receta.getTitulo());
                alert.setMessage("Se va a borrar esta receta, ¿Desea continuar?");
                //Si pulsamos esta opcion llamara al metodo de la base de datos y eliminara el registro
                alert.setPositiveButton("Borrar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //Llama al metodo de la base de datos que borrara el registro
                        baseDatos.borrarById(id_receta);
                        Toast.makeText(getApplicationContext(),"Receta eliminada",Toast.LENGTH_SHORT).show();
                        //Creamos un intent para que nos lleve a la actividad anterior
                        Intent intent = new Intent(getApplicationContext(), MisRecetas.class);
                        startActivity(intent);
                    }
                });
                //Si pulsa cancelar, se cancelara este DialogAlert
                alert.setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });

                alert.show();
            }
        });
    }

    //Metodo para obtener los datos de la receta con el id que pasamos por parametro
    public Receta mostrarDatosReceta(int id) {
        //Un cursor al que llamamos al metodo de nuestra base de datos, en el cual obtendremos los datos cuyo id sea el que le pasamos por parametro
        Cursor datos = baseDatos.getById(id);
        String titulo, tiempo, ingredientes, instrucciones;
        Receta receta = null;
        while(datos.moveToNext()) {
            //Recogemos los datos y los guardamos en variables
            id = datos.getInt(datos.getColumnIndex("id_receta"));
            titulo = datos.getString(datos.getColumnIndex("titulo"));
            tiempo = datos.getString(datos.getColumnIndex("tiempo"));
            ingredientes = datos.getString(datos.getColumnIndex("ingredientes"));
            instrucciones = datos.getString(datos.getColumnIndex("instrucciones"));
            //Le pasamos los valores los campos de texto de nuestro layout
            mainTitulo.setText(titulo);
            tituloTv.setText(titulo);
            tiempoTv.setText(tiempo);
            ingredientesTv.setText(ingredientes);
            instruccionesTv.setText(instrucciones);
            //Creamos un objeto de tipo Receta y le pasamos los datos guardados en el constructor
            receta = new Receta(id, titulo, tiempo, ingredientes, instrucciones);
            //Añadimos el objeto de tipo receta al arrayList
        }
        //El método retornará un objeto de tipo receta
        return receta;
    }
}
