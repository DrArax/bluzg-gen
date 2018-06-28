package com.drarax.bluzggen.feature;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class AllGenerateActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_generate);
        GenerujBluzge();
    }

    private static final String finalBluzga = "Bluzga";

    public void GenerujBluzge() {
        String bluzg = getIntent().getStringExtra(finalBluzga);
        TextView bluzga = (TextView) findViewById(R.id.bluzg);
        bluzga.setText(finalBluzga);
    }
}
