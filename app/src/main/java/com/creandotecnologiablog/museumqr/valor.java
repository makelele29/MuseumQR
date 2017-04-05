package com.creandotecnologiablog.museumqr;

import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.NumberPicker;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;
import java.util.StringTokenizer;

import static com.creandotecnologiablog.museumqr.R.color.colorOK;

public class valor extends AppCompatActivity {
    private int correctaN;
    private String correctaS;
    private boolean jugado;
    private MenuItem salir;
    private  boolean correcto;
    public static final String OK="ok";
    private NumberPicker number;
    private String info;
    private boolean numero;
    private EditText edit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_valor);

            Pregunta object=(Pregunta) getIntent().getSerializableExtra(Principal.DATOS);
            ((TextView)findViewById(R.id.txtPregunta)).setText(object.getPregunta());
            String tipo=getIntent().getStringExtra(Principal.TIPO);
            if(!tipo.equals("texto")){
                correctaN=((Valor)object).getCorrecta();
                number=new NumberPicker(this);
                number.setMinValue(0);
                Random r=new Random();
                numero=true;
                number.setMaxValue(correctaN+ r.nextInt(20));
                //number.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT,ViewGroup.LayoutParams.WRAP_CONTENT));

                ((LinearLayout)findViewById(R.id.layoutRespuesta)).addView(number);
            }else{
                correctaS= ((ValorTexto)object).getCorrecta();
                numero=false;
                edit=new EditText(this);
                edit.setLayoutParams(new ActionBar.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT));
                edit.setGravity(Gravity.CENTER);
                ((LinearLayout)findViewById(R.id.layoutRespuesta)).addView(edit);

            }
            info=object.getInfo();

        jugado=false;

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
    public void click(View v){
        if(!jugado){
            jugado=true;
            if(numero) {
                int valor = number.getValue();

                if (valor == correctaN) {
                    correcto = true;
                    number.setBackgroundColor(getResources().getColor(colorOK));
                } else {

                    number.setBackgroundColor(getResources().getColor(R.color.colorFail));
                    TextView infoview = (TextView) findViewById(R.id.info);
                    infoview.setText(info);
                    infoview.setVisibility(View.VISIBLE);
                    correcto = false;
                }
                number.setEnabled(false);

            }else{
                String valor=edit.getText().toString();
                if(valor.equals(correctaS)){
                    correcto = true;
                    edit.setBackgroundColor(getResources().getColor(colorOK));
                } else {

                    edit.setBackgroundColor(getResources().getColor(R.color.colorFail));
                    TextView infoview = (TextView) findViewById(R.id.info);
                    infoview.setText(info);
                    infoview.setVisibility(View.VISIBLE);
                    correcto = false;
                }
                edit.setEnabled(false);

            }
            salir.setVisible(true);
        }


    }
}
