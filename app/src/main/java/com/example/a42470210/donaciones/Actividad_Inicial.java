package com.example.a42470210.donaciones;

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.support.annotation.Size;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by 42470210 on 16/6/2017.
 */

public class Actividad_Inicial extends AppCompatActivity
{

    public void InSe(View Vista){
        Intent IniciarSesion;
        IniciarSesion = new Intent(Actividad_Inicial.this, ActividadIngreso.class);
        startActivity(IniciarSesion);
    }
    public void Reg(View Vista){
        Intent Registrarse;
        Registrarse = new Intent(Actividad_Inicial.this, ActividadRegistro.class);
        startActivity(Registrarse);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_regisini);
    }

}

