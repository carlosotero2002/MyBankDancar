package com.example.mybank;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class UsuariosSQLiteHelper extends SQLiteOpenHelper
{
    public UsuariosSQLiteHelper(Context context, String nombre, SQLiteDatabase.CursorFactory factory, int version)
    {
        super(context,nombre,factory,version);
    }
    @Override
    public void onCreate(SQLiteDatabase db)
    {
        db.execSQL("CREATE TABLE Usuarios (email TEXT, password TEXT);");
        db.execSQL("CREATE TABLE Cuentas (email TEXT, num_cuenta TEXT, balance TEXT);");

        // Motivo puede ser hipoteca o payroll
        db.execSQL("CREATE TABLE Transacciones (email_origen TEXT, email_destino TEXT, banco TEXT ,cantidad TEXT, motivo TEXT)");


    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int versionAnterior, int versionNueva)
    {
        db.execSQL("DROP TABLE IF EXISTS Usuarios");
        db.execSQL("CREATE TABLE Usuarios (email TEXT, password TEXT)");

        db.execSQL("DROP TABLE IF EXISTS Cuentas");
        db.execSQL("CREATE TABLE Cuentas (email TEXT, num_cuenta TEXT, balance TEXT);");
    }
}
