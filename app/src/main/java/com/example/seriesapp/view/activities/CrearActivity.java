package com.example.seriesapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.seriesapp.R;
import com.example.seriesapp.model.entities.Serie;
import com.example.seriesapp.model.repository.SerieRepository;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.model.Document;

import javax.xml.parsers.DocumentBuilder;

public class CrearActivity extends AppCompatActivity {
    private static final String NOMBRE_COLECCION = "series";
    SerieRepository miSerieRepository;
    EditText etCrearNombre, etCrearDescripcion, etCrearUI;
    Button btCrearGuardar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_crear);
        miSerieRepository = new SerieRepository(CrearActivity.this);
        referenciarElementos();
    }

    private void referenciarElementos(){
        etCrearNombre = findViewById(R.id.et_crear_nombre);
        etCrearDescripcion = findViewById(R.id.et_crear_descripcion);
        etCrearUI = findViewById(R.id.et_crear_link);
        btCrearGuardar = findViewById(R.id.bt_crear_guardar);
    }

    public void clickGuardar(View view){
        String nombre = etCrearNombre.getText().toString();
        String descripcion = etCrearDescripcion.getText().toString();
        String url = etCrearUI.getText().toString();

        if("".equals(nombre)){

            etCrearNombre.setError(getString(R.string.crear_error));
            return;
        }
        if("".equals(descripcion)){
            etCrearNombre.setError(getString(R.string.crear_error));
            return;
        }
        if("".equals(url)){
            etCrearNombre.setError(getString(R.string.crear_error));
            return;
        }
        Serie serie = new Serie(nombre,descripcion,url);
        //miSerieRepository.insertarSerie(serie);
        Intent datos = new Intent();
        datos.putExtra("datos_de_serie",serie);
        setResult(RESULT_OK, datos);

        FirebaseFirestore firestore = FirebaseFirestore.getInstance();
        firestore.collection("series").add(serie);

        //finish();
    }
}