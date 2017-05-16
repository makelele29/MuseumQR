package com.creandotecnologiablog.museumqr;

import java.util.HashMap;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Respuesta extends Pregunta {


    private HashMap<String,String> respuesta1,respuesta2,respuesta3,respuesta4;
    private int correcta;
    public Respuesta(){
        correcta=0;

    }

    public String getRespuesta1() {
        String resp="";
        if(respuesta1.containsKey(getIdioma()))
            resp=respuesta1.get(getIdioma());
        else
            resp=respuesta1.get("es");
        return resp;
    }

    public void setRespuesta1(HashMap<String, String> respuesta1) {
        this.respuesta1 = respuesta1;
    }

    public String getRespuesta2() {
        String resp="";
        if(respuesta2.containsKey(getIdioma()))
            resp=respuesta2.get(getIdioma());
        else
            resp=respuesta2.get("es");
        return resp;
    }

    public void setRespuesta2(HashMap<String, String> respuesta2) {
        this.respuesta2 = respuesta2;
    }

    public String getRespuesta3() {
        String resp="";
        if(respuesta3.containsKey(getIdioma()))
            resp=respuesta3.get(getIdioma());
        else
            resp=respuesta3.get("es");
        return resp;
    }

    public void setRespuesta3(HashMap<String, String> respuesta3) {
        this.respuesta3 = respuesta3;
    }

    public String getRespuesta4() {
        String resp="";
        if(respuesta4.containsKey(getIdioma()))
            resp=respuesta4.get(getIdioma());
        else
            resp=respuesta4.get("es");
        return resp;
    }

    public void setRespuesta4(HashMap<String, String> respuesta4) {
        this.respuesta4 = respuesta4;
    }

    public Respuesta(HashMap<String, String> pregunta, HashMap<String, String> respuesta1, HashMap<String, String> respuesta2, HashMap<String, String> respuesta3, HashMap<String, String> respuesta4, int correcta) {
        super(pregunta);
        this.respuesta1 = respuesta1;
        this.respuesta2 = respuesta2;
        this.respuesta3 = respuesta3;
        this.respuesta4 = respuesta4;
        this.correcta = correcta;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }


}
