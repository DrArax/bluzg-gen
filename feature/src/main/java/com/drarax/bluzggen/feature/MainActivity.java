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

    private Generator gen;

    MainActivity() throws FileNotFoundException {
        gen = new Generator(getApplicationContext());
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button first_cat = (Button) findViewById(R.id.first_cat_bttn);
        first_cat.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View view) {
                String bluzga = gen.Generuj();

                Intent genAll = new Intent(getApplicationContext(), AllGenerateActivity.class);
                genAll.putExtra("finalBluzga", bluzga);
                startActivity(genAll);
            }
        });
    }
}
