package com.example.Apibooks;

import android.os.Parcel;
import android.os.Parcelable;

public class Lista implements Parcelable {

    private String nombre;
    private String fantigua;
    private String freciente;


    public Lista(String nombre, String fantigua, String freciente) {
        this.nombre = nombre;
        this.fantigua = fantigua;
        this.freciente = freciente;
    }

    protected Lista(Parcel in) {
        nombre = in.readString();
        fantigua = in.readString();
        freciente = in.readString();
    }

    public static final Parcelable.Creator<Lista> CREATOR = new Parcelable.Creator<Lista>() {
        @Override
        public Lista createFromParcel(Parcel in) {
            return new Lista(in);
        }

        @Override
        public Lista[] newArray(int size) {
            return new Lista[size];
        }
    };

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getFantigua() {
        return fantigua;
    }

    public void setFantigua(String fantigua) {
        this.fantigua = fantigua;
    }

    public String getFreciente() {
        return freciente;
    }

    public void setFreciente(String freciente) {
        this.freciente = freciente;
    }

    @Override
    public String toString() {
        return "Lista{" +
                "nombre='" + nombre + '\'' +
                ", fantigua='" + fantigua + '\'' +
                ", freciente='" + freciente + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(nombre);
        dest.writeString(fantigua);
        dest.writeString(freciente);
    }
}
