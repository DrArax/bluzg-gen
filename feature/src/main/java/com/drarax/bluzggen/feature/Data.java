package com.drarax.bluzggen.feature;

import android.content.Context;
import android.content.res.AssetManager;
import android.util.Log;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.LinkedList;


public class Data {

    private LinkedList<LinkedList<String>> rzeczownik_zwierzeta;
    private LinkedList<LinkedList<String>> rzeczownik_reszta;
    private LinkedList<LinkedList<String>> rzeczownik;
    private LinkedList<String> przymiotnik;

    private String rzeczownikFile;
    private String przymiotnikFile;

    private Context context;

    public static final String tag = "Data";

    Data(String rFile, String pFile, Context context) {
        rzeczownik_zwierzeta = new LinkedList<>();
        rzeczownik_reszta = new LinkedList<>();
        rzeczownik = new LinkedList<>();
        przymiotnik = new LinkedList<>();

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
                rzeczBuffer = null;
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
                przymBuffer = null;
            } catch (IOException e) {
                e.printStackTrace();
                Log.e(tag, "Przymiotnik file error.");
            }

            Podziel(rzeczWhole, przymWhole);
            rzeczWhole = null; przymWhole = null;
        }
        else {
            Log.e(tag, "Null context");
        }
    }

    private void Podziel(String rzecz, String przym) {
        int rzeczCurrent = 0;

        String[] rzeczKategorie = rzecz.split("CAT");

        // Rzeczownik Kategoria: Zwierzeta
        String[] rzeczZwierzetaTempBuffer = rzeczKategorie[0].split("/");
        for (int rz = 0; rz < rzeczZwierzetaTempBuffer.length / 2; rz++) {
            rzeczownik_zwierzeta.add(new LinkedList<String>());
            rzeczownik_zwierzeta.get(rz).add(rzeczZwierzetaTempBuffer[rzeczCurrent]);
            rzeczownik_zwierzeta.get(rz).add(rzeczZwierzetaTempBuffer[rzeczCurrent+1]);
            rzeczCurrent += 2;
        }
        rzeczCurrent = 0;

        // Rzeczownik Kategoria: Reszta
        String[] rzeczResztaTempBuffer = rzeczKategorie[rzeczKategorie.length-1].split("/");
        for (int rz = 0; rz < rzeczResztaTempBuffer.length / 2; rz++) {
            rzeczownik_reszta.add(new LinkedList<String>());
            rzeczownik_reszta.get(rz).add(rzeczResztaTempBuffer[rzeczCurrent]);
            rzeczownik_reszta.get(rz).add(rzeczResztaTempBuffer[rzeczCurrent+1]);
            rzeczCurrent += 2;
        }

        // Rzeczownik Wszystkie
        rzeczCurrent = 0;

        for (int rz = 0; rz < rzeczownik_zwierzeta.size(); rz++) {
            rzeczownik.add(new LinkedList<String>());
            rzeczownik.get(rzeczCurrent).add(rzeczownik_zwierzeta.get(rz).get(0));
            rzeczownik.get(rzeczCurrent).add(rzeczownik_zwierzeta.get(rz).get(1));
            rzeczCurrent++;
        }

        for (int rz = 0; rz < rzeczownik_reszta.size(); rz++) {
            rzeczownik.add(new LinkedList<String>());
            rzeczownik.get(rzeczCurrent).add(rzeczownik_reszta.get(rz).get(0));
            rzeczownik.get(rzeczCurrent).add(rzeczownik_reszta.get(rz).get(1));
            rzeczCurrent++;
        }

        // Przymiotnik
        String[] przymTempBuffer = przym.split("/");
        for (int prz = 0; prz < przymTempBuffer.length; prz++) {
            przymiotnik.add(przymTempBuffer[prz]);
        }
        przymTempBuffer = null;
        rzeczKategorie = null;
        rzeczZwierzetaTempBuffer = null;
        rzeczResztaTempBuffer = null;
    }

    public LinkedList<LinkedList<String>> getRzeczownik_zwierzeta() {
        return rzeczownik_zwierzeta;
    }

    public LinkedList<LinkedList<String>> getRzeczownik_reszta() {
        return rzeczownik_reszta;
    }

    public LinkedList<LinkedList<String>> getRzeczownik() {
        return rzeczownik;
    }

    public LinkedList<String> getPrzymiotnik() {
        return przymiotnik;
    }
}
