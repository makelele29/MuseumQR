package com.creandotecnologiablog.museumqr;

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

import org.json.JSONException;
import org.json.JSONObject;

import static com.creandotecnologiablog.museumqr.R.color.colorOK;

public class respuestas extends AppCompatActivity {
    private int correcta;
    private boolean jugado;
    private MenuItem salir;
    private  boolean correcto;
    public static final String OK="ok";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_respuestas);

            Respuesta object=(Respuesta) getIntent().getSerializableExtra(Principal.DATOS);

            ((TextView)findViewById(R.id.txtPregunta)).setText(object.getPregunta());
            ((Button)findViewById(R.id.uno)).setText(object.getrespuesta1());
            ((Button)findViewById(R.id.dos)).setText(object.getrespuesta2());
            ((Button)findViewById(R.id.tres)).setText(object.getrespuesta3());
            ((Button)findViewById(R.id.cuatro)).setText(object.getrespuesta4());
            correcta=object.getCorrecta();

        jugado=false;
    }
    public void click(View v){
        if(!jugado){
            jugado=true;

            int valor=Integer.parseInt((String)v.getTag());
            v.setBackgroundColor(getResources().getColor(colorOK));
            if(valor==correcta){
                correcto=true;
            }else{


                try {
                    Thread a=new Thread();
                    a.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                v.setBackgroundColor(getResources().getColor(R.color.colorFail));
                Button button=null;
                switch (correcta){
                    case 1:
                        button=(Button)findViewById(R.id.uno);
                        break;
                    case 2:
                        button=(Button)findViewById(R.id.dos);
                        break;
                    case 3:
                        button=(Button)findViewById(R.id.tres);
                        break;
                    case 4:
                        button=(Button)findViewById(R.id.cuatro);
                        break;

                }
                button.setBackgroundColor(getResources().getColor(R.color.colorOK));
                correcto=false;
            }
            salir.setVisible(true);

        }


    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_ok, menu);
        salir=menu.findItem(R.id.ok);
        salir.setVisible(false);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            case R.id.ok:
                getIntent().putExtra(OK,correcto);
                setResult(RESULT_OK,getIntent());
                finish();

        }
        return true;
    }
}
