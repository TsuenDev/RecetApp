package com.example.apprecetas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
//Clase principal de inicio la cual contiene dos botones
    Button btn_main_misRecetas, btn_main_nuevaReceta;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        //declaramos las variables y las asociamos el id de boton para poder acceder a ellos
        btn_main_misRecetas = findViewById(R.id.btn_main_recetas);
        btn_main_nuevaReceta = findViewById(R.id.btn_main_nuevaReceta);

        //Hacemos que los botones escuchen el evento onClick
        btn_main_nuevaReceta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), NuevaReceta.class);
                startActivity(intent);
            }
        });

        btn_main_misRecetas.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), MisRecetas.class);
                startActivity(intent);
            }
        });

    }
}