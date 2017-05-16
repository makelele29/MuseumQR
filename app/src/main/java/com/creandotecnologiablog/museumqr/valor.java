package com.creandotecnologiablog.museumqr;

import android.os.Handler;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import static com.creandotecnologiablog.museumqr.R.color.colorOK;

public class valor extends AppCompatActivity {
    private int correctaN;
    private String correctaS;
    private boolean jugado;
    private TextView txtcoins;
    private  boolean correcto;
    private TextView solucion;
    private NumberPicker number;
    private String info;
    private boolean numero;
    private EditText edit;
    private String tipo;
    private int coins;
    private int intento;
    private double[]porcentajes={0.7,0.4,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor);
            solucion=(TextView)findViewById(R.id.solucion);
            Pregunta object=(Pregunta) getIntent().getSerializableExtra(MenuPrincipal.DATOS);
            ((TextView)findViewById(R.id.txtPregunta)).setText(object.getPregunta());
            tipo=object.getTipo();
        txtcoins=(TextView)findViewById(R.id.coinsPregunta);
            if(!tipo.equals("texto")){

                correctaN=((Number)object).getCorrecta();
                switch(tipo){
                    case "number":
                        Number number =(Number)object;
                        edit=new EditText(this);
                        edit.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                        edit.setGravity(Gravity.CENTER);
                        edit.setInputType(InputType.TYPE_CLASS_NUMBER);
                        ((LinearLayout)findViewById(R.id.layoutRespuesta)).addView(edit);
                        break;
                    case "spinner":
                        Spinner spinner=(Spinner)object;
                        this.number =new NumberPicker(this);
                        this.number.setMinValue(spinner.getMin());
                        this.number.setMaxValue(spinner.getMax());
                        ((LinearLayout)findViewById(R.id.layoutRespuesta)).addView(this.number);
                        break;

                }

                numero=true;


            }else{

                correctaS= ((Texto)object).getCorrecta();
                numero=false;
                edit=new EditText(this);
                edit.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                edit.setGravity(Gravity.CENTER);
                ((LinearLayout)findViewById(R.id.layoutRespuesta)).addView(edit);



            }
        coins=object.getCoins();
        ((TextView)findViewById(R.id.coinsPregunta)).setText(""+object.getCoins());
        intento=3;
        ((TextView)findViewById(R.id.intentos)).setText(""+intento);


    }


    public void click(View v){
        if(intento!=0){

            if(numero) {
                int valor = 0;
                switch (tipo){
                    case "spinner":
                        valor = number.getValue();
                        if (valor == correctaN) {
                            intento=0;
                            number.setBackgroundColor(getResources().getColor(colorOK));
                        } else {

                            //number.setBackgroundColor(getResources().getColor(R.color.colorFail));
                            ((TextView)findViewById(R.id.coinsPregunta)).setText(""+(int)(coins*porcentajes[3-intento]));

                            intento--;
                            Toast.makeText(valor.this,getResources().getString(R.string.vida_menos),Toast.LENGTH_LONG).show();
                            ((TextView)findViewById(R.id.intentos)).setText(""+intento);
                            if(intento==0){

                                solucion.setText(""+correctaN);
                                solucion.setBackgroundColor(getResources().getColor(colorOK));
                            }
                        }

                    break;
                    case "number":
                        valor = Integer.parseInt(edit.getText().toString());
                        if (valor == correctaN) {
                            intento=0;
                            edit.setBackgroundColor(getResources().getColor(colorOK));
                        } else {
                            ((TextView)findViewById(R.id.coinsPregunta)).setText(""+(int)(coins*porcentajes[3-intento]));

                            intento--;
                            ((TextView)findViewById(R.id.intentos)).setText(""+intento);
                            if(intento==0){

                                solucion.setText(""+correctaN);
                                solucion.setBackgroundColor(getResources().getColor(colorOK));
                            }
                        }

                    break;
                }


            }else{
                String valor=edit.getText().toString();
                if(valor.toLowerCase().equals(correctaS.toLowerCase())){
                    intento=0;
                    edit.setBackgroundColor(getResources().getColor(colorOK));
                } else {

                    ((TextView)findViewById(R.id.coinsPregunta)).setText(""+(int)(coins*porcentajes[3-intento]));

                    intento--;
                    Toast.makeText(valor.this,getResources().getString(R.string.vida_menos),Toast.LENGTH_LONG).show();
                    ((TextView)findViewById(R.id.intentos)).setText(""+intento);
                    if(intento==0){

                        solucion.setText(""+correctaS);
                        solucion.setBackgroundColor(getResources().getColor(colorOK));
                    }
                }


            }
            if(intento==0)
                terminar();

        }


    }
    public void terminar(){
        Handler handler=new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                getIntent().putExtra(MenuPrincipal.OK,Integer.parseInt(txtcoins.getText().toString()));
                setResult(RESULT_OK,getIntent());
                finish();

            }
        },3000);
    }
}
