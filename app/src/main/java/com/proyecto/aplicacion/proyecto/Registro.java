package com.proyecto.aplicacion.proyecto;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import java.util.ArrayList;

import Procesos.Procesos_Usuario;


public class Registro extends Fragment implements View.OnClickListener {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private EditText eTAdmin_usuario,eTAdmin_cedula,eTAdmin_pass,eTAdmin_cnfpass;
    private Button btnAdmin_aceptar,btnAdmin_cencelar;
    private RadioGroup rBAdmin_Opciones;
    private ArrayList<Object> Lista_Registro;

    private OnFragmentInteractionListener mListener;

    public Registro() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Registro.
     */
    // TODO: Rename and change types and number of parameters
    public static Registro newInstance(String param1, String param2) {
        Registro fragment = new Registro();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v;
        v=inflater.inflate(R.layout.fragment_registro, container, false);


        eTAdmin_usuario=(EditText) v.findViewById(R.id.eTAdmin_usuario);
        eTAdmin_cedula=(EditText) v.findViewById(R.id.eTAdmin_cedula);
        eTAdmin_pass=(EditText) v.findViewById(R.id.eTAdmin_pass);
        eTAdmin_cnfpass=(EditText) v.findViewById(R.id.eTAdmin_Cnfpass);

        rBAdmin_Opciones=(RadioGroup) v.findViewById(R.id.rBAdmin_opciones);

        btnAdmin_aceptar=(Button) v.findViewById(R.id.btnAdmin_aceptar);
        btnAdmin_cencelar=(Button) v.findViewById(R.id.btnAdmin_Cancelar);

        btnAdmin_aceptar.setOnClickListener(this);
        btnAdmin_cencelar.setOnClickListener(this);

        return v;
    }

    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach() {
        super.onDetach();
        mListener = null;
    }


    public void onRadioButtonClicked(View view) {
        // Is the button now checked?
        boolean checked = ((RadioButton) view).isChecked();

        // Check which radio button was clicked
        switch(view.getId()) {
            case R.id.rBsi:
                if (checked)
                    Toast.makeText(getActivity(),"Funciona si",Toast.LENGTH_SHORT).show();
                    break;
            case R.id.rBno:
                if (checked)
                    Toast.makeText(getActivity(),"Funciona no",Toast.LENGTH_SHORT).show();
                    break;
        }
    }


    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.btnAdmin_aceptar) {
            final int privilegios;
            Procesos_Usuario P = new Procesos_Usuario();
            Lista_Registro = new ArrayList<Object>();
            Lista_Registro.add(0, eTAdmin_usuario.getText().toString());
            Lista_Registro.add(1, eTAdmin_cedula.getText().toString());
            Lista_Registro.add(2, eTAdmin_pass.getText().toString());
            int selectedId = rBAdmin_Opciones.getCheckedRadioButtonId();
            if (selectedId == R.id.rBsi) {
                privilegios = 1;

            } else {
                privilegios = 0;
            }
            Lista_Registro.add(3, privilegios);
            P.registrarUsuario(Lista_Registro, getActivity());
        }
        //Toast.makeText(getActivity(),"Funciona",Toast.LENGTH_SHORT).show();
    }

    int validar(){

        return 1;
    }

    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }


}
