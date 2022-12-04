package com.example.mybank.fragments;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.mybank.LoginActivity;
import com.example.mybank.R;
import com.example.mybank.UsuariosSQLiteHelper;


public class HomeFragment extends Fragment {

    public TextView balance;
    public TextView numeroCuenta;
    public String userLogged = "admin@admin.com";
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);
        balance = view.findViewById(R.id.balance);
        numeroCuenta = view.findViewById(R.id.cuenta);


        SQLiteDatabase db = LoginActivity.db;

        Cursor c = db.rawQuery("SELECT * FROM Cuentas WHERE email = ?", new String[] {userLogged});

        if (c != null)
        {
            c.moveToFirst();
            balance.setText(c.getString(2));
            numeroCuenta.setText(c.getString(1));
        }


    }
}