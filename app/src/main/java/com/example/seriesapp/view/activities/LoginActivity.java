package com.example.seriesapp.view.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.seriesapp.R;

public class LoginActivity extends AppCompatActivity {

    private TextView et_usuario, et_contrasena;
    private Button bt_entrar,bt_registrar;

    private SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

    sharedPreferences = getSharedPreferences("preferencias",MODE_PRIVATE);
    if(sharedPreferences.getBoolean("logeado",false)){
        finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);

    }
    referencia();

    }
    ////
    private void referencia(){
        et_usuario = findViewById(R.id.et_login_usuario);
        et_contrasena = findViewById(R.id.et_login_contraseña);
    }

    public void clickIngresar(View view){
        String email = et_usuario.getText().toString();
        String contrasena = et_contrasena.getText().toString();
        if(email.equals("jmoreno844@unab.edu.co") && contrasena.equals("12345")){

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putBoolean("logeado",true);
            editor.apply();

            finish();
            Intent intent = new Intent(LoginActivity.this,MainActivity.class);
            startActivity(intent);

        } else{
            Toast.makeText(this,getString(R.string.errormsg), Toast.LENGTH_SHORT).show();
        }
    }

    public void clickRegistrar(View view){

    }
}
