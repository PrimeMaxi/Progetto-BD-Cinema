package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.time.Year;
import java.util.List;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.dao.implDAO.FilmDaoImpl;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;
import sample.models.entity.Film;
import sample.models.enumerations.GENERE;
import sample.service.SceneCreator;

public class CinemaController implements Initializable {

  public Label cinemaName;
  public Label numeroSala;
  public Button modifica;

  public TableColumn<Film,Integer> idFilm;
  public TableColumn<Film,String> titolo;
  public TableColumn<Film,Year> annoUscita;
  public TableColumn<Film,String> regia;
  public TableColumn<Film,GENERE> genere;
  public TableColumn<Film,Time> durataFilm;
  public TableColumn<Film,Date> inizioData;
  public TableColumn<Film,Date> fineData;
  public TableColumn azione;
  public TableView tableFilms;
  private FilmDaoImpl filmDao;


  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    tableFilms.getColumns().clear();
    CinemaDAO cinemaDAO = null;
    try {
      cinemaDAO = new CinemaDAOImpl(DatabaseConnection.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
    }
    Cinema cinema = cinemaDAO != null ? cinemaDAO.retriveCinema() : null;
    cinemaName.setText(cinema != null ? cinema.getNomeCinema() : null);
    numeroSala.setText(cinema != null ? cinema.getNumeroSala().toString() : null);

    //Rimpiere tabella di film
    filmDao = new FilmDaoImpl(DatabaseConnection.getConnection());
    List<Film> filmList = filmDao.queryListFilm();
    final ObservableList<Film> data = FXCollections.observableList(filmList);
    idFilm.setCellValueFactory(new PropertyValueFactory<>("idFilm"));
    titolo.setCellValueFactory(new PropertyValueFactory<>("titolo"));
    annoUscita.setCellValueFactory(new PropertyValueFactory<>("annoUscita"));
    regia.setCellValueFactory(new PropertyValueFactory<>("regia"));
    genere.setCellValueFactory(new PropertyValueFactory<>("genere"));
    durataFilm.setCellValueFactory(new PropertyValueFactory<>("durataFilm"));
    inizioData.setCellValueFactory(new PropertyValueFactory<>("inizioData"));
    fineData.setCellValueFactory(new PropertyValueFactory<>("fineData"));
    tableFilms.getColumns().addAll(idFilm,titolo,annoUscita,regia,genere,durataFilm,inizioData,fineData);
    tableFilms.setItems(data);
  }


  public void modificaCinema(ActionEvent actionEvent) {
    try {
      SceneCreator.launchScene("views/modificaCinema.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  public void back(ActionEvent actionEvent) {
  }

  public void listFilm(ActionEvent actionEvent) {
  }

  public void booking(ActionEvent actionEvent) {
  }


}
