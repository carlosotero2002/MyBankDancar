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
import com.example.mybank.MainActivity;
import com.example.mybank.R;

public class TransferFragment extends Fragment {


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_transfer, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState)
    {
        super.onViewCreated(view, savedInstanceState);

        SQLiteDatabase db = LoginActivity.db;
        Cursor c = db.rawQuery("SELECT * FROM Transacciones Where email_origen=? OR email_destino=?", new String[] {LoginActivity.loggedUser,LoginActivity.loggedUser});

        String table1 = "";
        String table2 = "";
        String table3 = "";
        String table4 = "";
        String table5 = "";
        if (c != null)
        {
            int i = 0;
            while(c.moveToPosition(i))
            {
                String origen = c.getString(0) ;
                String destino = c.getString(1);
                String banco = c.getString(2) ;
                String cantidad = c.getString(3);
                String motivo = c.getString(4) + "\n\n\n";

                table1 +=  origen + destino + banco + cantidad + motivo ;
                table2 +=  destino;
                table3 +=  banco;
                table4 +=  cantidad;
                table5 +=  motivo;
                i++;
            }
            TextView t1 = view.findViewById(R.id.transaccion_table);
            TextView t2 = view.findViewById(R.id.transaccion_table2);
            TextView t3 = view.findViewById(R.id.transaccion_table3);
            TextView t4 = view.findViewById(R.id.transaccion_table4);
            TextView t5 = view.findViewById(R.id.transaccion_table5);
            t1.setText(table1);
            //t2.setText(table2);
            //t3.setText(table3);
            //t4.setText(table4);
            //t5.setText(table5);

        }


    }

}