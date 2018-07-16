package com.drarax.bluzggen.feature;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;


public class Data {

    public LinkedList<LinkedList<String>> rzeczownik;
    public LinkedList<LinkedList<String>> przymiotnik;

    private String rzeczownikFile;
    private String przymiotnikFile;

    private Context context;

    public static final String tag = "Data";

    Data(String rFile, String pFile, Context context) {
        rzeczownik = new LinkedList<LinkedList<String>>();
        przymiotnik = new LinkedList<LinkedList<String>>();

        rzeczownikFile = rFile;
        przymiotnikFile = pFile;

        this.context = context;
    }

    public void Wczytaj() throws FileNotFoundException {
        String przymWhole = "";
        String rzeczWhole = "";

        if (context != null) {
            AssetManager assets = context.getAssets();

            try {
                InputStream is1 = assets.open(rzeczownikFile);
                byte[] rzeczBuffer = new byte[is1.available()];
                is1.read(rzeczBuffer);
                is1.close();
                rzeczWhole = new String(rzeczBuffer);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(tag, "Reczownik file error.");
            }

            try {
                InputStream is2 = assets.open(przymiotnikFile);
                byte[] przymBuffer = new byte[is2.available()];
                is2.read(przymBuffer);
                is2.close();
                przymWhole = new String(przymBuffer);
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(tag, "Przymiotnik file error.");
            }

            Podziel(rzeczWhole, przymWhole);
        }
        else {
            Log.e(tag, "Null context");
        }
    }

    private void Podziel(String rzecz, String przym) {
        int rzeczCurrent = 0;
        int przymCurrent = 0;

        String[] rzeczTempBuffer = rzecz.split("/");
        for (int rz = 0; rz < rzeczTempBuffer.length / 2; rz++) {
            rzeczownik.add(new LinkedList<String>());
            rzeczownik.get(rz).add(rzeczTempBuffer[rzeczCurrent]);
            rzeczownik.get(rz).add(rzeczTempBuffer[rzeczCurrent+1]);
            rzeczCurrent += 2;
        }

        String[] przymTempBuffer = przym.split("/");
        for (int prz = 0; prz < przymTempBuffer.length / 3; prz++) {
            przymiotnik.add(new LinkedList<String>());
            przymiotnik.get(prz).add(przymTempBuffer[przymCurrent]);
            przymiotnik.get(prz).add(przymTempBuffer[przymCurrent+1]);
            przymiotnik.get(prz).add(przymTempBuffer[przymCurrent+2]);
            przymCurrent += 3;
        }
    }
}
