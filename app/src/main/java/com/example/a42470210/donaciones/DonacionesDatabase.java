package com.example.a42470210.donaciones;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

/**
 * Created by 42470210 on 22/6/2017.
 */

public class DonacionesDatabase extends SQLiteOpenHelper {
    public DonacionesDatabase (Context contexto, String Nombre, SQLiteDatabase.CursorFactory fabrica, int Version)
    {
        super(contexto, Nombre, fabrica, Version);
    }

    @Override
    public void onCreate (SQLiteDatabase BaseDeDatos)
    {
        Log.d("SQL","Inicio onCreate personas");
        String sqlCrearTablaPersonas;
        sqlCrearTablaPersonas="create table usuarios (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, apellido text, dni text, email text, usuario text, contrasena text)";
        Log.d("SQL","Invoco al creador de la tabla");
        BaseDeDatos.execSQL((sqlCrearTablaPersonas));
        Log.d("SQL","Fin de la creacion de la tabla personas");

        Log.d("SQL","Inico onCreate Institucion");
        String sqlCrearTablaInstitucion;
        sqlCrearTablaInstitucion="create table instituciones (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, apellido text, cuit text, email text, usuario text, contrasena text)";
        Log.d("SQL","Invoco al creador de la tabla");
        BaseDeDatos.execSQL((sqlCrearTablaInstitucion));
        Log.d("SQL","Fin de la creacion de la tabla institucion");

        Log.d("SQL","Inico onCreate Establecimiento");
        String sqlCrearTablaEstablecimiento;
        sqlCrearTablaEstablecimiento="create table establecimiento (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, direccion text, telefono text, categorias text)";
        Log.d("SQL","Invoco al creador de la tabla");
        BaseDeDatos.execSQL((sqlCrearTablaEstablecimiento));
        Log.d("SQL","Fin de la creacion de la tabla establecimiento");

        Log.d("SQL","Inico onCreate Evento");
        String sqlCrearTablaEvento;
        sqlCrearTablaEvento="create table evento (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text, lugar text, fecha datetime, descripcion text)";
        Log.d("SQL","Invoco al creador de la tabla");
        BaseDeDatos.execSQL((sqlCrearTablaEvento));
        Log.d("SQL","Fin de la creacion de la tabla evento");

        Log.d("SQL","Inico onCreate Categorias");
        String sqlCrearTablaCategorias;
        sqlCrearTablaCategorias="create table categorias (_id INTEGER PRIMARY KEY AUTOINCREMENT, nombre text)";
        Log.d("SQL","Invoco al creador de la tabla");
        BaseDeDatos.execSQL((sqlCrearTablaCategorias));
        Log.d("SQL","Fin de la creacion de la tabla categorias");
    }


    @Override
    public void onUpgrade (SQLiteDatabase baseDeDatos, int VersionAnterior, int VersionNueva)
    {

    }
}
