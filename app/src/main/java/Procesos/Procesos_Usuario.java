package Procesos;


import android.app.Activity;
import android.content.Intent;
import android.support.v7.app.AlertDialog;
import android.widget.Toast;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.proyecto.aplicacion.proyecto.Login;
import com.proyecto.aplicacion.proyecto.Principal;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Procesos_Usuario {

    private RequestQueue requestQueue;
    private static final String URLLoginR="http://192.168.0.120:80/androidlogin/Registro_Usuario.php";
    private static final String URLLoginB="http://192.168.0.120:80/androidlogin/Registro_Usuario.php";
    private StringRequest request;

    public void registrarUsuario(final ArrayList<Object> Lista, final Activity activity){
        requestQueue= Volley.newRequestQueue(activity);
        request=new StringRequest(Request.Method.POST,URLLoginR,new Response.Listener<String>(){
            @Override
            public void onResponse(String response){
                try{
                    JSONObject jsonObject=new JSONObject(response);
                    if(jsonObject.names().get(0).equals("success")){
                        Toast.makeText(activity,"FELICITACIONES: "+jsonObject.getString("success"),Toast.LENGTH_SHORT).show();
                    }else{
                        Toast.makeText(activity,"ERROR"+jsonObject.getString("error"),Toast.LENGTH_SHORT).show();
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
                hashMap.put("nombre_usuario",Lista.get(0).toString());
                hashMap.put("cedula",Lista.get(1).toString());
                hashMap.put("pass",Lista.get(2).toString());
                hashMap.put("privilegios",Lista.get(3).toString());
                return hashMap;
            }
        };
        requestQueue.add(request);


    }

    public void BuscarUsuario(){

    }


}
