package com.proyecto.aplicacion.proyecto;

import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

public class Login extends AppCompatActivity implements View.OnClickListener {
    private EditText eTusuario,eTpass;
    private Button btnLogin;

    private RequestQueue requestQueue;
    private static final String URLLogin="http://192.168.0.120:80/androidlogin/usuario_control.php";
    private StringRequest request;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        eTusuario=(EditText) findViewById(R.id.eTusuario);
        eTpass=(EditText) findViewById(R.id.eTpass);

        requestQueue= Volley.newRequestQueue(this);

        btnLogin=(Button) findViewById(R.id.btnLogin);
        btnLogin.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        request=new StringRequest(Request.Method.POST,URLLogin,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){
                        Toast.makeText(getApplicationContext(),"SUCCESS"+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                        startActivity(new Intent(getApplicationContext(),Principal.class));
                    }else{
                        AlertDialog.Builder builder=new AlertDialog.Builder(Login.this);
                        builder.setMessage("Usuario o Contraseña Incorrectos").setNegativeButton("Repetir",null).create().show();
                        //Toast.makeText(getApplicationContext(),"ERROR"+jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
                    }
                }catch(JSONException e){
                    e.printStackTrace();
                }
            }
            },new Response.ErrorListener(){
                @Override
                        public void onErrorResponse(VolleyError error){

                    }
            }){
            @Override
            protected Map<String,String> getParams()throws AuthFailureError {
                HashMap<String,String> hashMap=new HashMap<>();
                hashMap.put("nombre_usuario",eTusuario.getText().toString());
                hashMap.put("pass",eTpass.getText().toString());
                return hashMap;
            }
        };
        requestQueue.add(request);
    }
}
