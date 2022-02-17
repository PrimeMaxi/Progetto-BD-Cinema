package sample.controllers;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.fxml.Initializable;
import javafx.scene.control.Label;

public class ItemSalaController implements Initializable {

  public Label numeroSalaITem;
  private Integer inputSalaItem;

  @Override
  public void initialize(URL url, ResourceBundle resourceBundle) {

  }

  public Label getNumeroSalaITem() {
    return numeroSalaITem;
  }


  public void setNumeroSalaITem(Integer numeroSalaItem) {
    this.inputSalaItem = numeroSalaItem;
    this.numeroSalaITem.setText(inputSalaItem.toString());
  }


}
