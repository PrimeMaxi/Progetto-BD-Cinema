package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.OverrunStyle;
import javafx.scene.layout.Region;

public class ItemFilmController implements Initializable {

  public Label nomeFilmItem;

  public Label getNomeFilmItem() {
    return nomeFilmItem;
  }

  public void setNomeFilmItem(String nomeFilmItem) {
    this.nomeFilmItem.setText(nomeFilmItem);
  }

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }
}
