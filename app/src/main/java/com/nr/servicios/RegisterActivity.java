package com.nr.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.regex.Pattern;

public class RegisterActivity extends AppCompatActivity implements View.OnClickListener {
    Toolbar toolbar;
    EditText nombre,contraseña,identificacion,email,contraseñac;
    Button register;
    daoUsuario dao;
    Spinner tipodoc;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        nombre=(EditText)findViewById(R.id.username);
        contraseña=(EditText)findViewById(R.id.password);
        contraseñac=(EditText)findViewById(R.id.passwordc);
        email=(EditText)findViewById(R.id.email);
        register=(Button) findViewById(R.id.registro);
        register.setOnClickListener(this);
        dao=new daoUsuario(this);

        mostrartoolbar(getResources().getString(R.string.toolbar_tittle_create),true);

    }
    public void mostrartoolbar(String titulo, boolean upButton){
        Toolbar toolbar=(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setTitle(titulo);
        getSupportActionBar().setDisplayHomeAsUpEnabled(upButton);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.registro:
                String email1=email.getText().toString();
                String pass=contraseña.getText().toString();
                String pass1=contraseñac.getText().toString();
                Usuario u=new Usuario();
                u.setNombre(nombre.getText().toString());
                u.setEmail(email.getText().toString());
                u.setPassword(contraseña.getText().toString());



                if(!u.isNull()) {
                    Toast.makeText(this, "Error:campos vacios", Toast.LENGTH_LONG).show();
                }
                else if(!validarContraseña(pass)){
                    contraseña.setError("Utilice minimo 8 caracteres");
                }
                else if (!pass1.equals(pass)) {
                    contraseñac.setError("Las contraseñas no coinciden");
                }
                else if (!validarEmail(email1)) {
                    email.setError("email no valido");
                }
                else if (!pass.matches(".*\\d.*")) {
                    contraseña.setError("Utilice numeros tambien");
                }
                else if (!pass.matches(".*[a-z].*")){
                    contraseña.setError("Utilice minusculas tambien");
                }
                else if (dao.insertUsuario(u)) {
                    Toast.makeText(this, "Registro exitoso!!", Toast.LENGTH_LONG).show();
                    Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                    startActivity(intent);
                    finish();

                } else {
                    Toast.makeText(this, "Usuario ya Registrado", Toast.LENGTH_LONG).show();
                }



                break;

        }
    }

    private boolean validarEmail(String email1) {
        Pattern pattern = Patterns.EMAIL_ADDRESS;
        return pattern.matcher(email1).matches();
    }
    private boolean validarContraseña(String con) {
        return con.length() >= 8;
    }


}
