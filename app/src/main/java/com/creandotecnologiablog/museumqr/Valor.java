package com.creandotecnologiablog.museumqr;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Valor extends Pregunta{
    private int correcta;
    private String campo;

    public Valor(){
        correcta=0;
        campo="";
    }

    public Valor(String pregunta, String tipo, int qr, String siguiente, String info, int correcta, String campo) {
        super(pregunta, tipo, qr, siguiente, info);
        this.correcta = correcta;
        this.campo = campo;
    }

    public String getCampo() {
        return campo;
    }

    public void setCampo(String campo) {
        this.campo = campo;
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
                "\",\"campo\":\""+getCampo()+
                "\",\"info\":\""+getInfo()+
                "\",\"correcta\":"+getCorrecta()
                +"}";

    }
}
