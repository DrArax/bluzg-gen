package com.drarax.bluzggen.feature;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class AllGenerateActivity extends AppCompatActivity {

    private Inne other;
    private String fBluzga;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_generate);
        GenerujBluzge();

        other = new Inne();
        context = getApplicationContext();

        Button gen_again = (Button) findViewById(R.id.gen_again_bttn);
        gen_again.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                GenerujBluzge();
            }
        });

        Button copy = (Button) findViewById(R.id.copy_bttn);
        copy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                other.KopiujDoSchowka(view, context, fBluzga);
            }
        });
    }

    public void GenerujBluzge() {
        Generator gen = new Generator();
        fBluzga = gen.Generuj();
        TextView bluzga = (TextView) findViewById(R.id.bluzg);
        bluzga.setText(fBluzga);
    }
}
