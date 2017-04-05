package com.creandotecnologiablog.museumqr;

import android.app.Activity;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.icu.text.SimpleDateFormat;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.text.method.ScrollingMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.ByteArrayOutputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Locale;
import java.util.Objects;

public class Principal extends AppCompatActivity {

    private String tipo;
    private ArrayList<Pregunta> qrs;
    private int mision;
    private Chronometer crono;
    private static final int PREGUNTAS = 0;
    static final String DATOS = "json";
    static final String TIPO = "tipo";
    private double total;
    private double score;
    private boolean terminado;
    private int codigo;
    private MenuItem itemCodigo;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_principal);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(!terminado) {
                    IntentIntegrator integrator = new IntentIntegrator(Principal.this);
                    integrator.setDesiredBarcodeFormats(IntentIntegrator.ALL_CODE_TYPES);
                    integrator.setPrompt(getResources().getString(R.string.scan));
                    integrator.setCameraId(0);  // Use a specific camera of the device
                    integrator.setBeepEnabled(false);
                    integrator.setOrientationLocked(false);
                    integrator.initiateScan();
                }
            }
        });
        ((TextView)findViewById(R.id.txtDescripcion)).setMovementMethod(new ScrollingMovementMethod());
        qrs=new ArrayList<>();
        mision=0;
        crono=(Chronometer)findViewById(R.id.tiempo);
        total=0;
        terminado=false;

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_principal, menu);
        itemCodigo=menu.findItem(R.id.codigo);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        switch (id){
            case R.id.codigo:
                mostarDialogo();
                break;
        }

        //noinspection SimplifiableIfStatement

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == PREGUNTAS && resultCode==RESULT_OK) {
            if(data.getExtras().getBoolean(respuestas.OK)){
                total+=score;
                ((TextView)findViewById(R.id.puntuacion)).setText(""+total);
            }
            cargarSiguienteMision();
        } else {
                IntentResult result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data);
                if (result != null) {

                    if (result.getContents() == null) {
                        Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
                    } else {

                        leerQR(result.getContents());

                    }

                } else{
                    super.onActivityResult(requestCode, resultCode, data);
            }
        }
    }
    public void leerQR(String data){

        try {
            JSONObject object = new JSONObject(data);
            if(object.has("start")){
                ((LinearLayout)findViewById(R.id.score)).setVisibility(View.VISIBLE);
                cargarQR(object.getString("child"));

            }else{

                if(object.getInt("qr")==qrs.get(mision).getQr()){
                    Intent intent=null;
                    switch (qrs.get(mision).getTipo()){
                        case "respuestas":

                            intent=new Intent(this,respuestas.class);
                            intent.putExtra(DATOS,((Respuesta)qrs.get(mision)));
                            break;
                        case "valor":
                        case "texto":
                            intent=new Intent(this,valor.class);
                            intent.putExtra(TIPO,qrs.get(mision).getTipo());
                            intent.putExtra(DATOS,(qrs.get(mision)));

                    }
                    startActivityForResult(intent,PREGUNTAS);
                    Log.i("Hola",object.getString("pregunta"));

                }else
                    Toast.makeText(Principal.this,getResources().getString(R.string.errorQR),Toast.LENGTH_LONG).show();
            }

        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    public void cargarQR(String child){
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference sala = database.getReference(child).child(""+codigo);

            sala.addValueEventListener(new ValueEventListener() {
                @Override
                public void onDataChange(DataSnapshot dataSnapshot) {
                    if(dataSnapshot.getValue()!=null) {
                        itemCodigo.setVisible(false);
                        tipo = dataSnapshot.child("tipo").getValue(String.class);

                        switch (tipo) {
                            case "Mision":
                                cargarMision(dataSnapshot);
                                break;
                        }
                    }else
                        Toast.makeText(Principal.this,getResources().getString(R.string.errorSala),Toast.LENGTH_LONG);

                }

                @Override
                public void onCancelled(DatabaseError databaseError) {
                    // ...
                }
            });
        ChildEventListener listener = new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {


            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {
                int pos=Integer.parseInt(dataSnapshot.getKey())-1;
                qrs.remove(pos);
                switch (dataSnapshot.child("tipo").getValue(String.class)){
                    case "respuestas":
                        qrs.add(pos,dataSnapshot.getValue(Respuesta.class));
                        break;
                    case "valor":
                        qrs.add(pos,dataSnapshot.getValue(Valor.class));
                        break;
                    case "texto":
                        qrs.add(pos,dataSnapshot.getValue(ValorTexto.class));
                        break;
                }

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {


            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
            // ...
        };
        String idioma=Locale.getDefault().getLanguage();
        sala.child(idioma).child("qrs").addChildEventListener(listener);


    }
    public void cargarMision(DataSnapshot dataSnapshot){
        String idioma=Locale.getDefault().getLanguage();
        Log.i("Idioma",idioma);
        dataSnapshot=dataSnapshot.child(idioma);
        TextView des=(TextView)findViewById(R.id.txtDescripcion);
        des.setText(dataSnapshot.child("mision").getValue(String.class));
        ((TextView)findViewById(R.id.txtTitulo)).setText(getResources().getString(R.string.mision)+" "+(mision+1));
        dataSnapshot=dataSnapshot.child("qrs");
        for(DataSnapshot data:dataSnapshot.getChildren()){
            switch (data.child("tipo").getValue(String.class)){
                case "respuestas":
                    qrs.add(data.getValue(Respuesta.class));
                    break;
                case "valor":
                    qrs.add(data.getValue(Valor.class));
                    break;
                case "texto":
                    qrs.add(data.getValue(ValorTexto.class));
                    break;
            }

        }


        score=100/qrs.size();
        crono.start();

    }
    public void cargarSiguienteMision(){
        if((qrs.size()-1)!=mision) {
            ((TextView) findViewById(R.id.txtDescripcion)).setText(qrs.get(mision).getSiguiente());
            mision++;
            ((TextView) findViewById(R.id.txtTitulo)).setText(getResources().getString(R.string.mision) + " " + (mision + 1));
        }else{
            ((TextView) findViewById(R.id.txtTitulo)).setText(getResources().getString(R.string.terminado));
            ((TextView) findViewById(R.id.txtDescripcion)).setText(getResources().getString(R.string.descterminar));
            terminado=true;
            crono.stop();

        }

    }
    public void  mostarDialogo(){
        final AlertDialog.Builder builder = new AlertDialog.Builder(this);
        // Get the layout inflater
        LayoutInflater inflater = getLayoutInflater();
        final View v=inflater.inflate(R.layout.dialogo, null);
        final EditText editcodigo=(EditText) v.findViewById(R.id.editcodigo);


        builder.setView(v) // Add action buttons
                .setPositiveButton("Guardar", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int id) {
                        codigo=0;
                    if(editcodigo.getText().toString().length()>0)
                        codigo=Integer.parseInt( editcodigo.getText().toString());
                    Toast.makeText(getApplicationContext(),"El código introducido es "+codigo,Toast.LENGTH_LONG).show();



                    }
                })
                .setNegativeButton("Cancelar", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        dialog.cancel();

                    }
                });
        builder.show();
    }
}
