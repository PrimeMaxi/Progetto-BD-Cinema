package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;

public class FilmController implements Initializable {


  public TextField Titolo;
  public TextField Trama;
  public TextField Regia;
  public TextField DurataFilm;
  public ChoiceBox GENERE;
  public Button SalvaButton;
  public Button AnnullaButton;
  public TextField AnnoFilm;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

    GENERE.getItems().addAll("Marianna","Horror");
  }

  public void saveBook(ActionEvent actionEvent) {
    String titolo = Titolo.getText();
    String trama = Trama.getText();
    String regia = Regia.getText();
    String genere = GENERE.getSelectionModel().getSelectedItem().toString();
    String durataFilm = DurataFilm.toString();
    String annoFilm = AnnoFilm.getText();


  }

  public void cancel(ActionEvent actionEvent) {
  }
}
