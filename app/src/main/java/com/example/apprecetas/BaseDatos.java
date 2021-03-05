package com.example.apprecetas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
//Clase que gestiona la base de datos
public class BaseDatos extends SQLiteOpenHelper {

    public static final int Database_version = 1;
    public static final String Database_name = "Recetas.db";

    public BaseDatos(Context context){
        super( context, Database_name,null,Database_version);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        //Creamos las tablas y las columnas de la base de datos
        db.execSQL("CREATE TABLE Receta(" +
                "id_receta INTEGER   PRIMARY KEY AUTOINCREMENT,"+
                "titulo VARCHAR NOT NULL,"+
                "tiempo VARCHAR,"+
                "ingredientes VARCHAR,"+
                "instrucciones VARCHAR);");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        //Actualizamos la base de datos
    }

    public void guardaDatosBD(String titulo, String tiempo, String ingredientes, String instrucciones){
        //Metodo que recoge datos y los guardaremos en la base de datos
        getReadableDatabase().execSQL("INSERT INTO Receta VALUES ("+
            null+",'"+titulo+"','"+tiempo+"','"+ingredientes+"','"+instrucciones+"');");
    }

    public Cursor getRecetas(){
        //Metodo que nos devuelve datos de la tabla receta
        return getReadableDatabase().query("Receta", null,null,null,null,null,null,null);
    }

    public Cursor getById(int id){
        return getReadableDatabase().rawQuery("SELECT * FROM Receta WHERE id_receta="+id+" ;",null);
    }
}
