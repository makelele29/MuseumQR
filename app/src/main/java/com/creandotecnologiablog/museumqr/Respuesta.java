package com.creandotecnologiablog.museumqr;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Respuesta extends Pregunta {


    private String respuesta1,respuesta2,respuesta3,respuesta4;
    private int correcta;
    public Respuesta(){
        correcta=0;
        respuesta1="";
        respuesta2="";
        respuesta3="";
        respuesta4="";
    }

    public Respuesta(String pregunta, String tipo, int qr, String siguiente, String info, String respuesta1, String respuesta2, String respuesta3, String respuesta4, int correcta) {
        super(pregunta, tipo, qr, siguiente, info);
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.correcta = correcta;
    }

    public String getrespuesta1() {
        return respuesta1;
    }

    public void setrespuesta1(String respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getrespuesta2() {
        return respuesta2;
    }

    public void setrespuesta2(String respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getrespuesta3() {
        return respuesta3;
    }

    public void setrespuesta3(String respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getrespuesta4() {
        return respuesta4;
    }

    public void setrespuesta4(String respuesta4) {
        this.respuesta4 = respuesta4;
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
                    "\",\"respuesta1\":\""+getrespuesta1()+
                    "\",\"respuesta2\":\""+getrespuesta2() +
                    "\",\"respuesta3\":\""+getrespuesta3()+
                    "\",\"respuesta4\":\""+getrespuesta4() +
                    "\",\"info\":\""+getInfo()+
                    "\",\"correcta\":"+getCorrecta()
                    +"}";

    }
}
