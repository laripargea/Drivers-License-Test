package edu.ubb.repository;

import edu.ubb.model.Frage;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class DateiLesen {

    //Konstruktor
    public DateiLesen() {
    }

    /**
     * Diese Methode liest die Fragen von der Datei und fuegt sie in einer Liste hinzu.
     * @return eine Liste mit den Fragen
     * @throws FileNotFoundException wenn die Datei nicht existiert
     */
    public static List<Frage> durchgehenDatei() throws FileNotFoundException {
        File myObj = new File("..\\Lari_Maria_L5\\src\\edu\\ubb\\files\\Fragen.txt");
        Scanner myReader = new Scanner(myObj);
        List<Frage> fragen = new ArrayList<>();

        //liest die Datei Zeile fuer Zeile
        while (myReader.hasNextLine()) {
            String data = myReader.nextLine();

            //auf 0-Position ist die Frage und auf den naechsten 3 sind die Antworten
            String frage  = data.split(";")[0];
            String antwort1 = data.split(";")[1];
            String antwort2 = data.split(";")[2];
            String antwort3 = data.split(";")[3];

            //wir erstellen ein Objekt Frage und fuegen in der Liste hinzu
            Frage objektFrage= new Frage(frage,antwort1,antwort2,antwort3);
            fragen.add(objektFrage);

        }

        myReader.close();
        return fragen;
    }

    /**
     * Diese Methode liest die Anworten von der Datei.
     * @return eine Liste mit 2 Elemente: Anzahl richtigen Antworten und Anzahl falschen Antworten
     * @throws FileNotFoundException wenn die Datei nicht existiert
     */
    public static List<String> lesenAntworten() throws FileNotFoundException {
        File myObj = new File("..\\Lari_Maria_L5\\src\\edu\\ubb\\files\\Antworten.txt");
        Scanner myReader = new Scanner(myObj);
        List<String> antworten = new ArrayList<>();
        String data = myReader.nextLine();

        //auf 0-Position ist die Anzahl der richtigen Antworten und auf 1 der falschen
        String antwortRichtig = data.split(" ")[0];
        String antwortFalsch = data.split(" ")[1];

        //wir fuegen sie in der Liste hinzu
        antworten.add(antwortRichtig);
        antworten.add(antwortFalsch);

        myReader.close();
        return antworten;
    }
}