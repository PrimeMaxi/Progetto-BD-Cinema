package sample.controllers;

import java.net.URL;
import java.sql.Time;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.DatePicker;
import javafx.scene.layout.GridPane;

public class PrenotazioneController implements Initializable {

  public DatePicker dataPrenotazione;
  public Button buttonCancel;
  public ChoiceBox<String> filmPrenotazione;
  public ChoiceBox<Time> orarioPrenotazione;
  public Button buttonPrenota;
  public GridPane gridSeats;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    
  }
}
