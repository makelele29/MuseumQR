package com.creandotecnologiablog.museumqr;

import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import static com.creandotecnologiablog.museumqr.R.color.colorOK;

public class respuestas extends AppCompatActivity {
    private int correcta;
    private boolean jugado;
    private MenuItem salir;
    private  boolean correcto;
    private int intento;
    private TextView txtcoins;
    private int coins;
    private double[]porcentajes={0.7,0.4,0};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuestas);

        Respuesta object=(Respuesta) getIntent().getSerializableExtra(MenuPrincipal.DATOS);

        ((TextView)findViewById(R.id.txtPregunta)).setText(object.getPregunta());
        ((Button)findViewById(R.id.uno)).setText(object.getRespuesta1());
        ((Button)findViewById(R.id.dos)).setText(object.getRespuesta2());
        ((Button)findViewById(R.id.tres)).setText(object.getRespuesta3());
        ((Button)findViewById(R.id.cuatro)).setText(object.getRespuesta4());
        coins=object.getCoins();
        ((TextView)findViewById(R.id.coinsPregunta)).setText(""+object.getCoins());
        correcta=object.getCorrecta();
        intento=3;
        ((TextView)findViewById(R.id.intentos)).setText(""+intento);
        jugado=false;
        txtcoins=(TextView)findViewById(R.id.coinsPregunta);
    }
    public void click(View v){
        if(intento!=0){


            int valor=Integer.parseInt((String)v.getTag());

            if(valor==correcta){
                v.setBackgroundColor(getResources().getColor(colorOK));
                intento=0;

            }else{

                v.setBackgroundColor(getResources().getColor(R.color.colorFail));
                v.setEnabled(false);
                Button button=null;
                if(intento==1) {
                    switch (correcta) {
                        case 1:
                            button = (Button) findViewById(R.id.uno);
                            break;
                        case 2:
                            button = (Button) findViewById(R.id.dos);
                            break;
                        case 3:
                            button = (Button) findViewById(R.id.tres);
                            break;
                        case 4:
                            button = (Button) findViewById(R.id.cuatro);
                            break;

                    }

                    button.setBackgroundColor(getResources().getColor(R.color.colorOK));
                }

                ((TextView)findViewById(R.id.coinsPregunta)).setText(""+(int)(coins*porcentajes[3-intento]));

                intento--;
                Toast.makeText(respuestas.this,getResources().getString(R.string.vida_menos),Toast.LENGTH_LONG).show();
                ((TextView)findViewById(R.id.intentos)).setText(""+intento);
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
