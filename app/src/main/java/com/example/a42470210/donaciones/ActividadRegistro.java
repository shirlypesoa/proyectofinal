package com.example.a42470210.donaciones;

import android.content.ContentValues;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.ArrayList;

/**
 * Created by 42470210 on 16/6/2017.
 */

public class ActividadRegistro extends AppCompatActivity
{
    public void Volver1 (View Vista){
        Intent Volver;
        Volver = new Intent(ActividadRegistro.this, Actividad_Inicial.class);
        startActivity(Volver);
    }
    public void RegistroU (View Vista){
        Intent Usuario;
        Usuario = new Intent(ActividadRegistro.this, ActividadRegistroU.class);
        startActivity(Usuario);
    }
    public void RegistroI (View Vista){
        Intent Institucion;
        Institucion = new Intent(ActividadRegistro.this, ActividadRegistroI.class);
        startActivity(Institucion);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro);
    }
}
