package com.nr.servicios;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.text.DateFormat;
import java.util.Date;

public class FacturaActivity extends AppCompatActivity implements View.OnClickListener {
    Spinner tiposer;
    TextView numerof,valorf,valorfc,nombreu;
    Button pagarf;
    daoFactura daof;
    daoUsuario daou;
    Usuario u;
    int id=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_factura);
        tiposer=findViewById(R.id.tiposer);
        numerof=findViewById(R.id.numerof);
        valorf=findViewById(R.id.valorf);
        valorfc=findViewById(R.id.valorfc);
        pagarf=findViewById(R.id.pagarf);
        pagarf.setOnClickListener(this);


        Bundle b=getIntent().getExtras();
        id=b.getInt("id");
        daou=new daoUsuario(this);
        daof = new daoFactura(this);
        u=daou.getUsuarioById(id);

    }


    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.pagarf:
                String num=numerof.getText().toString();
                String vaf=valorf.getText().toString();
                String vafc=valorfc.getText().toString();
                Factura f=new Factura();
                f.setTipo(tiposer.getSelectedItem().toString());
                f.setNumeroF(numerof.getText().toString());
                f.setValor(valorf.getText().toString());

                if(num.equals("")|| vaf.equals("")|| vafc.equals("")) {
                    Toast.makeText(this, "Error:campos vacios", Toast.LENGTH_LONG).show();
                }
                else if(!validarnumero(num)){
                    numerof.setError("Requiere 10 Digitos");
                }
                else if (!vaf.equals(vafc)) {
                    valorfc.setError("El valor a pagar no coinciden");
                }
                else if  (!(Integer.parseInt(vaf)<=1000)){
                    Toast.makeText(this, "No tiene suficiente Saldo!", Toast.LENGTH_LONG).show();
                }
                else   {
                   // daou.retirar(u,Integer.parseInt(vaf));
                    daof.insertFactura(f);
                    Toast.makeText(this, "Pago exitoso!", Toast.LENGTH_LONG).show();

                }
                break;

        }

    }
    private boolean validarnumero(String con) {
        return con.length() ==10;
    }
}