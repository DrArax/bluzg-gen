package com.drarax.bluzggen.feature;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.LinkedList;
import java.util.Scanner;

public class Data {

    public LinkedList<LinkedList<String>> rzeczownik;
    public LinkedList<LinkedList<String>> przymiotnik;

    private String rzeczownikFile;
    private String przymiotnikFile;

    Data(String rFile, String pFile) {
        rzeczownik = new LinkedList<LinkedList<String>>();
        przymiotnik = new LinkedList<LinkedList<String>>();

        rzeczownikFile = rFile;
        przymiotnikFile = pFile;
    }

    public boolean Wczytaj() throws FileNotFoundException {
        int rz = 0;
        int pr = 0;

        try(Scanner readRzeczownik = new Scanner(new File(rzeczownikFile))) {

            while (readRzeczownik.hasNextLine()) {
                String[] tempRzecz = readRzeczownik.nextLine().split("/");
                rzeczownik.add(new LinkedList<String>());
                rzeczownik.get(rz).add(tempRzecz[0]);
                rzeczownik.get(rz).add(tempRzecz[1]);
                rz++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        try(Scanner readPrzymiotnik = new Scanner(new File(przymiotnikFile))) {

            while (readPrzymiotnik.hasNextLine()) {
                String[] tempPrzym = readPrzymiotnik.nextLine().split("/");
                przymiotnik.add(new LinkedList<String>());
                for (int i = 0; i < 3; i++) {
                    przymiotnik.get(pr).add(tempPrzym[i]);
                }
                pr++;
            }
        } catch(IOException e) {
            e.printStackTrace();
        }

        //readRzeczownik.close();
        //readPrzymiotnik.close();
        return true;
    }
}
