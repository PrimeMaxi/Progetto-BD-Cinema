package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;
import javafx.scene.layout.StackPane;
import sample.models.dao.implDAO.CinemaDAOImpl;
import sample.models.entity.Cinema;

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
  private CinemaDAOImpl cinemaDAO;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public void saveFilm(ActionEvent actionEvent) {

  }

  public void cancelFilm(ActionEvent actionEvent) {
  }


}
