package com.creandotecnologiablog.museumqr;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Texto extends Pregunta{
    private String correcta;

    public Texto(){
        correcta="";
    }

    public Texto(String pregunta, String tipo, int qr, String siguiente, String info, String correcta, String campo) {
        super(pregunta, tipo, qr, siguiente, info);
        this.correcta = correcta;
    }


    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }
    @Override
    public String toString(){
        return "{\"pregunta\":\""+getPregunta()+
                "\",\"info\":\""+getInfo()+
                "\",\"correcta\":\""+getCorrecta()
                +"\"}";

    }
}
