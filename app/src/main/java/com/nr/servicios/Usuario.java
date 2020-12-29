package com.nr.servicios;

public class Usuario {
    int Id,Sactual=1000;
    String Nombre;
    String Email;
    String Password;
    public Usuario() {
    }
    public Usuario(String nombre, String email, String password, String identificacion,String tipo, int sactual) {
        Nombre = nombre;
        Email = email;
        Password = password;
        Sactual=sactual;
    }
    public boolean isNull(){
        if (Nombre.equals("") || Email.equals("")||Password.equals("")){
            return false;
        }else {
            return true;
        }
    }
    @Override
    public String toString() {
        return "Usuario{" +
                "Id=" + Id +
                ", Nombre='" + Nombre + '\'' +
                ", Email='" + Email + '\'' +
                ", Password='" + Password + '\'' +
                ", Sactual='" + Sactual + '\'' +
                '}';
    }
    public int getId() {
        return Id;
    }

    public void setId(int id) {
        Id = id;
    }

    public String getNombre() {
        return Nombre;
    }

    public void setNombre(String nombre) {
        Nombre = nombre;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getPassword() {
        return Password;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public int getSactual() {
        return Sactual;
    }

    public void setSactual(int sactual) {
        Sactual = sactual;
    }
}
