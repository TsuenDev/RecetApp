package com.example.apprecetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//Clase principal de inicio la cual contiene dos botones
    Button btn_main_misRecetas, btn_main_nuevaReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btn_main_nuevaReceta = findViewById(R.id.btn_main_nuevaReceta);
        //Hacemos que los botones escuchen el evento onClick
        btn_main_nuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevaReceta.class);
                startActivity(intent);
            }
        });

        btn_main_misRecetas = findViewById(R.id.btn_main_recetas);
        btn_main_misRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MisRecetas.class);
                startActivity(intent);
            }
        });

    }



    //Creamos un menu
    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        super.onCreateOptionsMenu(menu);
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }
    //Le asignamos una funcion cual se haya seleccionado
    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        switch (item.getItemId())
        {
            case R.id.misRecetas:
                Intent intent0 = new Intent(getApplicationContext(), MisRecetas.class);
                startActivity(intent0);
                return true;
            case R.id.nuevaReceta:
                Intent intent1 = new Intent(getApplicationContext(), NuevaReceta.class);
                startActivity(intent1);
                return true;
            case R.id.salir:
                finish();
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
}