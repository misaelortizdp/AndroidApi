package com.example.Apibooks;

import android.os.Parcel;
import android.os.Parcelable;

public class Libro implements Parcelable {

    private String titulo;
    private String autor;
    private String img_url;

    public Libro(String titulo, String autor, String img_url) {
        this.titulo = titulo;
        this.autor = autor;
        this.img_url = img_url;
    }



    protected Libro(Parcel in) {
        titulo = in.readString();
        autor = in.readString();
        img_url = in.readString();
    }

    public static final Parcelable.Creator<Libro> CREATOR = new Parcelable.Creator<Libro>() {
        @Override
        public Libro createFromParcel(Parcel in) {
            return new Libro(in);
        }

        @Override
        public Libro[] newArray(int size) {
            return new Libro[size];
        }
    };

    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getImg_url() {
        return img_url;
    }

    public void setImg_url(String img_url) {
        this.img_url = img_url;
    }

    @Override
    public String toString() {
        return "Libro{" +
                "titulo='" + titulo + '\'' +
                ", autor='" + autor + '\'' +
                ", img_url='" + img_url + '\'' +
                '}';
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(titulo);
        dest.writeString(autor);
        dest.writeString(img_url);
    }
}
