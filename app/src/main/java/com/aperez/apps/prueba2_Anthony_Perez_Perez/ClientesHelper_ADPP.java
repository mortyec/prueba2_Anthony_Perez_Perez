package com.aperez.apps.prueba2_Anthony_Perez_Perez;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.support.annotation.Nullable;

public class ClientesHelper_ADPP extends SQLiteOpenHelper {

    private String createTable_Clientes = "CREATE TABLE Clientes (Codigo INTEGER PRIMARY KEY AUTOINCREMENT, " + "Nombre TEXT, " +
            "Apellido TEXT, " + " Usuario TEXT , " + "Contraseña TEXT )";

    public ClientesHelper_ADPP(@Nullable Context context, @Nullable String name,
                               @Nullable SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(createTable_Clientes);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS Clientes");
        sqLiteDatabase.execSQL(createTable_Clientes);
    }
}
