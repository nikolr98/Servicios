package com.nr.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoFactura {
    Context c;
    Factura f;
    SQLiteDatabase sql;
    String bd="BDServicio";
    String tabla="create table if not exists factura(id integer primary key autoincrement,tipo text,numerof text,valor text)";

    public daoFactura(Context c) {
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        f=new Factura();
    }
    public boolean insertFactura(Factura f){

            ContentValues cv=new ContentValues();
            cv.put("tipo",f.getTipo());
            cv.put("numerof",f.getNumeroF());
            cv.put("valor",f.getValor());
            return (sql.insert("factura",null,cv)>0);
    }


}
