package com.example.johanfonseca.frases2;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.io.IOException;
import java.sql.SQLException;
import java.util.Calendar;


public class MainActivity extends ActionBarActivity implements View.OnClickListener{

    private SQLiteDatabase db;
    private TextView txtResultado,txtMood;
    private ImageButton bravo,feliz,triste,enamorado;
    private String[] campos = new String[]{"frase", "autor","score"};

    //private int alarma;
    //private int time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtResultado = (TextView)findViewById(R.id.txtFrase);
        txtMood = (TextView)findViewById(R.id.txtMood);
        bravo=(ImageButton)findViewById(R.id.Bbravo);
        bravo.setOnClickListener(this);
        feliz=(ImageButton)findViewById(R.id.Bfeliz);
        feliz.setOnClickListener(this);
        triste=(ImageButton)findViewById(R.id.Btriste);
        triste.setOnClickListener(this);
        enamorado=(ImageButton)findViewById(R.id.Benamorado);
        enamorado.setOnClickListener(this);
        //alarma=0;
     //   Calendar time=Calendar.getInstance();
       // Hora.setText("Hora: "+time.get(Calendar.HOUR_OF_DAY)+": "+time.get(Calendar.SECOND)+" "+time.get(Calendar.AM_PM));
       // int segundos=time.get(Calendar.SECOND);


        BaseDeDatos db2 = new BaseDeDatos(this, "phrases", null, 2);
        try {
            db2.createDataBase();
            db2.openDataBase();
        } catch (IOException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        db=db2.getWritableDatabase();

            //Cursor c=db.query("frases",campos,null,null,null,null,null);

        txtResultado.setText("");
        txtMood.setText("");
        Cursor c = db.query("frases", campos, "score=1", null, null, null, "RANDOM() LIMIT 1",null);

        txtResultado.setText("");
        txtMood.setText("");
        if (c.moveToFirst()) {
            do {
                String fras = c.getString(0);
                String aut = c.getString(1);
                String mood=c.getString(2);




                txtResultado.append(fras+"\n"+"-"+aut);
                txtMood.append(""+mood);
            }
            while (c.moveToNext());
        }

  //      Hora.setText(Integer.toString(segundo)+""+""+mayor);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onClick(View v)
    {
       // String[] campos = new String[]{"frase", "autor","score"};

        int seleccion=v.getId();

        try {
            switch (seleccion) {
                case R.id.Bbravo:
                    Cursor c = db.query("frases", campos, "score=1", null, null, null, "RANDOM() LIMIT 1",null);

                    txtResultado.setText("");
                    txtMood.setText("");
                    if (c.moveToFirst()) {
                        do {
                            String fras = c.getString(0);
                            String aut = c.getString(1);
                            String mood=c.getString(2);




                            txtResultado.append(fras+"\n"+"-"+aut);
                            txtMood.append(""+mood);
                        }
                        while (c.moveToNext());
                    }
                    break;
                case R.id.Benamorado:

                    Cursor d = db.query("frases", campos, "score=2", null, null, null, "RANDOM() LIMIT 1",null);

                    txtResultado.setText("");
                    txtMood.setText("");
                    if (d.moveToFirst()) {
                        do {
                            String fras = d.getString(0);
                            String aut = d.getString(1);
                            String mood=d.getString(2);


                            txtResultado.append(fras+"\n"+"-"+aut);
                            txtMood.append(""+mood);
                        }
                        while (d.moveToNext());
                    }
                    break;
                case R.id.Bfeliz:

                    Cursor e = db.query("frases", campos, "score=3", null, null, null, "RANDOM() LIMIT 1");

                    txtResultado.setText("");
                    txtMood.setText("");
                    if (e.moveToFirst()) {
                        do {
                            String fras = e.getString(0);
                            String aut = e.getString(1);
                            String mood=e.getString(2);


                            txtResultado.append(fras+"\n"+"-"+aut);
                            txtMood.append(""+mood);
                        }
                        while (e.moveToNext());
                    }
                    break;
                case R.id.Btriste:

                   Cursor f = db.query("frases", campos, "score=4", null, null, null, "RANDOM() LIMIT 1",null);

                    txtResultado.setText("");
                    txtMood.setText("");
                    if (f.moveToFirst()) {
                        do {
                            String fras = f.getString(0);
                            String aut = f.getString(1);
                            String mood=f.getString(2);


                            txtResultado.append(fras+"\n"+"-"+aut);
                            txtMood.append(""+mood);
                        }
                        while (f.moveToNext());
                    }
                    break;
            }
        }

        catch(Exception e){
            txtResultado.setText("error");
        };

    };
}
