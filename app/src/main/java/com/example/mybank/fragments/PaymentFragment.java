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


public class PaymentFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_payment, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        SQLiteDatabase db = LoginActivity.db;
        String[] qValues = new String[]{LoginActivity.loggedUser};
        Cursor c = db.rawQuery("SELECT * FROM Transacciones WHERE email_origen = ?", qValues);

        String table = "";
        if (c != null) {
            int i = 0;
            while (c.moveToPosition(i)) {
                String origen = c.getString(0);
                String destino = c.getString(1);
                String banco = c.getString(2);
                String cantidad = c.getString(3);
                String motivo = c.getString(4);

                table += "|" + origen + "|" + destino + "|" + banco + "|" + cantidad + "|" + motivo + "\n";
                i++;
            }
            TextView t = view.findViewById(R.id.payment_table);
            t.setText(table);
        }
    }
}