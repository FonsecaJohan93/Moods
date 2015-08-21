package com.example.johanfonseca.frases2;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.SQLException;

/**
 * Created by Johan Fonseca on 13/08/2015.
 */
public class BaseDeDatos extends SQLiteOpenHelper {

    private static String DB_PATH = "/data/data/com.example.johanfonseca.frases2/databases/";
    private static String DB_NAME = "phrases";
    private SQLiteDatabase myDataBase;
    private final Context myContext;

    public BaseDeDatos(Context contexto, String nombre, SQLiteDatabase.CursorFactory factory,
                       int version) {

        super(contexto, nombre, factory, version);
        this.myContext = contexto;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        // No hacemos nada aqui
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // Cuando haya cambios en la estructura deberemos

    }

    public void createDataBase() throws IOException {

        boolean dbExist = checkDataBase();

        if (dbExist) {
            // Si existe, no haemos nada!
        } else {
            // Llamando a este mtodo se crea la base de datos vacia en la ruta
            // por defecto del sistema de nuestra aplicacion por lo que
            // podremos sobreescribirla con nuestra base de datos.
            this.getReadableDatabase();

            try {

                copyDataBase();

            } catch (IOException e) {

                throw new Error("Error copiando database");
            }
        }
    }

    private boolean checkDataBase() {

        SQLiteDatabase checkDB = null;

        try {
            String myPath = DB_PATH + DB_NAME;
            checkDB = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);

        } catch (SQLiteException e) {
            // Base de datos no creada todavia
        }

        if (checkDB != null) {

            checkDB.close();
        }

        return checkDB != null ? true : false;

    }

    public void openDataBase() throws SQLException {

        // Open the database
        String myPath = DB_PATH + DB_NAME;
        myDataBase = SQLiteDatabase.openDatabase(myPath, null, SQLiteDatabase.OPEN_READONLY);
    }

    @Override
    public synchronized void close() {

        if (myDataBase != null)
            myDataBase.close();

        super.close();
    }

    private void copyDataBase() throws IOException {

        OutputStream databaseOutputStream = new FileOutputStream("" + DB_PATH + DB_NAME);
        InputStream databaseInputStream;

        byte[] buffer = new byte[1024];
        int length;

        databaseInputStream = myContext.getAssets().open("phrases");
        while ((length = databaseInputStream.read(buffer)) > 0) {
            databaseOutputStream.write(buffer);
        }

        databaseInputStream.close();
        databaseOutputStream.flush();
        databaseOutputStream.close();
    }

}