package com.aperez.apps.prueba2_Anthony_Perez_Perez;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class Activity_Cliente_Login_ADPP extends AppCompatActivity {


    private EditText editTextLoginUsuario;
    private EditText editTextLoginContraseña;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cliente_login_adpp);


        editTextLoginUsuario = findViewById(R.id.editTextLoginUsuario);
        editTextLoginContraseña = findViewById(R.id.editTextLoginContraseña);

    }

    public void onClicLogin(View view) {
        if (editTextLoginUsuario.getText().toString().equals("") && editTextLoginContraseña.getText().toString().equals("")) {
            Toast.makeText(this, "Ingrese un usuario y contraseña correcta", Toast.LENGTH_SHORT).show();
        } else {
            if(editTextLoginUsuario.getText().toString().equals("morty") && editTextLoginContraseña.getText().toString().equals("12345") ){
                Intent intent = new Intent(this, MainActivity_ADPP.class);
                startActivity(intent);
            }else {
                Toast.makeText(this, "Usuario o contraseña incorrecta", Toast.LENGTH_SHORT).show();
            }

        }

    }
}