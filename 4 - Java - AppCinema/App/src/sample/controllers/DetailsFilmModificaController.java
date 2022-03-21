package sample.controllers;

import java.net.URL;
import java.util.Arrays;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.fxml.Initializable;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.entity.Film;
import sample.models.entity.Proiezione;
import sample.models.enumerations.GENERE;

public class DetailsFilmModificaController implements Initializable {

  public TextField titolo;
  public TextField trama;
  public TextField regia;
  public TextField anno;
  public ChoiceBox<GENERE> genere;
  public AnchorPane paneFilmModifica;
  private Proiezione proiezione;
  private Film film;
  private FilmDAO filmDAO;

  public void clickConferma(MouseEvent mouseEvent) {
    filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
    filmDAO.updateFilm(
        new Film(
            film.getIdFilm(),
            titolo.getText(),
            trama.getText(),
            regia.getText(),
            Integer.parseInt(anno.getText()),
            film.getDurataFilm(),
            genere.getValue().toString()));
    close();
  }

  public void clickAnnulla(MouseEvent mouseEvent) {
    close();
  }

  public void setProiezione(Proiezione proiezione) {
    this.proiezione = proiezione;
  }

  public void setFilm(Film film) {
    this.film = film;
    titolo.setText(film.getTitolo());
    trama.setText(film.getTrama());
    regia.setText(film.getRegia());
    anno.setText(film.getAnnoUscita().toString());
    genere.setValue(film.getGenere());
  }
  public void close(){
    var root = (Stage) paneFilmModifica.getScene().getWindow();
    root.close();
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    genere.setItems(FXCollections.observableList(Arrays.stream(GENERE.values()).toList()));
  }
}
