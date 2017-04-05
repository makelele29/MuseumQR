package com.creandotecnologiablog.museumqr;

import java.io.Serializable;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Pregunta implements Serializable {
    private String pregunta;
    private String tipo;
    private int qr;
    private String siguiente;
    private String info;

    public Pregunta(){
        pregunta="";
        tipo="";
        qr=-1;
        siguiente="";
        info="";
    }

    public Pregunta(String pregunta, String tipo, int qr, String siguiente, String info) {
        this.pregunta = pregunta;
        this.tipo = tipo;
        this.qr = qr;
        this.siguiente = siguiente;
        this.info = info;
    }

    public String getSiguiente() {
        return siguiente;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public void setSiguiente(String siguiente) {
        this.siguiente = siguiente;
    }

    public String getPregunta() {
        return pregunta;
    }

    public void setPregunta(String pregunta) {
        this.pregunta = pregunta;
    }

    public int getQr() {
        return qr;
    }

    public void setQr(int qr) {
        this.qr = qr;
    }


    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }
}
