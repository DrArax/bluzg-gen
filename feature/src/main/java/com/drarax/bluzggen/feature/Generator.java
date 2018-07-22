package com.drarax.bluzggen.feature;

import android.util.Log;

import java.util.LinkedList;
import java.util.Random;

public class Generator {

    public String Generuj(String cat) {
        Random rand = new Random();
        LinkedList<LinkedList<String>> catPointer = new LinkedList<>();
        String bluzga = "";

        if (cat.equals("wszystkie"))
            catPointer = Globals.mData.getRzeczownik();

        if (cat.equals("zwierzeta"))
            catPointer = Globals.mData.getRzeczownik_zwierzeta();

        if (catPointer != null) {
            int randRzecz = rand.nextInt(catPointer.size());

            String rzecz = "";
            if (catPointer.get(randRzecz).get(0) != null) {
                rzecz = catPointer.get(randRzecz).get(0);
            }

            int randPrzym = rand.nextInt(Globals.mData.getPrzymiotnik().size());

            String przym = "";
            if (Globals.mData.getPrzymiotnik().get(randPrzym) != null) {
                przym = Globals.mData.getPrzymiotnik().get(randPrzym);
            }

            if (catPointer.get(randRzecz).get(1) != null) {
                przym = Odmien(przym, catPointer.get(randRzecz).get(1));
            }

            bluzga = new String(przym + " " + rzecz);
        }

        return bluzga;
    }

    private String Odmien(String przym, String rodzaj) {
        char[] przymChar = przym.toCharArray();
        char lastLetter = 0;

        switch(rodzaj) {
            case "M":
                if (przym.contains("i") && przym.indexOf("i") == przym.length() - 1) {
                    lastLetter = 'i';
                }
                else {
                    lastLetter = 'y';
                }
                break;

            case "Z":
                lastLetter = 'a';
                break;

            case "N":
                if (przym.contains("i") && przym.indexOf("i") == przym.length() - 1) {
                    char[] tempCharArray = new char[przymChar.length + 1];
                    for (int i = 0; i < przymChar.length; i++) {
                        tempCharArray[i] = przymChar[i];
                    }
                    przymChar = null;
                    przymChar = tempCharArray;
                }
                lastLetter = 'e';
                break;
        }

        if (new String(przymChar).contains(" ") && przymChar[new String(przymChar).indexOf(" ") - 1] == 'y')  {
            przymChar[new String(przymChar).indexOf(" ") - 1] = lastLetter;
        }
        else {
            przymChar[przymChar.length - 1] = lastLetter;
        }

        return new String(przymChar);
    }
}
