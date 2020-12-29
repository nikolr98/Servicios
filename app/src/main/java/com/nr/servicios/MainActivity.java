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

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    TextView saldo,nombreu,fechahora;
    Button pago;
    daoUsuario daou;
    daoFactura daof;
    int id=0;
    Usuario u;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        nombreu=findViewById(R.id.nombreu);
        pago=findViewById(R.id.pago);
        fechahora=findViewById(R.id.fechahora);
        pago.setOnClickListener(this);


        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        daou=new daoUsuario(this);
        daof = new daoFactura(this);
        u=daou.getUsuarioById(id);

        nombreu.setText("Bienvenid@"+" "+u.getNombre()+"\n"+"Saldo disponible:  $"+u.getSactual());
        String currentDateTimeString = DateFormat.getDateTimeInstance().format(new Date());
        fechahora.setText(currentDateTimeString);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pago:
                Intent intent = new Intent(MainActivity.this, FacturaActivity.class);
                intent.putExtra("Id",id);
                startActivity(intent);
                finish();
                break;
        }

    }
}