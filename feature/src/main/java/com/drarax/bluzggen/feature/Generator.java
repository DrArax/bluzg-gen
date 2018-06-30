package com.drarax.bluzggen.feature;

import android.content.Context;

import java.io.FileNotFoundException;
import java.util.Random;
import java.util.Scanner;

public class Generator {

    private Scanner scan;
    private Random rand;
    private Data data;

    Generator(Context context) throws FileNotFoundException {
        scan = new Scanner(System.in);
        rand = new Random();
        data = new Data("rzeczownik.txt", "przymiotnik.txt", context);
        data.Wczytaj(0);
    }

    public String Generuj() {
        int randRzecz = rand.nextInt(data.rzeczownik.size());

        String rzecz = data.rzeczownik.get(randRzecz-1).get(0);

        int randPrzym = rand.nextInt(data.przymiotnik.size());
        String przym = "";

        switch(data.rzeczownik.get(randRzecz-1).get(1)) {
            case "M":
                przym = data.przymiotnik.get(randPrzym).get(0);
                break;

            case "Z":
                przym = data.przymiotnik.get(randPrzym).get(1);
                break;

            case "N":
                przym = data.przymiotnik.get(randPrzym).get(2);
                break;
        }

        String bluzga = String.join(" ", przym, rzecz);

        return bluzga;
    }

}
