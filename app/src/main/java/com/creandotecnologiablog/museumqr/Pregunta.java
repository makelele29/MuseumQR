package com.creandotecnologiablog.museumqr;

import android.content.Intent;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Locale;

/**
 * Created by makelele29 on 3/04/17.
 */

public class Pregunta implements Serializable {
    private HashMap<String,String> pregunta;
    private String idioma=Locale.getDefault().getLanguage();
    private String tipo;
    private int coins;
    private HashMap<String,Integer> objetivos;



    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public Pregunta() {
        pregunta=new HashMap<>();
        coins=0;
        tipo="";
        objetivos=new HashMap<>();
    }

    public int getCoins() {
        return coins;
    }

    public void setCoins(int coins) {
        this.coins = coins;
    }

    public Pregunta(HashMap<String, String> pregunta) {
        this.pregunta = pregunta;
    }

    public String getPregunta() {

        String resp="";
        if(pregunta.containsKey(idioma))
            resp=pregunta.get(idioma);
        else
            resp=pregunta.get("es");
        return resp;
    }

    public String getIdioma() {
        return idioma;
    }

    public void setIdioma(String idioma) {
        this.idioma = idioma;
    }

    public void setPregunta(HashMap<String, String> pregunta) {
        this.pregunta = pregunta;
    }
    public HashMap<String, Integer> getObjetivos() {
        return objetivos;
    }

    public void setObjetivos(HashMap<String, Integer> objetivos) {
        this.objetivos = objetivos;
    }

}
