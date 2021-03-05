package com.example.apprecetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

public class EditarReceta extends AppCompatActivity {

    EditText tituloET, tiempoET, ingredientesET, instruccionesET;
    int id;
    ImageButton imgBtnVolverEdit;
    Button btnActualizar;
    BaseDatos baseDatos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.editar_receta);

        baseDatos = new BaseDatos(this);

        tituloET = findViewById(R.id.editTextTituloUpd);
        tiempoET = findViewById(R.id.editTextTiempoUpd);
        ingredientesET = findViewById(R.id.editTextIngredientesUpd);
        instruccionesET = findViewById(R.id.editTextInstruccionesUpd);

        //Recoge el id que ha pasado de la actividad anterior
        id = (int) getIntent().getExtras().get("id_receta");

        //metodo que muestra los datos del objeto receta de nuestra base de datos cuyo id sea el mismo que se pasa por parametro
        mostrarDatosReceta(id);

        imgBtnVolverEdit = findViewById(R.id.imgBtnVolverEdit);
        imgBtnVolverEdit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Finalizamos la actividad actual y nos lleva a la anterior
                finish();
            }
        });

        btnActualizar = findViewById(R.id.btnActualizar);
        btnActualizar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Asignamos el evento onClick para que cuando se pulse actualizar, actualice los datos en la base de datos
                // Llama a la funcion actualizarDatos() de nuestra clase de base de datos
                //Le pasamos como parametros los datos que contengan  los editText
                String titulo=tituloET.getText().toString();
                String tiempo=tiempoET.getText().toString();
                String ingredientes=ingredientesET.getText().toString();
                String instrucciones=instruccionesET.getText().toString();

                //Condicion para comprobar que el editText que tiene el nombre de titulo no este vacio
                if(!titulo.trim().equals("")) {
                    //Llamamos al metodo de nuestra base de datos que actualiza los datos de la BD por los pasados como parametro
                    baseDatos.actualizarDatos(id, titulo, tiempo, ingredientes, instrucciones);
                    //Hacemos un Toast para imprimir un mensaje el cual diga que se ha actualizado los datos
                    Toast.makeText(getApplicationContext(), "Datos actualizados correctamente", Toast.LENGTH_SHORT).show();
                    //Hacemos un finish() para que nos lleve a la actividad anterior
                    Intent intent = new Intent(getApplicationContext(), MisRecetas.class);
                    startActivity(intent);
                }else{
                    Toast.makeText(getApplicationContext(),"Rellene los campos",Toast.LENGTH_SHORT).show();
                }
            }
        });
    }

    //Metodo de clases anteriores para obtener los datos de la receta con el id que pasamos por parametro
    public void mostrarDatosReceta(int id) {
        //Un cursor al que llamamos al metodo de nuestra base de datos, en el cual obtendremos los datos cuyo id sea el que le pasamos por parametro
        Cursor datos = baseDatos.getById(id);
        String titulo, tiempo, ingredientes, instrucciones;
        while(datos.moveToNext()) {//El bucle funcionara mientras haya datos que leer

            //Recogemos los datos y los guardamos en variables
            id = datos.getInt(datos.getColumnIndex("id_receta"));
            titulo = datos.getString(datos.getColumnIndex("titulo"));
            tiempo = datos.getString(datos.getColumnIndex("tiempo"));
            ingredientes = datos.getString(datos.getColumnIndex("ingredientes"));
            instrucciones = datos.getString(datos.getColumnIndex("instrucciones"));
            //Le pasamos los valores los campos de texto de nuestro layout
            tituloET.setText(titulo);
            tiempoET.setText(tiempo);
            ingredientesET.setText(ingredientes);
            instruccionesET.setText(instrucciones);
        }
    }
}