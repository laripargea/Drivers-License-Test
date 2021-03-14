package edu.ubb.model;

import edu.ubb.repository.DateiLesen;
import java.io.FileNotFoundException;
import java.util.Calendar;
import java.util.List;
import java.util.Random;

public class Fragebogen{
    private int ID;
    private int FragebogenNummer;
    private int AnzahlFalschenAntworten;
    private int AnzahlRichtigenAntworten;
    private List<Frage> FragenListe;

    //Konstruktor
    public Fragebogen(int ID, List<Frage> fragenListe) throws FileNotFoundException {
        this.ID = ID;
        Calendar kalendar = Calendar.getInstance();
        this.FragebogenNummer = Integer.parseInt(Integer.toString(kalendar.get(Calendar.YEAR)) + ID); //Jahr+ID
        this.AnzahlFalschenAntworten = 0;
        this.AnzahlRichtigenAntworten = 0;
        this.FragenListe = fragenListe;
        randomFragen();
    }

    //Getters
    public int getFragebogenNummer() {
        return FragebogenNummer;
    }

    public int getAnzahlFalschenAntworten() {
        return AnzahlFalschenAntworten;
    }

    public int getAnzahlRichtigenAntworten() {
        return AnzahlRichtigenAntworten;
    }

    public List<Frage> getFragenListe() {
        return FragenListe;
    }

    /**
     * Diese Methode generiert eine Liste mit 26 random Fragen von der Datei.
     * @throws FileNotFoundException wenn die Datei nicht existiert
     */
    public void randomFragen() throws FileNotFoundException {
        Random random = new Random();
        for (int i = 1; i <= 26; i++) //wir brauchen 26 Fragen
        {
            //speichert den Index einer random Frage von den 40 Fragen
            int randomWert = random.nextInt(DateiLesen.durchgehenDatei().size());

            //fuegt die Frage zu der Liste fuer den Test hinzu
            getFragenListe().add(DateiLesen.durchgehenDatei().get(randomWert));
        }
    }
}