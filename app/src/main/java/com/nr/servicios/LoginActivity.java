package com.nr.servicios;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    EditText email,contraseña;
    Button login;
    TextView register;
    daoUsuario dao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email=(EditText)findViewById(R.id.username);
        contraseña=(EditText)findViewById(R.id.password);
        login=(Button)findViewById(R.id.login);
        register=(TextView) findViewById(R.id.cuentaaqui);
        login.setOnClickListener(this);
        register.setOnClickListener(this);
        dao=new daoUsuario(this);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.login:
                String u=email.getText().toString();
                String co=contraseña.getText().toString();
                if(u.equals("")&&co.equals("")){
                    Toast.makeText(this,"Error:campos vacios",Toast.LENGTH_LONG).show();
                } else if (dao.login(u,co)==1) {
                    Usuario ux=dao.getUsuario(u,co);
                    Toast.makeText(this,"Datos correctos!!",Toast.LENGTH_LONG).show();
                    Intent intent1=new Intent(LoginActivity.this,MainActivity.class);
                    intent1.putExtra("id",ux.getId());
                    startActivity(intent1);
                    finish();
                }else  {
                    Toast.makeText(this,"Usuario y/o contraseña incorrectos",Toast.LENGTH_LONG).show();
                }
                break;
            case R.id.cuentaaqui:
                Intent intent=new Intent(LoginActivity.this,RegisterActivity.class);
                startActivity(intent);
                break;
        }
    }
}