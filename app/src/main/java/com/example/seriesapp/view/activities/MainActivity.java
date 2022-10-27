package com.example.seriesapp.view.activities;

import androidx.activity.result.ActivityResult;
import androidx.activity.result.ActivityResultCallback;
import androidx.activity.result.ActivityResultLauncher;
import androidx.activity.result.contract.ActivityResultContracts;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.seriesapp.R;
import com.example.seriesapp.model.entities.Serie;
import com.example.seriesapp.model.local.SerieDAO;
import com.example.seriesapp.model.local.BaseDeDatos;
import com.example.seriesapp.model.repository.SerieRepository;
import com.example.seriesapp.view.adapters.AdaptadorPersonalizado;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SerieRepository miSerieRepository;
    private RecyclerView rvSeries;
    private ArrayList<Serie> listadoSeries;
    private Button btCrear;
    private AdaptadorPersonalizado adaptador;
    private MenuItem mi_cerrar;

    @Override
    public boolean onCreateOptionsMenu(@NonNull Menu menu) {
        getMenuInflater().inflate(R.menu.menu_navbar,menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()){
            case R.id.it_menu_cerrar:
                SharedPreferences sharedPreferences = getSharedPreferences("preferencias",MODE_PRIVATE);
                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putBoolean("logeado",false);
                editor.apply();


                Intent intent = new Intent(this,LoginActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP| Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);  // cierra las activities

                startActivity(intent);
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        btCrear = findViewById(R.id.button_1_nuevaserie);
        setContentView(R.layout.activity_main);

        miSerieRepository = new SerieRepository(getApplicationContext());

        listadoSeries = new ArrayList<Serie>();
        cargarDatosBasesDatos();
        adaptador = new AdaptadorPersonalizado(listadoSeries);



        rvSeries = findViewById(R.id.rv_listado);
        rvSeries.setLayoutManager(new LinearLayoutManager(this));
        rvSeries.setAdapter(adaptador);

    }

    private void cargarDatosBasesDatos(){

        FirebaseFirestore db = FirebaseFirestore.getInstance();
        db.collection("series").get().addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
            @Override
            public void onComplete(@NonNull Task<QuerySnapshot> task) {
                if(task.isSuccessful()){
                    listadoSeries.clear();
                    for (DocumentSnapshot documento : task.getResult()){
                        Serie miSerie = documento.toObject(Serie.class);
                        listadoSeries.add(miSerie);

                    }
                    adaptador.setListadoDatos(listadoSeries);
                }
                else {
                    Log.e("ErrorFS",task.getException().getMessage());
                }
            }
        });
       /* if (listadoSeries.isEmpty()){
            creoPorDefecto();
        }*/
    }

    private void creoPorDefecto() {
        Serie elMentalista = new Serie("El mentalista","Patrick jane",
                "https://pics.filmaffinity.com/El_mentalista_Serie_de_TV-630075500-large.jpg");
        Serie theSopranos = new Serie("The Sopranos","Italian gangster family","https://m.media-amazon.com/images/M/MV5BZGJjYzhjYTYtMDBjYy00OWU1LTg5OTYtNmYwOTZmZjE3ZDdhXkEyXkFqcGdeQXVyNTAyODkwOQ@@._V1_.jpg");
        miSerieRepository.insertarSerie(elMentalista);
        miSerieRepository.insertarSerie(theSopranos);
        listadoSeries =  (ArrayList<Serie>) miSerieRepository.obtenerTodasSeries();

    }

    public void clickCrear(View  view){
    Intent intent = new Intent(this,CrearActivity.class);
    irFormulario.launch(intent);
    }

    ActivityResultLauncher<Intent> irFormulario = registerForActivityResult(
            new ActivityResultContracts.StartActivityForResult(), new ActivityResultCallback<ActivityResult>() {
                @Override
                public void onActivityResult(ActivityResult result) {

                    if(result.getResultCode()==RESULT_OK){
                        adaptador.setListadoDatos((ArrayList<Serie>) miSerieRepository.obtenerTodasSeries());

                    }

                }
            });


}