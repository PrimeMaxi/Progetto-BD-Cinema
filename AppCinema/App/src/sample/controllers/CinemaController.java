package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class CinemaController implements Initializable {

  public Label cinemaName;
  public Label numeroSala;
  public Button modifica;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
    cinemaName.setText("CinemaMax");
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
