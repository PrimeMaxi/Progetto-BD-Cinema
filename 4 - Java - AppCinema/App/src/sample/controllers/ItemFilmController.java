package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Region;

public class ItemFilmController implements Initializable {

  public Label nomeFilmItem;
  public AnchorPane paneItemFilm;
  private CinemaDashboardController cinemaDashboardController;
  private Integer idProiezione;

  public Label getNomeFilmItem() {
    return nomeFilmItem;
  }

  public void setNomeFilmItem(String nomeFilmItem) {
    this.nomeFilmItem.setText(nomeFilmItem);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {
  }

  public void setCinemaDashboardController(
      CinemaDashboardController cinemaDashboardController) {
    this.cinemaDashboardController = cinemaDashboardController;
  }

  public void clickItemFilm(MouseEvent mouseEvent) {
    cinemaDashboardController.setItemFilmSelected(idProiezione);
  }

  public void setIdProiezione(Integer idProiezione) {
    this.idProiezione = idProiezione;
  }
}
