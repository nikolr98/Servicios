package com.nr.servicios;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;

public class daoUsuario {
    Context c;
    Usuario u;
    ArrayList<Usuario>lista;
    SQLiteDatabase sql;
    String bd="BDServicio";
    String tabla="create table if not exists usuario(id integer primary key autoincrement,nombre text,email text,password text,sactual integer)";

    public daoUsuario(Context c) {
        this.c = c;
        sql=c.openOrCreateDatabase(bd,c.MODE_PRIVATE,null);
        sql.execSQL(tabla);
        u=new Usuario();
    }
    public boolean insertUsuario(Usuario u){
        if(buscar(u.getEmail())==0){
            ContentValues cv=new ContentValues();
            cv.put("nombre",u.getNombre());
            cv.put("email",u.getEmail());
            cv.put("password",u.getPassword());
            cv.put("sactual",u.getSactual());
            return (sql.insert("usuario",null,cv)>0);
        }else {
            return false;
        }
    }
    public int buscar(String u){
        int x=0;
        lista = selectUsuario();
        for (Usuario us:lista){
            if(us.getEmail().equals(u)) {
                x++;
            }
        }
        return x;
    }
    public ArrayList<Usuario> selectUsuario(){
        ArrayList<Usuario> lista = new ArrayList<Usuario>();
        lista.clear();
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do {
                Usuario u = new Usuario();
                u.setId(cr.getInt(0));
                u.setNombre(cr.getString(1));
                u.setEmail(cr.getString(2));
                u.setPassword(cr.getString(3));
                lista.add(u);
            }while(cr.moveToNext());
        }
        return lista;
    }
    public int login(String u,String co){
        int a=0;
        Cursor cr = sql.rawQuery("select * from usuario", null);
        if(cr!=null && cr.moveToFirst()){
            do {
                if (cr.getString(2).equals(u)&&cr.getString(3).equals(co)){
                    a++;
                }
            }while(cr.moveToNext());
        }
        return a;
    }
    public Usuario getUsuario(String u, String co){
        lista = selectUsuario();
        for (Usuario us:lista){
            if(us.getEmail().equals(u) && us.getPassword().equals(co)){
                return us;
            }
        }
        return null;
    }
    public Usuario getUsuarioById(int id){
        lista = selectUsuario();
        for (Usuario us:lista){
            if(us.getId() == id){
                return us;
            }
        }
        return null;
    }
   /* public boolean retirar (Usuario u, int sal){
        ContentValues cv = new ContentValues();
        cv.put("monto", u.getSactual() - sal);
        return (sql.update("usuario", cv,"id="+u.getId(), null) > 0);

    }*/

}

