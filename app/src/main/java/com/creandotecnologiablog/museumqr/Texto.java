package com.creandotecnologiablog.museumqr;

import java.util.HashMap;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Texto extends Pregunta{
    private String correcta;

    public Texto(){
        correcta="";
    }

    public Texto(HashMap<String, String> pregunta, String correcta) {
        super(pregunta);
        this.correcta = correcta;
    }

    public String getCorrecta() {
        return correcta;
    }

    public void setCorrecta(String correcta) {
        this.correcta = correcta;
    }

}
