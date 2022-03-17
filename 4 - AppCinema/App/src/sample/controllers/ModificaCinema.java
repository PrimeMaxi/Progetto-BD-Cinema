package sample.controllers;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import sample.database.DatabaseConnection;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.dao.interfaceDAO.CinemaDAO;
import sample.models.entity.Cinema;
import sample.service.SceneCreator;

public class ModificaCinema implements Initializable {

  public TextField NomeCinema;
  public TextField Indirizzo;
  public TextField Provincia;
  public Button SalvaButton;
  public Button AnnullaButton;
  public TextField numeroSala;
  public TextField città;
  public TextField telefono;
  public StackPane rootPane;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    try {
      CinemaDAOImpl cinemaDao = new CinemaDAOImpl(DatabaseConnection.getConnection());
      var cinema = cinemaDao.retriveCinema();
      NomeCinema.setText(cinema.getNomeCinema());
      Indirizzo.setText(cinema.getIndirizzo());
      Provincia.setText(cinema.getProvincia());
      numeroSala.setText(cinema.getNumeroSala().toString());
      città.setText(cinema.getCittà());
      telefono.setText("0" + cinema.getTelefono().toString());
    } catch (SQLException e) {
      e.printStackTrace();
    }
  }

  public void updateCinema(ActionEvent actionEvent) throws SQLException, IOException {
    CinemaDAOImpl cinemaDAO = new CinemaDAOImpl(DatabaseConnection.getConnection());
    Cinema cinema = new Cinema();
    cinema.setNomeCinema(NomeCinema.getText());
    cinema.setIndirizzo(Indirizzo.getText());
    cinema.setCittà(città.getText());
    cinema.setNumeroSala(Integer.parseInt(numeroSala.getText()));
    cinema.setTelefono(Integer.parseInt(telefono.getText()));
    cinema.setProvincia(Provincia.getText());
    cinemaDAO.updateCinema(cinema);
    SceneCreator.launchScene("viewsRefactor/Dashboard.fxml");
  }

  public void cancelCinema(ActionEvent actionEvent) {
    try {
      SceneCreator.launchScene("viewsRefactor/Dashboard.fxml");
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

}
