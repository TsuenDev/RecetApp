package com.example.apprecetas;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;

public class NuevaReceta extends AppCompatActivity {
    ImageButton imgBtnVolverNuevaReceta;

    ArrayList<String> tituloReceta, tiempoReceta, ingredientesReceta, instruccionesReceta;
    EditText editTextTitulo, editTextTiempo, editTextIngredientes, editTextInstrucciones;
    Button btnGuardar;
    Receta receta;

    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.nueva_receta);

        baseDatos = new BaseDatos(this);

        tituloReceta = new ArrayList<String>();
        tiempoReceta = new ArrayList<String>();
        ingredientesReceta = new ArrayList<String>();
        instruccionesReceta = new ArrayList<String>();

        editTextTitulo = findViewById(R.id.editTextTitulo);
        editTextTiempo = findViewById(R.id.editTextTiempo);
        editTextIngredientes = findViewById(R.id.editTextIngredientes);
        editTextInstrucciones = findViewById(R.id.editTextInstrucciones);

        btnGuardar = findViewById(R.id.btnGuardar);
        btnGuardar.setOnClickListener(new View.OnClickListener() {
            //Evento  onClick del botno guardar
            @Override
            public void onClick(View v) {
                //Comprobamos que el texto del editText que recoge no este vacio
                if(editTextTitulo.getText().toString().trim().equals(""))
                {
                    Toast.makeText(getApplicationContext(),"Rellene los campos",Toast.LENGTH_SHORT).show();
                }else{
                    //Si no esta vacia, llamará al método guardarDatos que guardara los datos de los campos en nuestra base de datos
                    guardarDatos();
                    //Hacemos un intent para que al guardar, nos lleve a la actividad donde tenemos la lista de recetas
                    Intent intent = new Intent(getApplicationContext(), MisRecetas.class);
                    startActivity(intent);
                }
            }
        });

        imgBtnVolverNuevaReceta = findViewById(R.id.imgBtnVolverNuevaReceta);
        imgBtnVolverNuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//Al pulsar el boton volver, finalizara la actividad y nos llevará a la última mostrada
            }
        });

    }

    //Método que recoge los datos de los campos de los editText y los pasa como parametro al metodo de la BD que insertará los datos en la BD
    public void guardarDatos(){
        String titulo = editTextTitulo.getText().toString();
        String tiempo = editTextTiempo.getText().toString();
        String ingredientes = editTextIngredientes.getText().toString();
        String instrucciones = editTextInstrucciones.getText().toString();

        baseDatos.guardaDatosBD(titulo, tiempo, ingredientes, instrucciones);
        Toast.makeText(getApplicationContext(),"Receta añadida correctamente", Toast.LENGTH_SHORT).show();
    }

}
