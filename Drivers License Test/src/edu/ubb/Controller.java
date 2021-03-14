package edu.ubb;

import edu.ubb.model.Frage;
import edu.ubb.model.Fragebogen;
import edu.ubb.repository.DateiLesen;
import javafx.animation.PauseTransition;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.util.Duration;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.net.URL;
import java.util.*;

public class Controller implements Initializable {
    @FXML
    private RadioButton rbAntwort1;
    @FXML
    private RadioButton rbAntwort2;
    @FXML
    private RadioButton rbAntwort3;
    @FXML
    private Button btnNext;
    @FXML
    private Text idText;
    @FXML
    private Label idRichtige;
    @FXML
    private Label idFalsche;
    @FXML
    private Button idCloseBtn;
    @FXML
    private Button idResult;
    @FXML
    private Label idRichtigeAmEnde;
    @FXML
    private Label idFalscheAmEnde;
    private List<Frage> fragen;
    private int id;
    private Fragebogen fragebogen;
    private int index;
    private Stage stage;
    private Stage stage2;

    //Konstruktor
    public Controller() throws FileNotFoundException {
        rbAntwort1 = new RadioButton();
        rbAntwort2 = new RadioButton();
        rbAntwort3 = new RadioButton();
        btnNext = new Button();
        idText=new Text();
        idRichtige=new Label();
        idFalsche=new Label();
        idCloseBtn = new Button();
        idResult = new Button();
        idRichtigeAmEnde=new Label();
        idFalscheAmEnde=new Label();
        fragen = new ArrayList<>();
        id = 0;
        fragebogen = new Fragebogen(id,fragen);
        index = 0;
        stage = new Stage();
        stage2 = new Stage();
    }

    /**
     * Diese Methode schliesst die Anwendung.
     */
    public void schliessen(){
        stage = (Stage) idCloseBtn.getScene().getWindow();
        stage.close();
    }

    /**
     * Diese Methode gibt true zurueck, wenn ein Anwort richtig ist, also mit "*" beginnt; anders falsch.
     * @param antwort String
     * @return true fuer richtige Antwort, anders falsch
     */
    private boolean antwortFrage(String antwort){
        return antwort.charAt(0)=='*';
    }

    /**
     * Diese Methode setzt die Anzahl der richtigen und falschen Antworten zu.
     * @throws FileNotFoundException wenn die Datei nicht existiert
     */
    public void zeigeResultat() throws FileNotFoundException {
        idRichtigeAmEnde.setText(DateiLesen.lesenAntworten().get(0));
        idFalscheAmEnde.setText(DateiLesen.lesenAntworten().get(1));
    }

    /**
     * Diese Methode erstellt je eine neue Frage (wenn man auf den Next Button clickt).
     * Nach 5 verfehlten Antworten oder 26 Fragen schliesst sich den Fragebogen und zeigt die Resultate.
     * @throws IOException wenn ein Problem beim loading von fxml erscheint
     */
    public void nextButtonClick() throws IOException {
        //wenn nur die richtigen Antworten gewaehlt sind, wird richtigeAntworten incrementiert
        //anders wird falscheAntworten incrementiert
        if(rbAntwort1.isSelected()==antwortFrage(fragebogen.getFragenListe().get(index).getAntwort1()) &&
                rbAntwort2.isSelected()==antwortFrage(fragebogen.getFragenListe().get(index).getAntwort2()) &&
                rbAntwort3.isSelected()==antwortFrage(fragebogen.getFragenListe().get(index).getAntwort3()))
            idRichtige.setText(Integer.toString(Integer.parseInt(idRichtige.getText()) + 1));
        else
            idFalsche.setText(Integer.toString(Integer.parseInt(idFalsche.getText()) + 1));

        //Radiobuttons deselectieren
        rbAntwort1.setSelected(false);
        rbAntwort2.setSelected(false);
        rbAntwort3.setSelected(false);

        FileWriter fileWriter = new FileWriter("..\\Lari_Maria_L5\\src\\edu\\ubb\\files\\Antworten.txt");

        //wenn der Benutzer 5 Fragen verfehlt, endet den Fragebogen und zeigt die Resultate
        if (Integer.parseInt(idFalsche.getText()) == 5){

            //schliesst Fragebogen
            stage = (Stage) btnNext.getScene().getWindow();
            stage.close();

            erstelleResultat();
        }

        //wenn noch Fragen in der Liste gibt, wird die naechste Frage gezeigt; anders endet der Fragebogen
        if (index < 25) {
            //speichern die Resultate
            fileWriter.write(idRichtige.getText() + " " + idFalsche.getText());

            index++;
            erstelleFrage(index);
        }
        else {
            //schliesst Fragebogen
            stage = (Stage) btnNext.getScene().getWindow();
            stage.close();

            erstelleResultat();
        }
        fileWriter.close();
    }

    /**
     * Diese Methode oeffnet einen neuen Fragebogen.
     * Wenn die Zeit vergeht, schliesst er sich und zeigt die Resultate.
     * @throws IOException wenn ein Problem beim loading von fxml erscheint
     */
    public void erstelleFragebogen() throws IOException {
        id++;
        fragebogen = new Fragebogen(id,fragen);
        Parent root1 = FXMLLoader.load(getClass().getResource("frage.fxml"));
        stage.setTitle("FragebogenNummer: " + fragebogen.getFragebogenNummer());
        stage.setScene(new Scene(root1, 700, 500));
        stage.show();

        //nach 30 Minuten schliesst sich den Fragebogen und zeigt die Resultate
        PauseTransition delay = new PauseTransition(Duration.minutes(30));
        delay.setOnFinished(event -> {stage.close();
            try {
                erstelleResultat();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
        delay.play();
    }

    /**
     * Diese Methode oeffnet das Fenster mit der Resultate.
     * @throws IOException IOException wenn ein Problem beim loading von fxml erscheint
     */
    public void erstelleResultat() throws IOException {
        Parent root2 = FXMLLoader.load(getClass().getResource("resultat.fxml"));
        stage2.setTitle("Resultat");
        stage2.setScene(new Scene(root2, 700, 500));
        stage2.show();

        //die Resultate schliessen sich nach 7 Sekunden
        PauseTransition delay = new PauseTransition(Duration.seconds(7));
        delay.setOnFinished( event -> stage2.close() );
        delay.play();
    }

    /**
     * Diese Methode zeigt eine Frage von dem Fragebogen mit den moeglichen Antworten fuer den Benutzer.
     * @param i int - der Index der Frage von der Fragenliste
     */
    public void erstelleFrage(int  i) {
        //set Text fuer die Frage
        idText.setText(fragebogen.getFragenListe().get(i).getFrage());

        //set Text fuer die Antworten; wenn sie mit "*" beginnen, loscht man das "*"
        if (fragebogen.getFragenListe().get(i).getAntwort1().charAt(0) == '*')
            rbAntwort1.setText(fragebogen.getFragenListe().get(i).getAntwort1().substring(1));
        else
            rbAntwort1.setText(fragebogen.getFragenListe().get(i).getAntwort1());
        if (fragebogen.getFragenListe().get(i).getAntwort2().charAt(0) == '*')
            rbAntwort2.setText(fragebogen.getFragenListe().get(i).getAntwort2().substring(1));
        else
            rbAntwort2.setText(fragebogen.getFragenListe().get(i).getAntwort2());
        if (fragebogen.getFragenListe().get(i).getAntwort3().charAt(0) == '*')
            rbAntwort3.setText(fragebogen.getFragenListe().get(i).getAntwort3().substring(1));
        else
            rbAntwort3.setText(fragebogen.getFragenListe().get(i).getAntwort3());
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        //erstellt die erste Frage
        erstelleFrage(0);

        //initialisiert die richtigen und falschen Antworten (mit 0)
        idRichtige.setText(Integer.toString(fragebogen.getAnzahlRichtigenAntworten()));
        idFalsche.setText(Integer.toString(fragebogen.getAnzahlFalschenAntworten()));

        //wrap text fuer Antworten Buttons
        rbAntwort1.setWrapText(true);
        rbAntwort2.setWrapText(true);
        rbAntwort3.setWrapText(true);
    }
}