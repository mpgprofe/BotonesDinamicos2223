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

import java.util.Random;


public class MainActivity extends AppCompatActivity {
    Button bCrear,bBorrar;
    EditText nBotones;
    GridLayout tabla;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        bCrear = findViewById(R.id.buttonCrear);
        nBotones = findViewById(R.id.editTextNBotones);
        tabla = findViewById(R.id.layoutTabla);
        bBorrar = findViewById(R.id.buttonBorrar);

        bCrear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int cantidad = Integer.parseInt(nBotones.getText().toString());
                for (int i=0; i < cantidad; i++){
                    Button botonAux = new Button(MainActivity.this);
                    botonAux.setLayoutParams(new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                    botonAux.setText("Botón "+i);
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

    }

    int dameColorAleatorio(){
        Random random = new Random();
        return Color.argb(255, random.nextInt(256), random.nextInt(256),random.nextInt(256));

    }
}