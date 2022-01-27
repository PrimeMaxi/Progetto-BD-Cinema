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
import sample.models.enumerations.GENERE;
import sample.service.DateValidator;

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
    GENERE.getItems().addAll(sample.models.enumerations.GENERE.values());
  }

  public void saveFilm(ActionEvent actionEvent) {
    String titolo = Titolo.getText();
    String trama = Trama.getText();
    String regia = Regia.getText();
    String genere = GENERE.getValue().toString();
    System.out.println(genere);
    String durataFilm = DurataFilm.toString();
    String annoFilm = AnnoFilm.getText();

    if(titolo.isEmpty() || annoFilm.isEmpty() || genere.isEmpty()){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setContentText("Alcuni campi obbligatori sono vuoti");
      alert.showAndWait();
    }
    if(!DateValidator.isValidDate(annoFilm)){
      Alert alert = new Alert(AlertType.ERROR);
      alert.setHeaderText(null);
      alert.setContentText("Errore campo anno (YYYY)");
      alert.showAndWait();
    }

  }

  public void cancelFilm(ActionEvent actionEvent) {
  }
}
