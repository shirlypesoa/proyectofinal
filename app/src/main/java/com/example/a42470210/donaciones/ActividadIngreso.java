package com.example.a42470210.donaciones;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

/**
 * Created by 42470210 on 16/6/2017.
 */

public class ActividadIngreso extends AppCompatActivity{
    public void IniciarU(View Vista){
        Intent Usuario;
        Usuario = new Intent(ActividadIngreso.this, ActividadIngresoU.class);
        startActivity(Usuario);
    }
    public void IniciarI(View Vista){
        Intent Institucion;
        Institucion = new Intent(ActividadIngreso.this, ActividadIngresoI.class);
        startActivity(Institucion);
    }
    public void Volver2 (View Vista){
        Intent Volver;
        Volver = new Intent(ActividadIngreso.this, Actividad_Inicial.class);
        startActivity(Volver);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_ingreso);
    }
}