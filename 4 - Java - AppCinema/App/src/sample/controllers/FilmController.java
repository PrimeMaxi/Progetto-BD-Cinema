package sample.controllers;

import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalTime;
import java.time.Year;
import java.util.ResourceBundle;
import java.util.regex.Pattern;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import javafx.stage.Stage;
import javax.swing.JRootPane;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;
import sample.service.DateValidator;

public class FilmController implements Initializable {


  public TextField Titolo;
  public TextField Trama;
  public TextField Regia;
  public ChoiceBox GENERE;
  public Button SalvaButton;
  public Button AnnullaButton;
  public TextField AnnoFilm;
  public TextField Hour;
  public TextField minutes;
  public TextField second;
  public StackPane rootPane;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    GENERE.getItems().addAll(sample.models.enumerations.GENERE.values());
    GENERE.setValue(sample.models.enumerations.GENERE.Azione);
  }

  public void saveFilm(ActionEvent actionEvent) {
    String titolo = Titolo.getText();
    String trama = Trama.getText();
    String regia = Regia.getText();
    sample.models.enumerations.GENERE genere = sample.models.enumerations.GENERE.valueOf(GENERE.getValue().toString());
    String annoFilm = AnnoFilm.getText();
    String ore = Hour.getText();
    String minut = minutes.getText();
    String secondi = second.getText();
    String durataFilm = ore+":"+minut+":"+secondi;
    boolean allert;

    if(titolo.isEmpty() || annoFilm.isEmpty()){
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
    if(!DateValidator.isValidFilmLength(durataFilm))
      errorFormatLenght();
    if (!annoFilm.isEmpty()) {
      final var durata = Time.valueOf(LocalTime.of(Integer.parseInt(ore),Integer.parseInt(minut),Integer.parseInt(secondi)));
      Film film = new Film(titolo, trama, regia, Year.parse(annoFilm), durata, genere);
        var dao = new FilmDaoImpl(DatabaseConnection.getConnection());
        dao.insertFilm(film);
    }
  }

  private void errorFormatLenght() {
    Alert alert = new Alert(AlertType.ERROR);
    alert.setHeaderText(null);
    alert.setContentText("Errore campo durata (HH:MM:SS)");
    alert.showAndWait();
  }

  public void cancelFilm(ActionEvent actionEvent) {
    Stage stage = (Stage) rootPane.getScene().getWindow();
    stage.close();
  }
}
