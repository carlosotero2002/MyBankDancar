package com.example.mybank;

import androidx.appcompat.app.AppCompatActivity;

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



    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login2);

        /* DATABASE CREATION */

        UsuariosSQLiteHelper usdbh = new UsuariosSQLiteHelper(this, "db_usuarios",null,1);
        SQLiteDatabase db = usdbh.getWritableDatabase();
        if (db == null)
        {
            Log.d("Database","Not created");
        }
        else
        {
            db.rawQuery("INSERT INTO Usuarios(email,password) VALUES ('?','?') ",new String[] {"x","x"}).close();
        }
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

                Cursor c = db.rawQuery("SELECT email, password FROM Usuarios Where usuario='?'", new String[] {emailText});
                if (c.moveToFirst())
                {
                    String realPass = c.getString(1);
                    String realUser = c.getString(0);

                    if (realPass.equals(passwordText) && realUser.equals(emailText))
                    {
                        Intent i = new Intent(c_main , MainActivity.class);
                        startActivity(i);
                        Log.d("Intent", i.toString());
                    }
                }

            }
        });

    }
}