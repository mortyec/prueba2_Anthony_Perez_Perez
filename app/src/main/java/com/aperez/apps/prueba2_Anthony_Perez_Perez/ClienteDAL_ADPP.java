package com.aperez.apps.prueba2_Anthony_Perez_Perez;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class ClienteDAL_ADPP {
    private ClientesHelper_ADPP clientesHelper; // crea la BD
    private SQLiteDatabase sql;
    private Context context;

    public ClienteDAL_ADPP(Context context){
        this.context=context;
    }

    public void openDAL(){
        clientesHelper=new ClientesHelper_ADPP(context,"ClientesDB",null,1);
        sql=clientesHelper.getWritableDatabase();
    }

    public void closeDAL(){
        sql.close();
    }

    public long insertDAL(Cliente_ADPP cliente){
        long count =0;
        try {
            this.openDAL();
            ContentValues values = new ContentValues();
            values.put("nombre",cliente.getNombre());
            values.put("Apellido",cliente.getApellido());
            values.put("Usuario",cliente.getUsuario());
            values.put("Contraseña",cliente.getContraseña());

            count = sql.insert("Clientes",null,values);

        }catch (Exception e){
            throw e;
        }finally {
            sql.close();
        }
        return count;
    }

    public Cliente_ADPP selectByCodigoDAL(int codigo){
        Cliente_ADPP cliente=null;
        try {
            this.openDAL();
            String select = "SELECT Codigo, Nombre, Apellido, Usuario, Contraseña FROM Clientes WHERE Codigo="+codigo;
            Cursor cursor = sql.rawQuery(select, null);

            if(cursor.moveToFirst())
            {
                cliente = new Cliente_ADPP();
                cliente.setNombre(cursor.getString(1));
                cliente.setApellido(cursor.getString(2));
                cliente.setUsuario(cursor.getString(3));
                cliente.setContraseña(cursor.getString(4));
            }

        }catch (Exception e){
            throw e;
        }finally {
            sql.close();
        }
        return cliente;
    }

    public Cliente_ADPP selectByUsuarioDAL(String Usuario){
        Cliente_ADPP cliente=null;
        try {
            this.openDAL();
            String select = "SELECT Codigo, Nombre, Apellido, Usuario, Contraseña FROM Clientes WHERE Usuario="+Usuario;
            Cursor cursor = sql.rawQuery(select, null);

            if(cursor.moveToFirst())
            {
                cliente = new Cliente_ADPP();
                cliente.setNombre(cursor.getString(1));
                cliente.setApellido(cursor.getString(2));
                cliente.setUsuario(cursor.getString(3));
                cliente.setContraseña(cursor.getString(4));
            }

        }catch (Exception e){
            throw e;
        }finally {
            sql.close();
        }
        return cliente;
    }

    public ArrayList<String> selectDAL(){
        ArrayList<String> list = null;
        try{
            this.openDAL();
            String select = "SELECT Codigo, Nombre, Apellido, Usuario, Contraseña FROM Clientes";
            Cursor cursor = sql.rawQuery(select, null);

            if(cursor.moveToFirst())
            {
                list = new ArrayList<String>();
                do{
                    list.add(
                                    cursor.getString(1)+" "+
                                    cursor.getString(2)+" "+
                                    cursor.getString(3)+" "+
                                    cursor.getString(4)
                    );
                }while (cursor.moveToNext());

            }

        }catch (Exception e){
            throw e;
        }finally {
            sql.close();
        }
        return list;
    }

    public ArrayList<Cliente_ADPP> selectDAL2(){
        ArrayList<Cliente_ADPP> list = null;
        try{
            this.openDAL();
            String select = "SELECT Codigo, Nombre, Apellido, Usuario, Contraseña FROM Clientes";
            Cursor cursor = sql.rawQuery(select, null);

            if(cursor.moveToFirst())
            {
                list = new ArrayList<Cliente_ADPP>();
                do{
                    Cliente_ADPP cliente = new Cliente_ADPP();
                    cliente.setCodigo(Integer.valueOf(cursor.getString(0)));
                    cliente.setNombre(cursor.getString(1));
                    cliente.setApellido(cursor.getString(2));
                    cliente.setUsuario(cursor.getString(3));
                    cliente.setContraseña(cursor.getString(4));
                    list.add(cliente);
                }while (cursor.moveToNext());

            }

        }catch (Exception e){
            throw e;
        }finally {
            sql.close();
        }
        return list;
    }

    public int deleteDAL(int codigo){
        int count =0;
        try {
            this.openDAL();
            count = sql.delete("Clientes","Codigo="+codigo,null);

        }catch (Exception e){
            throw e;
        }finally {
            sql.close();
        }
        return count;
    }

    public int updateDAL(Cliente_ADPP cliente){
        int count =0;
        try{
            this.openDAL();
            ContentValues values = new ContentValues();
            values.put("Nombre",cliente.getNombre());
            values.put("Apellido",cliente.getApellido());
            values.put("Usuario",cliente.getUsuario());
            values.put("Contraseña",cliente.getContraseña());
            count=sql.update("Clientes",values,"Codigo="+cliente.getCodigo(),null);
        }catch (Exception e){
            throw e;
        }finally {
            this.sql.close();
        }
        return count;
    }

}
