package com.example.botonesdinamicos2223;

import androidx.appcompat.app.AppCompatActivity;
import androidx.gridlayout.widget.GridLayout;

import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button bCrear,bBorrar, bBorrarPares;
    EditText nBotones;
    GridLayout tabla;
    int[] idbotones;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCrear = findViewById(R.id.buttonCrear);
        nBotones = findViewById(R.id.editTextNBotones);
        tabla = findViewById(R.id.layoutTabla);
        bBorrar = findViewById(R.id.buttonBorrar);
        bBorrarPares = findViewById(R.id.buttonBorrarPares);

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(nBotones.getText().toString());
                idbotones = new int[cantidad];
                for (int i=0; i < cantidad; i++){
                    Button botonAux = new Button(MainActivity.this);
                    botonAux.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    botonAux.setText("Botón "+i);
                    botonAux.setId(View.generateViewId());
                    idbotones[i] = botonAux.getId();

                    botonAux.setBackgroundColor(dameColorAleatorio());
                    final int numero =i;

                    botonAux.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            view.setBackgroundColor(dameColorAleatorio());
                            Toast.makeText(MainActivity.this, "Soy el botón: "+numero, Toast.LENGTH_SHORT).show();
                        }
                    });

                    tabla.addView(botonAux);
                }
            }
        });

        bBorrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                tabla.removeAllViews();
            }
        });

        bBorrarPares.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                ArrayList<View> botones = tabla.getTouchables();
                Iterator<View> iterator = botones.iterator();

                while (iterator.hasNext()){
                    View v = iterator.next();
                    if (v instanceof Button){

                        int id = v.getId();
                        boolean encontrado = false;


                        for(int i = 0; i< idbotones.length && !encontrado; i++){
                            if (i%2==0 && id==idbotones[i]){
                                tabla.removeView(v);
                                encontrado=true;

                            }
                        }
                        /*
                        String [] cadenas = ((Button) v).getText().toString().split(" ");
                        int numero = Integer.parseInt(cadenas[1]);
                        if (numero%2==0){
                            tabla.removeView(v);
                        }
                        */
                    }
                }


            }
        });

    }

    int dameColorAleatorio(){
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256),random.nextInt(256));

    }
}