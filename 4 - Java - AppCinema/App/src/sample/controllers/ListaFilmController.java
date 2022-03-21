package sample.controllers;

import java.net.URL;
import java.sql.Time;
import java.time.Year;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.interfaceDAO.FilmDAO;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;

public class ListaFilmController implements Initializable {

  public AnchorPane paneListaFilm;
  public TableColumn<Film,String> titolo;
  public TableColumn<Film,String> trama;
  public TableColumn<Film,String> regia;
  public TableColumn<Film, Year> anno;
  public TableColumn<Film, Time> durata;
  public TableColumn<Film, GENERE> genere;
  public TableView table;
  private FilmDAO filmDAO;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
    final var films = filmDAO.queryListFilm();
    titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
    trama.setCellValueFactory(new PropertyValueFactory<>("trama"));
    regia.setCellValueFactory(new PropertyValueFactory<>("regia"));
    anno.setCellValueFactory(new PropertyValueFactory<>("annoUscita"));
    durata.setCellValueFactory(new PropertyValueFactory<>("durataFilm"));
    genere.setCellValueFactory(new PropertyValueFactory<>("genere"));
    table.setItems(FXCollections.observableList(films));
  }

  public void rimuovi(ActionEvent actionEvent) {
    filmDAO = new FilmDaoImpl(DatabaseConnection.getConnection());
    Film row = (Film) table.getSelectionModel().getSelectedItems().get(0);
    filmDAO.deleteFilm(filmDAO.queryFilmById(row.getTitolo()));
    table.getItems().remove(row);
  }
}
