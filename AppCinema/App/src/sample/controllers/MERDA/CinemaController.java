package sample.controllers.MERDA;

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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableColumn.CellEditEvent;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.control.cell.TextFieldTableCell;
import sample.Application;
import sample.controllers.NewFilmController;
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
  public TableView<Film> tableFilms;
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
    setupTableView();

    //Colonna Titolo tabella modificiale
    tableFilms.setEditable(true);
    titolo.setCellFactory(TextFieldTableCell.forTableColumn());

    // Doppio click sulla lista
    modifyRowTableViewDoubleClick();
  }




  public void modificaCinema(ActionEvent actionEvent) {
    try {
      SceneCreator.launchScene("views/modificaCinema.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }



  public void addFilm(ActionEvent actionEvent) {
    
  }

  public void booking(ActionEvent actionEvent) {
  }

  public void changeTitoloCell(CellEditEvent<Film, String> filmStringCellEditEvent) {
    filmDao = new FilmDaoImpl(DatabaseConnection.getConnection());
    filmDao.updateFilmTitolo(filmStringCellEditEvent.getNewValue(),filmStringCellEditEvent.getOldValue());
    Film filmSelected = (Film) tableFilms.getSelectionModel().getSelectedItem();
    filmSelected.setTitolo(filmStringCellEditEvent.getNewValue());
  }
  private void modifyRowTableViewDoubleClick() {
    tableFilms.setOnMouseClicked(
        mouseEvent -> {
          if (mouseEvent.getClickCount() == 2) {
            FXMLLoader loader =
                new FXMLLoader(Application.class.getResource("views/newFilm.fxml"));
            try {
              Application.setRoot(loader.load());
            } catch (IOException e) {
              e.printStackTrace();
            }
            NewFilmController newFilmController = loader.getController();
            ObservableList<Film> list = tableFilms.getSelectionModel().getSelectedItems();
            newFilmController.setData(list);
            Scene scene = new Scene(Application.getRoot());
            Application.getPrimaryStage().setScene(scene);
            Application.getPrimaryStage().show();
          }
        });
  }
  private void setupTableView() {
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
}
