package sample.controllers;

import java.net.URL;
import java.time.Year;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseConnection;
import sample.helpers.StageHelper;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;

public class FilmInsertController implements Initializable {

  public TextField titolo;
  public TextArea trama;
  public TextField regia;
  public TextField anno;
  public TextField hour;
  public TextField minutes;
  public TextField seconds;
  public ChoiceBox<GENERE> genere;
  public AnchorPane paneFilm;
  private FilmDAO filmDAO;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    genere.setItems(FXCollections.observableList(Arrays.stream(GENERE.values()).toList()));

  }

  public void conferma(ActionEvent actionEvent) {
    filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
    final var film = new Film(
        titolo.getText(),
        trama.getText(),
        regia.getText(),
        Year.of(Integer.parseInt(anno.getText())),
        StageHelper.getTime(hour.getText(),minutes.getText(),seconds.getText()),
        genere.getValue()
    );
    filmDAO.querySave(film);
    StageHelper.close(paneFilm);
  }

  public void annulla(ActionEvent actionEvent) {
    StageHelper.close(paneFilm);
  }

}
