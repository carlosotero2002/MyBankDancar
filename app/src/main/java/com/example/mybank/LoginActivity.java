package com.example.mybank;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ContentValues;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class LoginActivity extends AppCompatActivity {

    TextView password;
    TextView email;
    Button submit;
    public static String loggedUser;
    public static SQLiteDatabase db;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        /* DATABASE CREATION */

        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "db_usuarios",null,1);
        db = usdbh.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put("email","admin@admin.com");
        values.put("password","admin");
        db.insert("Usuarios",null,values);

        values = new ContentValues();
        values.put("email","admin@admin.com");
        values.put("num_cuenta","4456 6845 1235 3740");
        values.put("balance", "2550.35");
        db.insert("Cuentas",null,values);

        values = new ContentValues();
        values.put("email","admin@admin.com");
        values.put("num_cuenta","2422 7745 3255 3760");
        values.put("balance", "0.35");
        db.insert("Cuentas",null,values);

        values = new ContentValues();
        values.put("email","admin@admin.com");
        values.put("num_cuenta","4322 6645 1234 3112");
        values.put("balance", "25.00");
        db.insert("Cuentas",null,values);

        // Transacciones de prueba.
        values = new ContentValues();
        values.put("email_origen","admin@admin.com");
        values.put("email_destino","tacos@live.com");
        values.put("banco","SCOTIABANK");
        values.put("cantidad","50");
        values.put("motivo","Compra");
        db.insert("Transacciones",null,values);

        values = new ContentValues();
        values.put("email_origen","admin@admin.com");
        values.put("email_destino","Pampas@live.com");
        values.put("banco","BBVA");
        values.put("cantidad","385");
        values.put("motivo","Compra");
        db.insert("Transacciones",null,values);

        values = new ContentValues();
        values.put("email_origen","admin@admin.com");
        values.put("email_destino","Cisco@live.com");
        values.put("banco","SCOTIABANK");
        values.put("cantidad","665");
        values.put("motivo","Venta");
        db.insert("Transacciones",null,values);

        values = new ContentValues();
        values.put("email_origen","MySQL@live.com");
        values.put("email_destino","admin@admin.com");
        values.put("banco","BBVA");
        values.put("cantidad","420");
        values.put("motivo","HIPOTECA");
        db.insert("Transacciones",null,values);

        values = new ContentValues();
        values.put("email_origen","admin@admin.com");
        values.put("email_destino","Extraordinario@live.com");
        values.put("banco","BANCOMER");
        values.put("cantidad","1");
        values.put("motivo","Venta");
        db.insert("Transacciones",null,values);

        values = new ContentValues();
        values.put("email_origen","admin@admin.com");
        values.put("email_destino","Party@live.com");
        values.put("banco","SANTANDER");
        values.put("cantidad","500000");
        values.put("motivo","Compra");
        db.insert("Transacciones",null,values);


        /* LISTENERS */

        submit = findViewById(R.id.submit);
        email = findViewById(R.id.email);
        password = findViewById(R.id.pass);

        Context c_main = this;



        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view)
            {
                String emailText = email.getText().toString();
                String passwordText = password.getText().toString();
                Log.d("email",emailText);
                Log.d("pass",passwordText);

                Cursor c = db.rawQuery("SELECT * FROM Usuarios Where email=?", new String[] {emailText});

                if (c != null)
                {
                    c.moveToFirst();
                    Log.d("Cursor", String.valueOf(c.getPosition()));

                    try
                    {
                        String realPass = c.getString(1);
                        String realUser = c.getString(0);

                        if (realPass.equals(passwordText) && realUser.equals(emailText))
                        {
                            Intent i = new Intent(c_main , MainActivity.class);
                            loggedUser = realUser;
                            startActivity(i);
                            Log.d("Intent", i.toString());
                        }
                    }
                    catch (Exception e)
                    {
                        Log.d("Exception",e.toString());
                        c.close();
                    }


                }

            }
        });

    }
}