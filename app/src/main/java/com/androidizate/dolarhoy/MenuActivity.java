package com.androidizate.dolarhoy;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by CECI on 16/7/2017.
 */

public class MenuActivity extends AppCompatActivity {

    private ListView mDrawerList;
    private ArrayAdapter<String> mAdapter;
    private Integer mMenuPosition = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        mDrawerList = (ListView) findViewById(R.id.navList);

        addDrawerItems();

        ((Button) findViewById(R.id.btn_convertir)).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditText evImporteInicial = (EditText) findViewById(R.id.ev_importe_inicial);
                EditText evImporteFinal = (EditText) findViewById(R.id.ev_importe_final);
                Double loImporteInicial = Double.parseDouble(evImporteInicial.getText().toString());
                Double loImporteFinal = Double.MAX_VALUE;
                switch (mMenuPosition) {
                    case 0:
                        loImporteFinal = (loImporteInicial / 17.15);
                        break;
                    case 1:
                        loImporteFinal = (loImporteInicial * 17.15);
                        break;
                    case 2:
                        loImporteFinal = (loImporteInicial / 20.65);
                        break;
                    case 3:
                        loImporteFinal = (loImporteInicial * 20.65);
                        break;
                }
                evImporteFinal.setText(String.format("%.2f", loImporteFinal));
            }
        });
    }

    private void addDrawerItems() {
        String[] osArray = {"Pesos a Dólares", "Dólares a Pesos", "Pesos a Euros", "Euros a Pesos"};
        mAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, osArray);
        mDrawerList.setAdapter(mAdapter);

        mDrawerList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                TextView tvImporteInicial = (TextView) findViewById(R.id.tv_importe_inicial);
                TextView tvImporteFinal = (TextView) findViewById(R.id.tv_importe_final);
                EditText evImporteInicial = (EditText) findViewById(R.id.ev_importe_inicial);
                EditText evImporteFinal = (EditText) findViewById(R.id.ev_importe_final);

                evImporteInicial.setText("");
                evImporteFinal.setText("");
                mMenuPosition = position;
                switch (position) {
                    case 0:
                        tvImporteInicial.setText("Pesos");
                        tvImporteFinal.setText("Dólares");
                        break;
                    case 1:
                        tvImporteInicial.setText("Dólares");
                        tvImporteFinal.setText("Pesos");
                        break;
                    case 2:
                        tvImporteInicial.setText("Pesos");
                        tvImporteFinal.setText("Euros");
                        break;
                    case 3:
                        tvImporteInicial.setText("Euros");
                        tvImporteFinal.setText("Pesos");
                        break;
                }

                //Toast.makeText(MenuActivity.this, "Ingrese el importe", Toast.LENGTH_SHORT).show();
                //Toast.makeText(MenuActivity.this, String.valueOf(position), Toast.LENGTH_SHORT).show();
            }
        });
    }
}
