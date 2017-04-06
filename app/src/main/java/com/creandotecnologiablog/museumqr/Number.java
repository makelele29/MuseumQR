package com.creandotecnologiablog.museumqr;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Number extends Pregunta{
    private int correcta;

    public Number(){
        correcta=0;
    }

    public Number(String pregunta, String tipo, int qr, String siguiente, String info, int correcta) {
        super(pregunta, tipo, qr, siguiente, info);
        this.correcta = correcta;
    }


    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }
    @Override
    public String toString(){
        return "{\"pregunta\":\""+getPregunta()+
                "\",\"info\":\""+getInfo()+
                "\",\"correcta\":"+getCorrecta()
                +"}";

    }
}
