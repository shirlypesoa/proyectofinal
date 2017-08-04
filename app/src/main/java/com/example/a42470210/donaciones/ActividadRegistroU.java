package com.example.a42470210.donaciones;

import android.content.ContentValues;
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

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;

import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by 42470210 on 16/6/2017.
 */

public class ActividadRegistroU extends AppCompatActivity{

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

    public void Volver6 (View Vista){
        Intent Volver;
        Volver = new Intent(ActividadRegistroU.this, Actividad_Inicial.class);
        startActivity(Volver);
    }

    public void RegistrarmeU (View Vista)
    {
        EditText Nombre;
        Nombre = (EditText)findViewById(R.id.NombreU);
        EditText Apellido;
        Apellido = (EditText)findViewById(R.id.ApellidoU);
        EditText DNI;
        DNI=(EditText)findViewById(R.id.DniU);
        EditText Email;
        Email=(EditText)findViewById(R.id.EmailU);
        EditText Usuario;
        Usuario=(EditText)findViewById(R.id.UsuarioU);
        EditText Contraseña;
        Contraseña=(EditText)findViewById(R.id.ContraseñaU);
        EditText RContraseña;
        RContraseña=(EditText)findViewById(R.id.RepetirConU);
        Button Registrar;
        Registrar = (Button)findViewById(R.id.RegistrarmeU);
        String Nombre2;
        String Apellido2;
        String DNI2;
        String Email2;
        String Usuario2;
        String Contraseña2;
        String RContraseña2;

        Nombre2 = Nombre.getText().toString();
        Apellido2 = Apellido.getText().toString();
        DNI2 = DNI.getText().toString();
        Email2 = Email.getText().toString();
        Usuario2 = Usuario.getText().toString();
        Contraseña2=Contraseña.getText().toString();
        RContraseña2= RContraseña.getText().toString();

            if(BaseDeDatosAbierta())
            {
                int cantidadderegistros=0;
                ArrayList<String> ListaDatos = new ArrayList<String>();
                Cursor Conjuntoderegistros;
                Conjuntoderegistros=BaseDeDatos.rawQuery("select usuario from usuarios where usuario = '"+Usuario2 + "'", null);
                Log.d("SQLite","Entro al rawquery");
                if (Conjuntoderegistros.moveToFirst()==true)
                {
                    do {
                        Log.d("SQLite","Movetofirst=true");
                        cantidadderegistros++;
                        String NombreUsuario = Conjuntoderegistros.getString(0);
                        ListaDatos.add (NombreUsuario);
                    }while (Conjuntoderegistros.moveToNext()==true);
                }
                if (cantidadderegistros>0)
                {
                    Log.d("SQLite","Ya existe");
                    String EresUnCacahuateSi;
                    EresUnCacahuateSi="El nombre de usuario ya existe";
                    MostrarMensaje(EresUnCacahuateSi);
                }else
                {
                    Log.d("SQLite","uno: "+ Contraseña2 +" dos: "+ RContraseña2);
                    if (Contraseña2.compareTo(RContraseña2)!=0)
                    {
                        Log.d("SQLite","Contraseña repetida");
                        String EresUnCacahuateSi;
                        EresUnCacahuateSi="Las contraseñas no son iguales";
                        MostrarMensaje(EresUnCacahuateSi);
                    }else
                    {

                        ContentValues nuevoRegistro;
                        nuevoRegistro = new ContentValues();
                        Log.d("SQLite","nuevo registro");
                        nuevoRegistro.put("nombre",Nombre2);
                        nuevoRegistro.put("apellido",Apellido2);
                        nuevoRegistro.put("email",Email2);
                        nuevoRegistro.put("dni",DNI2);
                        nuevoRegistro.put("usuario",Usuario2);
                        nuevoRegistro.put("contraseña",Contraseña2);
                        BaseDeDatos.insert("usuarios", null, nuevoRegistro);
                        Log.d("SQLite","Agrego los datos");
                        BaseDeDatos.close();
                        String EresUnCacahuateSi;
                        EresUnCacahuateSi="Se creó el usuario: " + Usuario2;
                        MostrarMensaje(EresUnCacahuateSi);
                        Log.d("SQLite","Agrego las cosas");
                        Intent Aplicacion;
                        Aplicacion = new Intent(ActividadRegistroU.this, NavegacionUsuario.class);
                        startActivity(Aplicacion);
                    }
                }
            }else
            {
                String EresUnCacahuateSi;
                EresUnCacahuateSi="No entro al if";
                MostrarMensaje(EresUnCacahuateSi);
            }

        }

    private class BackgroundTask extends AsyncTask<String, Void, String>
    {
        Context ctx;
        BackgroundTask(Context ctx)
        {
            this.ctx= ctx;
        }

        public String registrar(String[] params)
        {
            OkHttpClient client = new OkHttpClient();

            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("metodo", params[0])
                    .addFormDataPart("nombre", params[1])
                    .addFormDataPart("apellido", params[2])
                    .addFormDataPart("dni", params[3])
                    .addFormDataPart("email", params[4])
                    .addFormDataPart("usuario", params[5])
                    .addFormDataPart("contraseña", params[6])
                    .build();


            Request request = new Request.Builder()
                    .url("http://10.152.2.33/api/ApiEjemplo/Usuario/Post")
                    .method("POST", RequestBody.create(null, new byte[0]))
                    .post(requestBody)
                    .build();

            try {
                Response response = client.newCall(request).execute();  // Llamo al API Rest servicio1 en ejemplo.com
                String resultado = response.body().string();

                return resultado;
            } catch (IOException e) {
                Log.d("Debug", e.getMessage());
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
                case "register":
                    return registrar(params);
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

        ArrayList<String> parsearResultado(String result) throws JSONException {
            ArrayList<String> usuarios = new ArrayList<>();
            JSONArray jsonusuarios = new JSONArray(result);  // Convierto el String en JSONArray
            for (int i=0; i<jsonusuarios.length(); i++) {    //  Recorro el JSONArray
                JSONObject jsonResultado = jsonusuarios.getJSONObject(i); // Obtengo cada Ciudad (obj compuesto)
                String nombre = jsonResultado.getString("nombre");
                String apellido = jsonResultado.getString("apellido");
                String dni = jsonResultado.getString("dni");
                String email = jsonResultado.getString("email");
                String usuario = jsonResultado.getString("usuario");
                String contraseña = jsonResultado.getString("contraseña"); //  Obtengo el nombre (tipo simple)
                usuarios.add(nombre);
                usuarios.add(apellido);
                usuarios.add(dni);
                usuarios.add(email);
                usuarios.add(usuario);
                usuarios.add(contraseña); // Agrego String al ArrayList
            }
            return usuarios;
        }

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.layout_registro_u);
    }
    public void MostrarMensaje(String mensaje){
        Toast SoyUnCacahuateSi;
        SoyUnCacahuateSi=Toast.makeText(this, mensaje, Toast.LENGTH_LONG);
        SoyUnCacahuateSi.show();
    }
}