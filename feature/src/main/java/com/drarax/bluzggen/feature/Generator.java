package com.drarax.bluzggen.feature;

import java.util.Random;

public class Generator {

    public String Generuj() {
        Random rand = new Random();

        int randRzecz = rand.nextInt(Globals.mData.rzeczownik.size());

        String rzecz = Globals.mData.rzeczownik.get(randRzecz-1).get(0);

        int randPrzym = rand.nextInt(Globals.mData.przymiotnik.size());
        String przym = "";

        switch(Globals.mData.rzeczownik.get(randRzecz-1).get(1)) {
            case "M":
                przym = Globals.mData.przymiotnik.get(randPrzym).get(0);
                break;

            case "Z":
                przym = Globals.mData.przymiotnik.get(randPrzym).get(1);
                break;

            case "N":
                przym = Globals.mData.przymiotnik.get(randPrzym).get(2);
                break;
        }

        //String bluzga = String.join(" ", przym, rzecz);       // min API26
        String bluzga = new String(przym + " " + rzecz);

        return bluzga;
    }

}
