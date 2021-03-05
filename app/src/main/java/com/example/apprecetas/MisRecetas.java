package com.example.apprecetas;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class MisRecetas extends AppCompatActivity {
    ImageButton imgBtnVolvermisRecetas;
    Button btnNuevaReceta;
    ListView listaRecetas;
    ArrayAdapter<String> adapter;
    ArrayList<String> arrayRecetas;
    ArrayList<Receta> recetas;
    Map<String, Integer> mapRecetas;

    BaseDatos baseDatos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.mis_recetas);

        //Instanciamos nuestra clase de base de datos
        baseDatos = new BaseDatos(this);

        //Array con objetos de tipo Receta obtenidos del metodo obtenerDatos() el cual recoge datos de nuestra base de datos
        recetas=obtenerDatos();
        //Inicializamos el array que tendrá los titulos de las recetas guardadas
        arrayRecetas = new ArrayList<String>();
        //Usamos este bucle para llenar el array con los titulos de las recetas que usaremos para mostrar en nuestra ListView
        for(int i=0;i<recetas.size(); i++)
        {
            arrayRecetas.add(recetas.get(i).getTitulo());
        }

        //Rellenar nuestro hashmap que después usaremos para encontrar un valor cuando le pasemos una clave que se le asigna al guardarlo en el map
        mapRecetas = new HashMap<String, Integer>();
        for(int i=0 ; i<recetas.size() ; i++){
            String key=recetas.get(i).getTitulo(); //Guardamos como llave titulo de la receta
            int valor=recetas.get(i).getId(); //Guardamos como valor el id del objeto
            mapRecetas.put(key,valor); //añadimos los valores al mapa que al pasarle un titulo, nos devolvera una id
        }

        //Asociamos esta variable a la id del boton y le añadimos un evento onClick
        imgBtnVolvermisRecetas = findViewById(R.id.imgBtnVolvermisRecetas);
        imgBtnVolvermisRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //Creamos un intent para que nos lleve a otra actividad
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(intent);
            }
        });

        //Asociamos esta variable a la id del boton y le añadimos un evento onClick
        btnNuevaReceta = findViewById(R.id.btnNuevaReceta);
        btnNuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v)
            {
                //Creamos un intent para que nos lleve a otra actividad
                Intent intent = new Intent(getApplicationContext(), NuevaReceta.class);
                startActivity(intent);
            }
        });

        //Creamos una variable de tipo LisView a la que la asignamos la id de la ListView de nuestro Layout
        listaRecetas = (ListView) findViewById(R.id.listaRecetas);
        //Creamos un adaptar para que muestre datos en la ListView, mostrara los datos del array que le hemos pasado
        adapter = new ArrayAdapter<String>(this, R.layout.support_simple_spinner_dropdown_item,arrayRecetas);
        //Volcamos el adapter en la ListView
        listaRecetas.setAdapter(adapter);
        //A la lista se asignamos que escuche el evento onItemClick para que haga una función cuando un item de la lista sea clickeado
        listaRecetas.setOnItemClickListener(new AdapterView.OnItemClickListener(){
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String nombre = adapter.getItem(position);//obtenemos el nombre de la posicion
                int id_receta=mapRecetas.get(nombre); //obtenemos el valor(id) del mapa que tiene como clave el nombre del item de la posicion clickeada
                //Creamos un intent para que nos lleve a otra actividad la cual nos mostrara los datos del receta
                Intent intent = new Intent(getApplicationContext(), RecetaInfo.class);
                intent.putExtra("id_receta",id_receta);//guardamos este valor con un nombre para enviarla a la otra actividad
                startActivity(intent);
            }
        });
    }

    //Metodo con el que obtendremos los datos de nuestra base de datos y lo guardaremos en un ArrayList
    public ArrayList<Receta> obtenerDatos()
    {
        //Un cursor ( coleccion de filas ) al que llamamos al metodo de nuestra base de datos, en el cual obtendremos los datos guardados
        Cursor datos = baseDatos.getRecetas();
        ArrayList<Receta> recetasArrayList = new ArrayList<Receta>();
        String titulo, tiempo ,ingredientes, instrucciones;
        int id;
        while(datos.moveToNext())//Estara en bucle siempre que se pueda acceder al siguiente dato
        {
            //Recogemos los datos y los guardamos en variables
            id = datos.getInt(datos.getColumnIndex("id_receta"));
            titulo = datos.getString(datos.getColumnIndex("titulo"));
            tiempo = datos.getString(datos.getColumnIndex("tiempo"));
            ingredientes = datos.getString(datos.getColumnIndex("ingredientes"));
            instrucciones = datos.getString(datos.getColumnIndex("instrucciones"));
            //Creamos un objeto de tipo Receta y le pasamos los datos guardados en el constructor
            Receta receta = new Receta(id,titulo,tiempo,ingredientes,instrucciones);
            //Añadimos el objeto de tipo receta al arrayList
            recetasArrayList.add(receta);
        }
        //El método retornará un array con objetos de tipo receta
        return recetasArrayList;
    }

}
