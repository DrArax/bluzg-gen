package com.drarax.bluzggen.feature;

import android.content.Context;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

import java.io.FileNotFoundException;

public class MainActivity extends AppCompatActivity {

    public Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        this.context = getApplicationContext();
        try {
            Globals.mData = new Data("rzeczownik.txt", "przymiotnik.txt", context);
            Globals.mData.Wczytaj();
        }
        catch(FileNotFoundException e) {
            e.printStackTrace();
        }

        Button first_cat = (Button) findViewById(R.id.first_cat_bttn);
        first_cat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                Generator gen = new Generator();
                String bluzga = gen.Generuj();

                Intent genAll = new Intent(context, AllGenerateActivity.class);
                startActivity(genAll);
            }
        });
    }
}
