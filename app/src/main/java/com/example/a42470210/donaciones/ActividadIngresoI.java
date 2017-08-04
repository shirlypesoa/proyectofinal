package com.example.a42470210.donaciones;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import java.io.IOException;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 42470210 on 16/6/2017.
 */

public class ActividadIngresoI extends AppCompatActivity
{
    DonacionesDatabase accesobase;
    SQLiteDatabase BaseDeDatos;

    Boolean BaseDeDatosAbierta()
    {
        Boolean responder;
        Log.d("SQLite","Inicializo el acceso a la base dandole como segundo parametro el nombre de la base");
        accesobase=new DonacionesDatabase(this,"BaseDeDatos",null,1 );
        Log.d("SQLite","Inicializo accediendo a la base en modo escritura");
        BaseDeDatos=accesobase.getWritableDatabase();
        Log.d("SQLite","Verifico que la base se haya abierto correctamente");

        if (BaseDeDatos == null)
        {
            responder = false;
        }
        else
        {
            responder =true;
        }
        return responder;
    }
    public void Volver4 (View Vista){
        Intent Volver;
        Volver = new Intent(ActividadIngresoI.this, Actividad_Inicial.class);
        startActivity(Volver);
    }

    EditText Usu;
    EditText Contra;
    Button Ingresar;
    String encontra2;
    String Usu2;

    public void ingresoi(View Vista)
    {

        Usu=(EditText)findViewById(R.id.usuI);
        Contra=(EditText)findViewById(R.id.contraI);
        Usu2=Usu.getText().toString();
        encontra2=Contra.getText().toString();
        Ingresar=(Button)findViewById(R.id.ingresoi);

        if (BaseDeDatosAbierta())
        {
            int cantidadderegistros = 0;
            Cursor Conjuntoderegistros;
            Conjuntoderegistros = BaseDeDatos.rawQuery("select nombre from instituciones where nombre = '" + Usu2 + "'", null);
            Log.d("SQLite", "Entro al rawquery del usuario");
            if (Conjuntoderegistros.moveToFirst() == true) {
                do {
                    Log.d("SQLite", "Movetofirst=true");
                    cantidadderegistros++;
                } while (Conjuntoderegistros.moveToNext() == true);
            } else {
                String EresUnCacahuateSi;
                EresUnCacahuateSi = "Usuario incorrecto";
                MostrarMensaje(EresUnCacahuateSi);
            }
            if (cantidadderegistros > 0) {
                String contraseña = "";
                Cursor Conjuntoderegistros2;
                Conjuntoderegistros2 = BaseDeDatos.rawQuery("select contraseña from instituciones where nombre = '" + Usu2 + "'", null);
                Log.d("SQLite", "Entro al rawquery de la contraseña");
                if (Conjuntoderegistros2.moveToFirst() == true) {
                    Log.d("SQLite", "Movetofirst=true");
                    contraseña = Conjuntoderegistros2.getString(0);
                }
                if (contraseña.compareTo(encontra2) == 0) {
                    String EresUnCacahuateSi;
                    EresUnCacahuateSi = "Todo lindo por aca";
                    MostrarMensaje(EresUnCacahuateSi);
                    Intent Aplicacion;
                    Aplicacion = new Intent(ActividadIngresoI.this, MapsActivity.class);
                    startActivity(Aplicacion);

                } else {
                    String EresUnCacahuateSi;
                    EresUnCacahuateSi = "Todo feo por aca";
                    MostrarMensaje(EresUnCacahuateSi);
                }
            }
        }
    }
    /*private class BackgroundTask extends AsyncTask<String, Void, String>
    {
        Context ctx;
        BackgroundTask(Context ctx)
        {
            this.ctx= ctx;
        }

        public String login(String[] params)
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("metodo", params[0])
                    .addFormDataPart("usuario", params[1])
                    .addFormDataPart("contraseña", params[2])
                    .build();


            Request request = new Request.Builder()
                    .url("http://10.152.2.33/ApiEjemplo/api/Usuario/Get"+params[1])
                    .method("POST", RequestBody.create(null, new byte[0]))
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();

                return resultado;

            } catch (IOException e) {
                Log.d("Debug", e.getMessage());
                //mostrarError(e.getMessage()); // Error de Network
                return "error";
            }
        }
        public String error(String metodo)
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("metodo", metodo)
                    .build();

            Request request = new Request.Builder()
                    .url("http://10.152.2.33/ApiEjemplo/api")
                    .method("POST", RequestBody.create(null, new byte[0]))
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();

                return resultado;
            } catch (IOException e) {
                Log.d("Debug", e.getMessage());
                //mostrarError(e.getMessage()); // Error de Network
                return "error";
            }
        }
        @Override
        protected String doInBackground (String...params) {
            switch (params[0])
            {
                case "login":
                    return login(params);
                default:
                    //return "Hubo un error";
                    return error(params[0]);
            }
        }
        @Override
        protected void onProgressUpdate(Void... values)
        {
            super.onProgressUpdate(values);
        }

        @Override
        protected void onPostExecute(String result)
        {
            Toast.makeText(ctx, result, Toast.LENGTH_LONG).show();
        }
    }*/

        @Override
        protected void onCreate (Bundle savedInstanceState){
            super.onCreate(savedInstanceState);
            setContentView(R.layout.layout_ingreso_i);
    }
    public void MostrarMensaje(String mensaje){
        Toast SoyUnCacahuateSi;
        SoyUnCacahuateSi=Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        SoyUnCacahuateSi.show();
    }
}
