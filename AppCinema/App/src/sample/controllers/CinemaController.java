package sample.controllers;

import java.net.URL;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;

public class CinemaController implements Initializable {

  public Label cinemaName;
  public Label numeroSala;
  public Button modifica;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    CinemaDAO cinemaDAO = null;
    try {
      cinemaDAO = new CinemaDAOImpl(DatabaseConnection.getConnection());
    } catch (SQLException e) {
      e.printStackTrace();
    }

    Cinema cinema = cinemaDAO.retriveCinema();
    cinemaName.setText(cinema.getNomeCinema());
    numeroSala.setText(cinema.getNumeroSala().toString());
  }

  public void modificaCinema(ActionEvent actionEvent) {

  }

  public void back(ActionEvent actionEvent) {
  }

  public void listFilm(ActionEvent actionEvent) {
  }

  public void booking(ActionEvent actionEvent) {
  }


}
