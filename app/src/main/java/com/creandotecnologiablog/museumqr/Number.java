package com.creandotecnologiablog.museumqr;

import java.util.HashMap;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Number extends Pregunta{
    private int correcta;

    public Number(){
        correcta=0;
    }

    public Number(HashMap<String, String> pregunta, int correcta) {
        super(pregunta);
        this.correcta = correcta;
    }

    public int getCorrecta() {
        return correcta;
    }

    public void setCorrecta(int correcta) {
        this.correcta = correcta;
    }

}
